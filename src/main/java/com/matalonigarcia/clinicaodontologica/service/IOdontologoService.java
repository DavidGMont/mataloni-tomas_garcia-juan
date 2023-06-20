package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto registrarOdontologo(Odontologo odontologo);

    OdontologoDto buscarOdontologoPorId(Long id);

    List<OdontologoDto> listarTodosLosOdontologos();

    OdontologoDto actualizarOdontologo(Odontologo odontologo);

    void eliminarOdontologo(Long id);
}
