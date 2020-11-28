package com.junlan.service;

import com.junlan.domain.StateEntity;
import com.junlan.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateServiceInt {

    @Autowired
    private StateRepository stateRepository;

    public List<StateEntity> findAll() {
        return stateRepository.findAll();
    }
}
