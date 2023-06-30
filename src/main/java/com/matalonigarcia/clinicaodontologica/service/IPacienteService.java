package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    PacienteDto registrarPaciente(Paciente paciente);

    PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException;

    PacienteDto buscarPacientePorDni(String dni) throws ResourceNotFoundException;

    List<PacienteDto> listarTodosLosPacientes();

    PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
