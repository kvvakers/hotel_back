package ua.nure.HotelAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.nure.HotelAPI.models.Owner;
import ua.nure.HotelAPI.models.Room;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    Optional<Room> findByRoomId(Integer roomId);

    @Query("SELECT r " +
            "FROM Room r LEFT JOIN Deal d " +
            "ON r.hotelId = d.hotelId " +
            "AND r.roomId = d.roomId " +
            "WHERE r.bedNumbers >= ?1 AND (d.dateBegin IS NULL OR d.dateEnd < ?2 OR d.dateBegin > ?3)")
    List<Room> findByPersonAmountAndDate(Integer personAmount, Timestamp startDate, Timestamp endDate);
}
