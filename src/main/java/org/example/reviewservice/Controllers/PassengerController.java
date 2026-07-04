package org.example.reviewservice.Controllers;

import org.example.reviewservice.RequestDTO.PassengerRequestDTO;
import org.example.reviewservice.ResponseDTO.PassengerResponseDTO;
import org.example.reviewservice.Services.PassengerServices;
import org.example.reviewservice.models.Passenger;
import org.example.reviewservice.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/passengers")
@PreAuthorize("isAuthenticated()")
public class PassengerController {
    private final PassengerRepository passengerRepository;
    private final PassengerServices passengerServices;
    @Autowired
    public PassengerController(PassengerRepository passengerRepository, PassengerServices passengerServices) {
        this.passengerRepository = passengerRepository;
        this.passengerServices = passengerServices;
    }
    @GetMapping
    public ResponseEntity<List<PassengerResponseDTO>> getAllPassengers() {

        return passengerServices.getAllPassengers();

    }

    @GetMapping("/{id}")
    public  ResponseEntity<PassengerResponseDTO> getPassengerById(@PathVariable Long id) {
        return passengerServices.getPassengerById(id);
    }
    @PostMapping("/create")
    public ResponseEntity<Passenger> createPassenger(@RequestBody PassengerRequestDTO
                                                                            passengerRequestDTO) {

        return  passengerServices.createPassenger(passengerRequestDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Passenger>> deletePassenger(@PathVariable Long id) {
        return passengerServices.deletePassenger(id);
    }
    @PatchMapping("/{id}/email")
    public ResponseEntity<Passenger> updatePassenger(
            @RequestBody String email,
            @PathVariable Long id) {

        // Find the passenger to update
        Optional<Passenger> passenger = passengerRepository.findById(id);

        if (passenger.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Check if another passenger already has this email
        Passenger existingPassenger = passengerRepository.findByEmail(email);

        if (existingPassenger != null && !existingPassenger.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        passenger.get().setEmail(email);
        passengerRepository.save(passenger.get());

        return ResponseEntity.ok(passenger.get());
    }
}
