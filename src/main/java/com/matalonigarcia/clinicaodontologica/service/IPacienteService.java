package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    PacienteDto registrarPaciente(Paciente paciente);

    PacienteDto buscarPacientePorId(int id);

    PacienteDto buscarPacientePorDni(String dni);

    List<PacienteDto> listarTodosLosPacientes();

    PacienteDto actualizarPaciente(Paciente paciente);

    void eliminarPaciente(int id);
}
