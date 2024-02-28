package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.City;
import ua.nure.HotelAPI.repo.CityRepo;

import java.util.List;

@Service
@Transactional (rollbackOn = Exception.class)
public class CityService {
    private final CityRepo cityRepo;

    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public List<City> getCities () { return cityRepo.findAll(); }
    public City getCity (String name) {
        return cityRepo.findByCityName(name).orElseThrow(() -> new RuntimeException("City not found"));
    }
}
