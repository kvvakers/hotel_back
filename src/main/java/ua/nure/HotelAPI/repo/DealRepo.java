package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.HotelAPI.models.Deal;

import java.util.Optional;

@Repository
public interface DealRepo extends JpaRepository<Deal, Integer> {
    Optional<Deal> findByDealId(Integer dealId);
}
