package org.example.reviewservice.RequestDTO;

import lombok.*;
import org.example.entityservices.models.Booking;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private String content;
    private Double rating;
}
