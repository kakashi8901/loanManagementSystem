package net.statestreet.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.statestreet.lms.entity.StatesInIndia;

@Repository
public interface StatesInIndiaRepository extends JpaRepository <StatesInIndia, Long> {
	
	
}