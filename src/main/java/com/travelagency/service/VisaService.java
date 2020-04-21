package com.travelagency.service;

import com.travelagency.entity.Visa;

import java.util.List;
import java.util.Optional;

public interface VisaService {
    Optional<Visa> getVisa(Long id);
    List<Visa> findAll();
}
