package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarTodosLosPacientes();

    Paciente buscarPacientePorDni(String dni);
}
