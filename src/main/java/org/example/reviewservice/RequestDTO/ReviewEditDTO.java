package org.example.reviewservice.RequestDTO;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReviewEditDTO {
    private String comment;
}
