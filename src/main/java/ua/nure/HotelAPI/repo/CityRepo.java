package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.HotelAPI.models.City;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, String> {
    Optional<City> findByCityName(String cityName);
}
