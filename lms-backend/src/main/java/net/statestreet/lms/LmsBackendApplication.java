package net.statestreet.lms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;




@SpringBootApplication
@EnableCaching
public class LmsBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(LmsBackendApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}


}
