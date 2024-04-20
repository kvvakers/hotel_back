package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.Room;
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
    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }
    public List<Room> getRooms() {return roomRepo.findAll();}
    //public List<Room> getRooms(Integer personAmount) {return roomRepo.findByPersonAmount(personAmount);}
    public Room getRoom(Integer roomId) {
        return roomRepo.findByRoomId(roomId).orElseThrow(() -> new RuntimeException("Room not found111"));
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
            return rooms;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
