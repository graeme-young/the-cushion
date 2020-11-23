package com.graemeyoung.server.resources;

import com.graemeyoung.server.Constants;
import com.graemeyoung.server.domain.User;
import com.graemeyoung.server.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/me")
    public ResponseEntity<Map<String, Boolean>> isLoggedIn(HttpServletRequest request) {
        Map<String, Boolean> map = new HashMap<>();
        try {
            Cookie cookie = WebUtils.getCookie(request, "cushionAccessToken");
            String token = cookie.getValue();
            int userId = (Integer) Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token).getBody().get("userId");
            userService.validateUserById(userId);
            map.put("success", true);
        } catch(Exception e) {

            map.put("success", false);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(HttpServletResponse response, @RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);

        Cookie cookie = new Cookie("cushionAccessToken", "");

        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/*");

        Map<String, String> jwtToken = generateJWTToken(user);

        cookie.setValue(jwtToken.get("token"));

        response.addCookie(cookie);

        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(HttpServletResponse response, @RequestBody Map<String, Object> userMap) {
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.registerUser(firstName, lastName, email, password);
        Cookie cookie = new Cookie("cushionAccessToken", "");

        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/*");

        Map<String, String> jwtToken = generateJWTToken(user);

        cookie.setValue(jwtToken.get("token"));

        response.addCookie(cookie);

        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token  = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
