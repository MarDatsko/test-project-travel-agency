package com.travelagency.dto;

import com.travelagency.enums.UserRole;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
}
