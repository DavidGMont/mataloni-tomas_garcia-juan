package com.matalonigarcia.clinicaodontologica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ClinicaodontologicaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaodontologicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClinicaodontologicaApplication.class, args);
		LOGGER.info("🦷 La Clínica se está abriendo...");
	}

}
