package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.Deal;
import ua.nure.HotelAPI.models.Hotel;
import ua.nure.HotelAPI.models.Room;
import ua.nure.HotelAPI.repo.DealRepo;
import ua.nure.HotelAPI.repo.HotelRepo;
import ua.nure.HotelAPI.repo.RoomRepo;
import ua.nure.HotelAPI.resource.RoomResource;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class HotelService {
    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;
    private final DealRepo dealRepo;

    public HotelService (HotelRepo hotelRepo, RoomRepo roomRepo, DealRepo dealRepo) {

        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
        this.dealRepo = dealRepo;
    }

    public List<Hotel> getHotels () { return hotelRepo.findAll(); }
    public List<Hotel> getHotelsWithParams (String cityName, String startDate, String endDate, Integer personAmount) {

        List<Hotel> hotels = hotelRepo.findAll();

        if (!cityName.isEmpty()) hotels.removeIf(hotel -> !Objects.equals(hotel.getCityName(), cityName));
        if (personAmount != 0) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedStartDate = dateFormat.parse(startDate);
                Date parsedEndDate = dateFormat.parse(endDate);
                Timestamp timestampStart = new Timestamp(parsedStartDate.getTime());
                Timestamp timestampEnd = new Timestamp(parsedEndDate.getTime());
                List<Room> rooms = roomRepo.findByPersonAmountAndDate(personAmount, timestampStart, timestampEnd);
                hotels.removeIf(hotel -> {
                    for (Room room: rooms) {
                        if (Objects.equals(room.getHotelId(), hotel.getHotelId())) return false;
                    }
                    return true;
                });
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }

        }


        return hotels;
    }
    public Hotel getHotel (Integer hotelId) {
        return hotelRepo.findByHotelId(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found111"));
    }


}
