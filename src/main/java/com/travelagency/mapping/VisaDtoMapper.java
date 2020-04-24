package com.travelagency.mapping;

import com.travelagency.dto.VisaDto;
import com.travelagency.entity.Visa;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class VisaDtoMapper extends AbstractConverter<Visa, VisaDto> {

    @Override
    protected VisaDto convert(Visa visa) {
        VisaDto visaDto = new VisaDto();
        visaDto.setId(visa.getId());
        visaDto.setName(visa.getName());
        return visaDto;
    }
}
