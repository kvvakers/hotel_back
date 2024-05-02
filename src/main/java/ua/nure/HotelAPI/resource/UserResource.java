package ua.nure.HotelAPI.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.HotelAPI.service.UserService;
import ua.nure.HotelAPI.models.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) { this.userService = userService; }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() { return userService.getUsers(); }

    @GetMapping("/me/")
    public ResponseEntity<?> getUserData(@RequestHeader("Authorization") String token) {return  userService.getUserData(token);}

    @PostMapping("/registration/")
    public ResponseEntity<?> registration(@RequestBody User user) {
        return userService.registration(user);
    }

    @PostMapping("/authorization/")
    public ResponseEntity<?> authorization(@RequestBody User user) { return userService.authorization(user); }
}
