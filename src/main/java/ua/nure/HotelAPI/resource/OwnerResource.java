package ua.nure.HotelAPI.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.HotelAPI.models.Owner;
import ua.nure.HotelAPI.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerResource {
    private final OwnerService ownerService;

    public OwnerResource(OwnerService ownerService) { this.ownerService = ownerService; }

    @GetMapping
    public List<Owner> getOwners() { return ownerService.getOwners(); }
}
