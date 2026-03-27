package com.hms.frontend.dto.signup;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {

    private String username;
    private String password;
    private String email;
}
