package ua.nure.HotelAPI.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.HotelAPI.models.Deal;
import ua.nure.HotelAPI.service.DealService;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealResource {
    private final DealService dealService;

    public DealResource(DealService dealService) { this.dealService = dealService; }

    @GetMapping
    public List<Deal> getDeals() { return dealService.getDeals(); }
}
