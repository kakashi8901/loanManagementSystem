package net.statestreet.lms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import net.statestreet.lms.entity.StatesInIndia;
import net.statestreet.lms.repository.StatesInIndiaRepository;
import net.statestreet.lms.service.StatesInIndiaService;

@Service
public class StatesInIndiaServiceImpl implements StatesInIndiaService{

    @Autowired
    private StatesInIndiaRepository stateInIndiaRepository;

    @Cacheable("statesInIndiaCaffeineCache")
    public List<StatesInIndia> getAllStatesInIndia() {
    	return stateInIndiaRepository.findAll();
    	

    }
}
