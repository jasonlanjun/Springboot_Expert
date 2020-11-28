package com.junlan.service;

import com.junlan.domain.CityEntity;
import com.junlan.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityServiceInt {

    @Autowired
    private CityRepository cityRepository;

    public List<CityEntity> findAll() {
        return cityRepository.findAll();
    }

    public List<String> findCityByState(String state) {
        return cityRepository.findCityByState(state);
    }
}
