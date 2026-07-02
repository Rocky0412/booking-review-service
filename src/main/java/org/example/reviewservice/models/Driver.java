package org.example.reviewservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel {
    String name;
    String surname;
    String LicenceNumber;
    private String password;
    @OneToMany(mappedBy = "driver", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Booking> bookings;
    public void addBooking(Booking booking) {
        if(this.bookings == null) {
            this.bookings = new ArrayList<>();
        }
        this.bookings.add(booking);
        booking.setDriver(this);
    }
}
