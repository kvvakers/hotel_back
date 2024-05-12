package ua.nure.HotelAPI.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.HotelAPI.models.Deal;
import ua.nure.HotelAPI.records.RequestedDeal;
import ua.nure.HotelAPI.service.DealService;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealResource {
    private final DealService dealService;

    public DealResource(DealService dealService) { this.dealService = dealService; }

    @GetMapping
    public ResponseEntity<?> getDeals(@RequestHeader("Authorization") String token) { return dealService.getDeals(token); }

    @PostMapping
    public ResponseEntity<?> postDeal(@RequestHeader("Authorization") String token, @RequestBody RequestedDeal deal) {
        return dealService.postDeal(token, deal);
    }
}
