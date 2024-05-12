package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.nure.HotelAPI.models.Deal;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface DealRepo extends JpaRepository<Deal, Integer> {
    Optional<Deal> findByDealId(Integer dealId);

    @Query(
            "SELECT d FROM Deal d INNER JOIN Room r ON d.roomId = r.id INNER JOIN Hotel h ON r.hotelId = h.hotelId WHERE h.ownerEmail = ?1 AND d.dateEnd >= ?2"
    )
    Optional<List<Deal>> findMyDeals(String email, Timestamp today);
}
