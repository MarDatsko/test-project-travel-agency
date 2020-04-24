package com.travelagency.dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CountryDto {

    private Long id;
    private String name;
    private VisaDto visa;
}
