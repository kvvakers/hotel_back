package ua.nure.HotelAPI.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.HotelAPI.service.UserService;
import ua.nure.HotelAPI.models.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) { this.userService = userService; }

    @GetMapping
    public List<User> getUsers() { return userService.getUsers(); }
}
