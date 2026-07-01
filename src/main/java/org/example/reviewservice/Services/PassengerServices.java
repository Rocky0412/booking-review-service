package org.example.reviewservice.Services;

import org.example.reviewservice.RequestDTO.PassengerRequestDTO;
import org.example.reviewservice.ResponseDTO.PassengerResponseDTO;
import org.example.reviewservice.models.Passenger;
import org.example.reviewservice.repositories.PassengerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServices {
    private final PassengerRepository passengerRepository;

    public PassengerServices(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;

    }

    public ResponseEntity<List<PassengerResponseDTO>> getAllPassengers() {
            List<Passenger> passengers = passengerRepository.findAll();
            List<PassengerResponseDTO> list= new ArrayList<>();
            for(Passenger passenger : passengers) {
                PassengerResponseDTO passengerResponseDTO = PassengerResponseDTO
                        .builder()
                        .email(passenger.getEmail())
                        .firstName(passenger.getFirstName())
                        .lastName(passenger.getLastName())
                        .build();
                passengerResponseDTO.setPhoneNumber(passengerResponseDTO.maskPhoneNumber(passenger.getPhoneNumber()));
                list.add(passengerResponseDTO);
            }
            return ResponseEntity.ok(list);
    }

    public ResponseEntity<PassengerResponseDTO> getPassengerById( @PathVariable long id) {

        Optional<Passenger> passenger =passengerRepository.findById(id);
        System.out.println(passenger);
        if(passenger.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PassengerResponseDTO passengerResponseDTO =PassengerResponseDTO
                .builder()
                .email(passenger.get().getEmail())
                .firstName(passenger.get().getFirstName())
                .lastName(passenger.get().getLastName())
                .build();
        passengerResponseDTO.setPhoneNumber(passengerResponseDTO.maskPhoneNumber(
                passenger.get().getPhoneNumber()));

        return ResponseEntity.ok(passengerResponseDTO);
    }

    public ResponseEntity<Passenger> createPassenger(@RequestBody PassengerRequestDTO passengerRequestDTO) {
        Optional<Passenger> passenger= Optional.ofNullable(passengerRepository
                .findByEmail(passengerRequestDTO.getEmail()));
        System.out.println(passenger);
        if(passenger.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Passenger newPassenger= Passenger
                .builder()
                .email(passengerRequestDTO.getEmail())
                .firstName(passengerRequestDTO.getFirstName())
                .lastName(passengerRequestDTO.getLastName())
                .phoneNumber(passengerRequestDTO.getPhoneNumber())
                .build();
        passengerRepository.save(newPassenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPassenger);
    }
    public ResponseEntity<Optional<Passenger>> deletePassenger(@PathVariable Long id) {
        Optional<Passenger> passenger= passengerRepository.findById(id);
        if(passenger.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        passengerRepository.deleteById(id);
        return ResponseEntity.ok(passenger);
    }


}
