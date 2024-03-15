package ua.nure.HotelAPI.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.HotelAPI.service.HotelService;
import ua.nure.HotelAPI.models.Hotel;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelResource {
    private final HotelService hotelService;

    public HotelResource(HotelService hotelService) { this.hotelService = hotelService; }

//    @GetMapping
//    public List<Hotel> getHotels() { return hotelService.getHotels(); }

    @GetMapping
    public List<Hotel> getHotelsWithParams(
            @RequestParam String cityName,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Integer personAmount
    ) {
        return hotelService.getHotelsWithParams(cityName, startDate, endDate, personAmount);
    }
}
