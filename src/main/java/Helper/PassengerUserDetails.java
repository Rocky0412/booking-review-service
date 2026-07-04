package Helper;

import org.example.reviewservice.RequestDTO.PassengerRequestDTO;
import org.example.reviewservice.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;




public class PassengerUserDetails implements UserDetails {

    final private Passenger passenger;
    public PassengerUserDetails(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
      return passenger.getPassword();
    }

    @Override
    public String getUsername() {
        return passenger.getEmail();
    }
}
