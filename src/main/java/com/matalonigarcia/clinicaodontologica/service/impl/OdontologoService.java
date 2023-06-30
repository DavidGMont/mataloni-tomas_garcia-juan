package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.matalonigarcia.clinicaodontologica.repository.OdontologoRepository;
import com.matalonigarcia.clinicaodontologica.service.IOdontologoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OdontologoService implements IOdontologoService {
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
        LOGGER.info("👨‍⚕️ Se registró correctamente al odontólogo: {}.", odontologoDto);
        return odontologoDto;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto;
        if (odontologoBuscado != null) {
            odontologoDto = mapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("👨‍⚕️ Se ha encontrado al odontólogo con ID {}: {}.", id, odontologoDto);
        } else {
            LOGGER.error("🛑 El odontólogo solicitado con ID {} no está registrado en la base de datos.", id);
            throw new ResourceNotFoundException("🛑 Lastimosamente, el odontólogo al que buscas ya no se encuentra "
                    .concat("con nosotros ¿y si buscas uno nuevo?"));
        }
        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarTodosLosOdontologos() {
        List<OdontologoDto> odontologoDtos = odontologoRepository
                .findAll()
                .stream()
                .map(odontologo -> mapper.convertValue(odontologo, OdontologoDto.class))
                .toList();
        LOGGER.info("🦷 Listando todos los odontólogos: {}.", odontologoDtos);
        return odontologoDtos;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoActualizadoDto;
        if (odontologoAActualizar != null) {
            odontologoAActualizar = odontologo;
            odontologoActualizadoDto = registrarOdontologo(odontologoAActualizar);
            LOGGER.warn("🛑 El odontólogo con ID {} ha sido actualizado: {}.", odontologo.getId(),
                    odontologoActualizadoDto);
        } else {
            LOGGER.error("🛑 No es posible actualizar el odontólogo porque no está registrado en la base de datos.");
            throw new ResourceNotFoundException("🛑 No es posible actualizar a un odontólogo que no existe desde el "
                    .concat("principio, por favor registra a tu odontólogo, luego, si quieres actualizarlo, ")
                    .concat("ven con su ID."));
        }
        return odontologoActualizadoDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("🚮 Se ha eliminado al odontólogo con ID {}.", id);
        } else {
            LOGGER.error("🛑 Se intentó eliminar el odontólogo con ID {}, pero este no existe en la base de datos.",
                    id);
            throw new ResourceNotFoundException("🛑 Sabemos que no te agrada el odontólogo con ID "
                    .concat(id.toString()).concat(", pero él solamente existe en nuestros recuerdos, ")
                    .concat("de nuestra base de datos se retiró hace algún tiempo..."));
        }
    }
}
