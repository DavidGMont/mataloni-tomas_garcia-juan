package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente registrarPaciente(Paciente paciente);

    Paciente buscarPacientePorId(int id);

    Paciente buscarPacientePorDni(String dni);

    List<Paciente> listarTodosLosPacientes();

    Paciente actualizarPaciente(Paciente paciente);

    void eliminarPaciente(int id);
}
