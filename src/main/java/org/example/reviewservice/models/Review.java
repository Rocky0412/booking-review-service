package org.example.reviewservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking-review")
public class Review  extends BaseModel{

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Double rating;


}
