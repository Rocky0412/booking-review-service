package org.example.reviewservice.RequestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
public class PassengerAuthDTO {

    private String username;
    private String password;
}
