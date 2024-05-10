package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.nure.HotelAPI.models.Room;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    Optional<Room> findById(Integer id);
    Optional<List<Room>> findByHotelId(Integer hotelId);

    @Query("SELECT r " +
            "FROM Room r " +
            "LEFT JOIN Deal d " +
            "ON r.id = d.roomId " +
            "WHERE r.bedNumbers >= ?1 AND (d.dateBegin IS NULL OR d.dateEnd < ?2 OR d.dateBegin > ?3)")
    List<Room> findByPersonAmountAndDate(Integer personAmount, Timestamp startDate, Timestamp endDate);
}
