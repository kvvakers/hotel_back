package ua.nure.HotelAPI.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.HotelAPI.models.City;
import ua.nure.HotelAPI.service.CityService;

import java.util.List;

@RestController
@RequestMapping ( "/api/cities")
public class CityResource {
    private final CityService cityService;

    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getCities() { return cityService.getCities(); }
}
