package org.example.reviewservice.CommandLineRunner;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.reviewservice.JWTService.JWTUtils;
import org.example.entityservices.models.*;
import org.example.entityservices.repositories.BookingRespository;
import org.example.entityservices.repositories.DriverRepository;
import org.example.entityservices.repositories.PassengerRepository;
import org.example.entityservices.repositories.ReviewRepository;
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







    }
}