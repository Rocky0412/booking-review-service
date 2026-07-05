package org.example.reviewservice.Configuartion.Helper;

import org.example.entityservices.models.Passenger;
import org.example.entityservices.repositories.PassengerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PassengerUserDetailsImp implements UserDetailsService {

    private final PassengerRepository passengerRepository;
    public PassengerUserDetailsImp(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Passenger> passenger = Optional.ofNullable(passengerRepository.findByEmail(username));
        if (passenger.isPresent()) {
            return new PassengerUserDetails(passenger.get());
        }
        throw new UsernameNotFoundException(username);

    }
}
