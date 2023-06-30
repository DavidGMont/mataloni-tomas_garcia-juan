package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.dto.TurnoDto;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.exceptions.BadRequestException;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoDto registrarTurno(Turno turno) throws BadRequestException, ResourceNotFoundException;

    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;

    List<TurnoDto> listarTodosLosTurnos();

    TurnoDto actualizarTurno(Turno turno) throws BadRequestException, ResourceNotFoundException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
