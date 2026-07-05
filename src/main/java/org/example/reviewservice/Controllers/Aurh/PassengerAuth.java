package org.example.reviewservice.Controllers.Aurh;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entityservices.models.Passenger;
import org.example.reviewservice.JWTService.JWTUtils;
import org.example.reviewservice.RequestDTO.PassengerAuthDTO;
import org.example.reviewservice.RequestDTO.PassengerRequestDTO;
import org.example.reviewservice.Services.PassengerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class PassengerAuth {


    private final PassengerServices passengerServices;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    @Autowired
    PassengerAuth(
            PassengerServices passengerServices,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTUtils jwtUtils) {

        this.passengerServices = passengerServices;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }
    @GetMapping("/validate")
    ResponseEntity<?> validatePassengerAuth(HttpServletRequest request) {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt-token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    // Validate the JWT here
                    break;
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @PostMapping("/signup/passenger")
    ResponseEntity<Passenger> signIn(@RequestBody PassengerRequestDTO passengerRequestDTO) {
        passengerRequestDTO.setPassword(passwordEncoder.encode(passengerRequestDTO.getPassword()));
        return passengerServices.createPassenger(passengerRequestDTO);
    }
    @GetMapping("/signin/passenger")
    ResponseEntity<?> login(@RequestBody PassengerAuthDTO passengerAuthDTO,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            passengerAuthDTO.getUsername(),
                            passengerAuthDTO.getPassword()
                    )
            );
           if(authentication.isAuthenticated()) {

               Map<String,String> map = new HashMap<>();

               UserDetails userDetails = (UserDetails) authentication.getPrincipal();
               map.put("username", userDetails.getUsername());
               String JWT_Token= jwtUtils.buildToken(map,userDetails);

               //System.out.println( "JWT_Token " + JWT_Token);

               /* Set Token
                *
                *
                * */

               ResponseCookie cookie = ResponseCookie
                       .from("jwt-token",JWT_Token)
                       .httpOnly(true)
                       .maxAge(3600)
                       .path("/")
                       .sameSite("none")
                       .secure(false)
                       .build();
               response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

               System.out.println("cookies == " + cookie.toString());



               return ResponseEntity.ok("Login successful " + JWT_Token);

           }
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

    }


}
