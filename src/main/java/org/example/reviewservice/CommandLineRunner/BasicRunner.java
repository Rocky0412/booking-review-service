package org.example.reviewservice.CommandLineRunner;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.reviewservice.JWTService.JWTUtils;
import org.example.reviewservice.models.*;
import org.example.reviewservice.repositories.BookingRespository;
import org.example.reviewservice.repositories.DriverRepository;
import org.example.reviewservice.repositories.PassengerRepository;
import org.example.reviewservice.repositories.ReviewRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class BasicRunner implements CommandLineRunner {

    private final ReviewRepository reviewRepository;
    private final BookingRespository bookingRespository;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;
    private final JWTUtils jwtUtils;

    @Autowired
    public BasicRunner(
            ReviewRepository reviewRepository,
            BookingRespository bookingRespository,
            DriverRepository driverRepository,
            PassengerRepository passengerRepository,
            JWTUtils jwtUtils) {

        this.reviewRepository = reviewRepository;
        this.bookingRespository = bookingRespository;
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void run(String... args) throws Exception {

        UserDetails userDetails = User
                .builder()
                .username("Rocky")
                .password("password")
                .build();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        claims.put("Token", "ADMIN");
        String jwt= jwtUtils.buildToken(claims, userDetails);
        System.out.println("JWT Token: " + jwt);
        Claims claims1= jwtUtils.parseToken(jwt);
        System.out.println("JWT Claims: " + claims1);





    }
}