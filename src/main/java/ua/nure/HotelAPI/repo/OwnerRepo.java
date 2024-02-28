package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.HotelAPI.models.Owner;

import java.util.Optional;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Integer> {
    Optional<Owner> findByOwnerId(Integer ownerId);
}
