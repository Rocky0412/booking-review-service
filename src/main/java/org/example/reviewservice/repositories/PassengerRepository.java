package org.example.reviewservice.repositories;

import org.example.reviewservice.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByEmail(String email) ;
}
