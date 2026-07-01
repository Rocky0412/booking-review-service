package org.example.reviewservice.CommandLineRunner;


import org.example.reviewservice.models.*;
import org.example.reviewservice.repositories.BookingRespository;
import org.example.reviewservice.repositories.DriverRepository;
import org.example.reviewservice.repositories.PassengerRepository;
import org.example.reviewservice.repositories.ReviewRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class BasicRunner implements CommandLineRunner {

    private final ReviewRepository reviewRepository;
    private final BookingRespository bookingRespository;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;

    @Autowired
    public BasicRunner(
            ReviewRepository reviewRepository,
            BookingRespository bookingRespository,
            DriverRepository driverRepository,
            PassengerRepository passengerRepository) {

        this.reviewRepository = reviewRepository;
        this.bookingRespository = bookingRespository;
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        Review review = Review.builder()
//                .rating(4.5)
//                .content("Very sweet")
//                .build();
//
//        Passenger passenger = Passenger.builder()
//                .firstName("Rocky")
//                .lastName("Doe")
//                .email("rocky@gmail.com")
//                .phoneNumber("123456789")
//                .build();
//
//        Driver driver = Driver.builder()
//                .name("Driver")
//                .LicenceNumber("LICENCE1234")
//                .surname("Surname")
//                .build();
//
//// Save parent entities first
//        passenger = passengerRepository.save(passenger);
//        driver = driverRepository.save(driver);
//
//// Create booking
//        Booking booking = Booking.builder()
//                .price(1200)
//                .startTime(LocalDateTime.now())
//                .review(review)
//                .totalDistance(100)
//                .bookingStatus(BookingStatus.CANCELLED)
//                .driver(driver)
//                .passenger(passenger)
//                .build();
//build
//// Save booking
//        bookingRespository.save(booking);





    }
}