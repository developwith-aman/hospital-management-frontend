package com.hms.frontend.dto.bill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillRequestDTO {

    private Long patientId;
    private int doctorId;
    private double consultationFee;
    private double medicineCost;
    private double bedCharges;
}
