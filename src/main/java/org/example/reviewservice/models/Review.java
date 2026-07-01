package org.example.reviewservice.models;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
@Inheritance(strategy = InheritanceType.JOINED)
public class Review  extends BaseModel{

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Double rating;


}
