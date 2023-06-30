package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto registrarOdontologo(Odontologo odontologo);

    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;

    List<OdontologoDto> listarTodosLosOdontologos();

    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
