package org.example.reviewservice.Controllers.Aurh;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.example.reviewservice.JWTService.JWTUtils;
import org.example.reviewservice.RequestDTO.PassengerAuthDTO;
import org.example.reviewservice.RequestDTO.PassengerRequestDTO;
import org.example.reviewservice.Services.PassengerServices;
import org.example.reviewservice.models.Passenger;
import org.example.reviewservice.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;
import javax.security.auth.Subject;
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
    @PostMapping("/signup/passenger")
    ResponseEntity<Passenger> signIn(@RequestBody PassengerRequestDTO passengerRequestDTO) {
        passengerRequestDTO.setPassword(passwordEncoder.encode(passengerRequestDTO.getPassword()));
        return passengerServices.createPassenger(passengerRequestDTO);
    }
    @GetMapping("/signin/passenger")
    ResponseEntity<?> login(@RequestBody PassengerAuthDTO passengerAuthDTO, HttpServletRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            passengerAuthDTO.getUsername(),
                            passengerAuthDTO.getPassword()
                    )
            );

            Map<String,String> map = new HashMap<>();

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            map.put("username", userDetails.getUsername());
            String JWT_Token= jwtUtils.buildToken(map,userDetails);

            System.out.println( "JWT_Token " + JWT_Token);



            return ResponseEntity.ok("Login successful " + JWT_Token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

    }


}
