package com.matalonigarcia.clinicaodontologica.repository;

import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByDni(String dni);
}
