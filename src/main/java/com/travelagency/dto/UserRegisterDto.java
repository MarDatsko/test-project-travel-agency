package com.travelagency.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
