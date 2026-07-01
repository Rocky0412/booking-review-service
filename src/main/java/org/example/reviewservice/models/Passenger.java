package org.example.reviewservice.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="passengers")
@Builder
public class Passenger extends BaseModel{
     private String firstName;
     private String lastName;
     private String email;
     private String phoneNumber;
     @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
     List<Booking> bookings;



     public void addBooking(Booking booking) {
          if (bookings == null) {
               bookings = new ArrayList<>();
          }
          this.bookings.add(booking);
          booking.setPassenger(this);
     }

}
