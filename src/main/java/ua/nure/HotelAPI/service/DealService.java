package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.Deal;
import ua.nure.HotelAPI.repo.DealRepo;

import java.util.List;

@Service
@Transactional
public class DealService {
    private final DealRepo dealRepo;

    public DealService(DealRepo dealRepo) {

        this.dealRepo = dealRepo;
    }

    public List<Deal> getDeals() { return dealRepo.findAll(); }
    public Deal getDeal (Integer dealId) {
        return dealRepo.findByDealId(dealId).orElseThrow(() -> new RuntimeException("Deal not found111"));
    }
}

