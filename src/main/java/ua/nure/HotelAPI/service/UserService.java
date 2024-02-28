package ua.nure.HotelAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.User;
import ua.nure.HotelAPI.repo.UserRepo;

import java.util.List;

@Service
@Transactional (rollbackOn = Exception.class)
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getUsers() { return userRepo.findAll(); }
    public User getUser (Integer userId) {
        return userRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found111"));
    }
}
