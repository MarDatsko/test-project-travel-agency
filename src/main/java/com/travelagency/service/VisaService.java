package com.travelagency.service;

import com.travelagency.entity.Visa;

import java.util.List;

public interface VisaService {

    Visa getVisaById(Long id);

    List<Visa> getAllVisas();

    void deleteVisaById(Long id);

    Visa createVisa(Visa visa);
}
