package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.entity.Turno;

import java.util.List;

public interface ITurnoService {

    Turno registrarTurno(Turno turno);

    Turno buscarTurnoPorId(int id);

    List<Turno> listarTodosLosTurnos();

    Turno actualizarTurno(Turno turno);

    void eliminarTurno(int id);
}
