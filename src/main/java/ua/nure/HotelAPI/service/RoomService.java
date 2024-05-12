package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.helper.JWTHelper;
import ua.nure.HotelAPI.models.Room;
import ua.nure.HotelAPI.repo.ImageRepo;
import ua.nure.HotelAPI.repo.RoomRepo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackOn = Exception.class)
public class RoomService {
    private final RoomRepo roomRepo;
    private final ImageRepo imageRepo;
    public RoomService(RoomRepo roomRepo, ImageRepo imageRepo) {
        this.roomRepo = roomRepo;
        this.imageRepo = imageRepo;
    }
    public List<Room> getRoomWithParams (String startDate, String endDate, Integer personAmount, Integer hotelId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedStartDate = null;
        Date parsedEndDate = null;
        try {
            parsedStartDate = dateFormat.parse(startDate);
            parsedEndDate = dateFormat.parse(endDate);

            Timestamp timestampStart = new Timestamp(parsedStartDate.getTime());
            Timestamp timestampEnd = new Timestamp(parsedEndDate.getTime());

            List<Room> rooms = this.roomRepo.findByPersonAmountAndDate(personAmount, timestampStart, timestampEnd);
            rooms.removeIf(room -> !Objects.equals(room.getHotelId(), hotelId));

            for (Room room: rooms) {
                room.setImages(imageRepo.findByRoomId(room.getId()).orElse(new ArrayList<>()));
            }
            return rooms;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> makeRoomInaccessible(String token, Integer id) {
        if (!JWTHelper.isTokenCorrect(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");

        try {
            Room room = roomRepo.findById(id).orElseThrow();

            room.setStatus(!room.getStatus());

            roomRepo.save(room);
            return ResponseEntity.ok("Successfully changed");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Room not found!");
        }
    }
}
