package com.hms.frontend.service;

import com.hms.frontend.api.ApiClient;
import com.hms.frontend.dto.bill.BillRequestDTO;
import com.hms.frontend.dto.bill.BillResponseDTO;

import java.util.Arrays;
import java.util.List;

public class BillService {


    public List<BillResponseDTO> generateBill(BillRequestDTO billRequestDTO) {

        BillResponseDTO[] response = ApiClient.postWithToken(
                "/bills/generate",
                billRequestDTO,
                BillResponseDTO[].class
        );

        if (response == null) {
            throw new IllegalArgumentException("Couldn't generate Bill");
        }
        return Arrays.asList(response);
    }

}
