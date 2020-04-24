package com.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DateAndCountryDto {

    private Long id;
    private String name;
    private Date firstDate;
    private Date secondDate;
}
