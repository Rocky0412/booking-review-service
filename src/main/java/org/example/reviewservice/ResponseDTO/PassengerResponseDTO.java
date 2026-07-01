package org.example.reviewservice.ResponseDTO;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    public  final String maskPhoneNumber(String PhoneNumber) {
        return "****" + PhoneNumber.substring(4);
    }

}
