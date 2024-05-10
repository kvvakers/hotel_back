package ua.nure.HotelAPI.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.HotelAPI.service.HotelService;
import ua.nure.HotelAPI.models.Hotel;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelResource {
    private final HotelService hotelService;

    public HotelResource(HotelService hotelService) { this.hotelService = hotelService; }
    @GetMapping
    public List<Hotel> getHotelsWithParams(
            @RequestParam String cityName,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Integer personAmount
    ) {
        return hotelService.getHotelsWithParams(cityName, startDate, endDate, personAmount);
    }
    @GetMapping("/my/")
    public ResponseEntity<?> getMyHotels(@RequestHeader("Authorization") String token) {
        return hotelService.getMyHotels(token);
    }

    @GetMapping("/my/by")
    public ResponseEntity<?> getMyHotelById(@RequestHeader("Authorization") String token, @RequestParam Integer id) {
        return hotelService.getMyHotelById(token, id);
    }

    @PostMapping
    public ResponseEntity<?> changeHotels(@RequestBody Hotel hotel, @RequestHeader("Authorization") String token) {
        return hotelService.changeHotels(token, hotel);
    }
}
