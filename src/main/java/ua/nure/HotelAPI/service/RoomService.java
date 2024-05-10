package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.Room;
import ua.nure.HotelAPI.repo.ImageRepo;
import ua.nure.HotelAPI.repo.RoomRepo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
}
