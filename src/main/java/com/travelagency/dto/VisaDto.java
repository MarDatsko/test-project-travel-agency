package com.travelagency.dto;

import com.travelagency.entity.Visa;
import java.util.List;
import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class VisaDto {
    private Long id;
    private String name;
    private List<CountryDto> countries;

    public VisaDto mapVisaToVisaDto (Visa visa){
        VisaDto visaDto = new VisaDto();
        visaDto.setId(visa.getId());
        visaDto.setName(visa.getName());
        return visaDto;
    }
}
