package org.example.reviewservice.Controllers.Aurh;

import org.example.reviewservice.RequestDTO.PassengerRequestDTO;
import org.example.reviewservice.Services.PassengerServices;
import org.example.reviewservice.models.Passenger;
import org.example.reviewservice.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class PassengerAuth {


    private final PassengerServices passengerServices;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    PassengerAuth(
                  PassengerServices passengerServices,
                  PasswordEncoder passwordEncoder) {

        this.passengerServices = passengerServices;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/signup/passenger")
    ResponseEntity<Passenger> signIn(@RequestBody PassengerRequestDTO passengerRequestDTO) {
        passengerRequestDTO.setPassword(passwordEncoder.encode(passengerRequestDTO.getPassword()));
        return passengerServices.createPassenger(passengerRequestDTO);
    }

}
