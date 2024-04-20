package ua.nure.HotelAPI.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.HotelAPI.models.Room;
import ua.nure.HotelAPI.repo.RoomRepo;
import ua.nure.HotelAPI.service.RoomService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomResource {
    private final RoomService roomService;

    public RoomResource(RoomService roomService) { this.roomService = roomService; }

    @GetMapping
    public List<Room> roomWithParams (
            @RequestParam Integer hotelId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Integer personAmount
    ) {
        return this.roomService.getRoomWithParams(startDate, endDate, personAmount, hotelId);
    }

}
