package com.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DateAndCountryDto {

    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date firstDate;

    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date secondDate;
}
