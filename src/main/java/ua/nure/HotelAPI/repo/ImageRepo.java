package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.HotelAPI.models.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image, Integer> {
    Optional<List<Image>> findByRoomId(Integer roomId);
    Optional<Image> findByImageId(Integer imageId);
}
