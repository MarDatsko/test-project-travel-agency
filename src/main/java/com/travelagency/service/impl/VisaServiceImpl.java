package com.travelagency.service.impl;

import com.travelagency.dao.VisaDao;
import com.travelagency.entity.Visa;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class VisaServiceImpl implements VisaService {

    @Autowired
    private VisaDao visaDao;

    @Override
    public Visa getVisaById(Long id) {
        Visa visaById = visaDao.getVisaById(id);
        if (visaById == null) {
            throw new ResourceNotFoundException(id.toString());
        }
        return visaById;
    }

    @Override
    public List<Visa> getAllVisas() {
        List<Visa> allVisas = visaDao.getAllVisas();
        if (allVisas == null || allVisas.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any visas");
        }
        return allVisas;
    }

    @Override
    public void deleteVisaById(Long id) {
        visaDao.deleteVisaById(id);
    }

    @Override
    public Visa createVisa(Visa visa) {
        visaDao.createVisa(visa);
        return visa;
    }
}
