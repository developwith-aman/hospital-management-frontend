package com.hms.frontend.dto.email;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailUpdateResponseDTO {

    private Long patientID;
    private String patientName;
    private String previousEmail;
    private String updatedEmail;
}
