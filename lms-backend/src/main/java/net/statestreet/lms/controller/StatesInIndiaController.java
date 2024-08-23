package net.statestreet.lms.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.statestreet.lms.entity.StatesInIndia;

import net.statestreet.lms.service.StatesInIndiaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/statesinindia")
public class StatesInIndiaController {

	@Autowired
    private StatesInIndiaService statesInIndiaService;
	
	// REST API for getting States from the DB
    @GetMapping("/states")
    public ResponseEntity<List<StatesInIndia>> getAllStatesInIndia(){
        List<StatesInIndia> states = statesInIndiaService.getAllStatesInIndia();
        return ResponseEntity.ok(states);
    }

}
