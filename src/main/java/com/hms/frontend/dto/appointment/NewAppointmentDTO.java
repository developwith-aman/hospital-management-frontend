package com.hms.frontend.dto.appointment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewAppointmentDTO {

    private int doctorId;
    private Long patientId;
    private LocalDateTime appointment_time;
    private String reason;
}
