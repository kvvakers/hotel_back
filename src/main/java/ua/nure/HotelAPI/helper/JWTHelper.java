package ua.nure.HotelAPI.helper;

import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTHelper {
    public static final long EXPIRATION_TIME = 259200000;
    public static final String SECRET_KEY = "This_fucking_piece_of_shit_this_hotel";
    public static String createToken(String email) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
    public static boolean isTokenCorrect(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    public static String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(JWTHelper.SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getSubject();
    }
}
