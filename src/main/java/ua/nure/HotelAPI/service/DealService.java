package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.helper.JWTHelper;
import ua.nure.HotelAPI.models.Deal;
import ua.nure.HotelAPI.models.Room;
import ua.nure.HotelAPI.records.RequestedDeal;
import ua.nure.HotelAPI.repo.DealRepo;
import ua.nure.HotelAPI.repo.HotelRepo;
import ua.nure.HotelAPI.repo.RoomRepo;
import ua.nure.HotelAPI.repo.UserRepo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DealService {
    private final DealRepo dealRepo;
    private final RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
    private final UserRepo userRepo;

    public DealService(DealRepo dealRepo, RoomRepo roomRepo, HotelRepo hotelRepo, UserRepo userRepo) {
        this.roomRepo = roomRepo;
        this.hotelRepo = hotelRepo;
        this.dealRepo = dealRepo;
        this.userRepo = userRepo;
    }

    public ResponseEntity<?> getDeals(String token) {
        if (!JWTHelper.isTokenCorrect(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        String email = JWTHelper.getEmailFromToken(token);

        List<Deal> deals = dealRepo.findMyDeals(email, new Timestamp(System.currentTimeMillis())).orElse(new ArrayList<>());
        for (Deal deal : deals) {
            try {
                Room room = roomRepo.findById(deal.getRoomId()).orElseThrow();
                room.setHotel(hotelRepo.findByHotelId(room.getHotelId()).orElseThrow());
                deal.setRoom(room);

                deal.setPhone((userRepo.findByEmail(email).orElseThrow()).getPhone());
            } catch (NoSuchElementException ignored) {}
        }

        return ResponseEntity.ok().body(deals);
    }
    public ResponseEntity<?> getBooks(String token) {
        if (!JWTHelper.isTokenCorrect(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        String email = JWTHelper.getEmailFromToken(token);

        List<Deal> deals = dealRepo.findMyBooks(email, new Timestamp(System.currentTimeMillis())).orElse(new ArrayList<>());
        for (Deal deal : deals) {
            try {
                Room room = roomRepo.findById(deal.getRoomId()).orElseThrow();
                room.setHotel(hotelRepo.findByHotelId(room.getHotelId()).orElseThrow());
                deal.setRoom(room);

                deal.setPhone((userRepo.findByEmail(email).orElseThrow()).getPhone());
            } catch (NoSuchElementException ignored) {}
        }

        return ResponseEntity.ok().body(deals);
    }
    public Deal getDeal (Integer dealId) {
        return dealRepo.findByDealId(dealId).orElseThrow(() -> new RuntimeException("Deal not found111"));
    }
    public ResponseEntity<?> postDeal(String token, RequestedDeal rDeal) {
        if (!JWTHelper.isTokenCorrect(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        String email = JWTHelper.getEmailFromToken(token);

        Deal deal = new Deal();

        if (roomRepo.findById(rDeal.roomId()).isEmpty()) return ResponseEntity.status(404).body("Room not found!");

        deal.setEmail(email);
        deal.setDealId((int)dealRepo.count() + 1);
        deal.setRoomId(rDeal.roomId());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date parsedStartDate = null;
        Date parsedEndDate = null;
        try {
            parsedStartDate = dateFormat.parse(rDeal.dateBegin());
            parsedEndDate = dateFormat.parse(rDeal.dateEnd());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Bad date!");
        }

        Timestamp timestampStart = new Timestamp(parsedStartDate.getTime());
        Timestamp timestampEnd = new Timestamp(parsedEndDate.getTime());

        LocalDate localDateEnd = timestampEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateStart = timestampStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        deal.setDateBegin(timestampStart);
        deal.setDateEnd(timestampEnd);

        int daysDiff = (int)ChronoUnit.DAYS.between(localDateStart, localDateEnd);
        try {
            int total = daysDiff * roomRepo.findPriceByRoomId(deal.getRoomId()).orElseThrow();
            deal.setTotal(total);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Something went wrong! Price not defined!");
        }
        try {
            dealRepo.save(deal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().body("Deal successfully added!");
    }
}

