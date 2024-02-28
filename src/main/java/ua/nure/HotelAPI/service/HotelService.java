package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.Hotel;
import ua.nure.HotelAPI.repo.HotelRepo;

import java.util.List;

@Service
@Transactional
public class HotelService {
    private final HotelRepo hotelRepo;

    public HotelService(HotelRepo hotelRepo) {

        this.hotelRepo = hotelRepo;
    }

    public List<Hotel> getHotels () { return hotelRepo.findAll(); }
    public Hotel getHotel (Integer hotelId) {
        return hotelRepo.findByHotelId(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found111"));
    }
}
