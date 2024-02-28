package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.HotelAPI.models.Hotel;

import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    Optional<Hotel> findByHotelId(Integer hotelId);
}
