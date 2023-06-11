package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.dto.TurnoDto;
import com.matalonigarcia.clinicaodontologica.entity.Turno;

import java.util.List;

public interface ITurnoService {

    TurnoDto registrarTurno(Turno turno);

    TurnoDto buscarTurnoPorId(int id);

    List<TurnoDto> listarTodosLosTurnos();

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(int id);
}
