package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.Owner;
import ua.nure.HotelAPI.repo.OwnerRepo;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class OwnerService {
    private final OwnerRepo ownerRepo;

    public OwnerService(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }
    public List<Owner> getOwners() {return ownerRepo.findAll();}
    public Owner getOwner(Integer ownerId) {
        return ownerRepo.findByOwnerId(ownerId).orElseThrow(() -> new RuntimeException("Owner not found111"));
    }
}
