package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.Owner;
import ua.nure.HotelAPI.models.Room;
import ua.nure.HotelAPI.repo.OwnerRepo;
import ua.nure.HotelAPI.repo.RoomRepo;

import java.util.List;

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
}
