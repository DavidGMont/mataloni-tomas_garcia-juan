package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.repository.OdontologoRepository;
import com.matalonigarcia.clinicaodontologica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ObjectMapper mapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    @Override
    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
        OdontologoDto odontologoDto = mapper.convertValue(odontologoRepository.save(odontologo), OdontologoDto.class);
        LOGGER.info("👨‍⚕️ Se guardó al odontólogo: {}", odontologoDto);
        return odontologoDto;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;
        if (odontologoBuscado != null) {
            odontologoDto = mapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("👨‍⚕️ Se ha encontrado al odontólogo con ID {}: {}", id, odontologoDto);
        } else
            LOGGER.info("🛑 El odontólogo con id {} no está registrado en la base de datos", id);
        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarTodosLosOdontologos() {
        List<OdontologoDto> odontologoDtos = odontologoRepository
                .findAll()
                .stream()
                .map(odontologo -> mapper.convertValue(odontologo, OdontologoDto.class))
                .toList();
        LOGGER.info("🦷 Listando todos los odontólogos: {}", odontologoDtos);
        return odontologoDtos;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoActualizadoDto = null;
        if (odontologoAActualizar != null) {
            odontologoAActualizar = odontologo;
            odontologoActualizadoDto = registrarOdontologo(odontologoAActualizar);
            LOGGER.warn("🛑 El odontólogo con ID {} ha sido actualizado: {}", odontologo.getId(), odontologoActualizadoDto);
        } else
            LOGGER.warn("🛑 No es posible actualizar el odontólogo porque no está registrado en la base de datos");
        return odontologoActualizadoDto;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if (odontologoRepository.existsById(id))
            odontologoRepository.deleteById(id);
        LOGGER.info("🚮 Se ha eliminado al odontólogo con ID: {}", id);
    }
}
