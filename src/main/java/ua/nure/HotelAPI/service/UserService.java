package ua.nure.HotelAPI.service;

import io.jsonwebtoken.*;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.nure.HotelAPI.models.User;
import ua.nure.HotelAPI.records.RespondedUser;
import ua.nure.HotelAPI.repo.UserRepo;

import javax.security.auth.kerberos.EncryptionKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional (rollbackOn = Exception.class)
public class UserService {
    private final UserRepo userRepo;
    private final long EXPIRATION_TIME = 259200000;
    private final String SECRET_KEY = "This_fucking_piece_of_shit_this_hotel";

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<List<User>> getUsers() { return ResponseEntity.ok(userRepo.findAll()); }
    public User getUser (Integer userId) {
        return userRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found111"));
    }

    public ResponseEntity<?> getUserData(String token) {
        if (!isTokenCorrect(token)) return ResponseEntity.status(401).body("Token is invalid");
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            return ResponseEntity.ok().body(createRespondedUser(userRepo.findByEmail(email).orElse(null)));
        } catch (Exception e) {
            System.out.println("Failed to parse the unsigned JWT: " + e.getMessage());
            return ResponseEntity.badRequest().body("You are error:(");
        }
    }


    public ResponseEntity<?> registration(User user) {
        if (user.getEmail() == null || user.getPassword() == null) return ResponseEntity.badRequest().body("User is null");

        List<User> users = userRepo.findAll();
        int maxId = 0;
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getEmail(), user.getEmail())) return ResponseEntity.badRequest().body("Email is already exists:(");
            if (users.get(i).getUserId() > maxId) {
                maxId = users.get(i).getUserId();
            }
        }

        user.setUserId(maxId + 1);

        try {
            user.setPassword(this.hashPassword(user.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error:(");
        }

        try {
            user.setToken(createToken(user.getEmail()));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("We are dead");
        }

        user = userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRespondedUser(user));
    }

    public ResponseEntity<?> authorization(User user) {
        Optional<User> foundedUser = userRepo.findByEmail(user.getEmail());
        if (foundedUser.isEmpty()) return ResponseEntity.status(404).body("User not found");
        try {
            if (Objects.equals(foundedUser.get().getPassword(), hashPassword(user.getPassword()))) {
                user.setToken(createToken(user.getEmail()));
                return ResponseEntity.accepted().body(createRespondedUser(user));
            }
            return ResponseEntity.status(401).body("Password is invalid");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error:(");
        }
    }

    private RespondedUser createRespondedUser(@Nullable User user) {
        if (user == null) return new RespondedUser("", "", "", "", "");
        return new RespondedUser(user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getToken());
    }
    private String hashPassword(String password) throws NoSuchAlgorithmException, InvalidParameterException {
        if (password.isEmpty()) throw new InvalidParameterException();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = messageDigest.digest(password.getBytes());

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
    private String createToken(String email) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
    private boolean isTokenCorrect(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
