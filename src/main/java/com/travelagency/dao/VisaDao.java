package com.travelagency.dao;

import com.travelagency.entity.Visa;

import java.util.List;

public interface VisaDao {

    Visa getVisaById(Long id);

    List<Visa> getAllVisas();

    void deleteVisaById(Long id);

    Visa createVisa(Visa visa);
}
