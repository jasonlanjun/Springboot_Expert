package com.junlan.service;

import com.junlan.domain.CityEntity;

import java.util.List;

public interface CityServiceInt {

    List<CityEntity> findAll();

    List<String> findCityByState(String state);

}
