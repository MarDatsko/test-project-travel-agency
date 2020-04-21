package com.travelagency.service.impl;

import com.travelagency.entity.Visa;
import com.travelagency.repository.VisaRepository;
import com.travelagency.service.VisaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisaServiceImpl implements VisaService {

    @Autowired
    private VisaRepository visaRepository;

    @Override
    public Optional<Visa> getVisa(Long id) {
        return visaRepository.findById(id);
    }

    @Override
    public List<Visa> findAll() {
        return (List<Visa>) visaRepository.findAll();
    }
}
