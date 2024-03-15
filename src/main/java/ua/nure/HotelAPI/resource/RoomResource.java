package ua.nure.HotelAPI.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.HotelAPI.models.Room;
import ua.nure.HotelAPI.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomResource {
    private final RoomService roomService;

    public RoomResource(RoomService roomService) { this.roomService = roomService; }

//    @GetMapping
//    public List<Room> getRooms() { return roomService.getRooms(); }
    //@GetMapping
    //public List<Room> getRoomsByPersonAmount(@RequestParam Integer personAmount) { return roomService.getRooms(personAmount); }
}
