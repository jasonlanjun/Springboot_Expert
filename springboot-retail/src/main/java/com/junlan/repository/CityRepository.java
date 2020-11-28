package com.junlan.repository;

import com.junlan.domain.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query(value = "SELECT city_name FROM CITY_ENTITY WHERE state_name = ?1 ORDER BY city_name", nativeQuery = true)
    List<String> findCityByState(String state);

}
