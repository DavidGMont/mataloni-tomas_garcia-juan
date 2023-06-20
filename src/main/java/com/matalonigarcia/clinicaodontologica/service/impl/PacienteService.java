package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.DomicilioDto;
import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.repository.PacienteRepository;
import com.matalonigarcia.clinicaodontologica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ObjectMapper mapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    @Override
    public PacienteDto registrarPaciente(Paciente paciente) {
        PacienteDto pacienteDto = mapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class);
        pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
        LOGGER.info("️🚹 Se guardó al paciente: {}", pacienteDto);
        return pacienteDto;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            pacienteDto = mapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(pacienteBuscado.getDomicilio()));
            LOGGER.info("🚹 Se ha encontrado al paciente con ID {}: {}", id, pacienteDto);
        } else
            LOGGER.info("🛑 El paciente con ID {} no está registrado en la base de datos", id);
        return pacienteDto;
    }

    @Override
    public List<PacienteDto> listarTodosLosPacientes() {
        List<PacienteDto> pacienteDtos = pacienteRepository
                .findAll()
                .stream()
                .map(paciente -> {
                    PacienteDto pacienteDto = mapper.convertValue(paciente, PacienteDto.class);
                    pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
                    return pacienteDto;
                })
                .toList();
        LOGGER.info("👨‍👩‍👦‍👦 Listando todos los pacientes: {}", pacienteDtos);
        return pacienteDtos;
    }

    @Override
    public PacienteDto buscarPacientePorDni(String dni) {
        Paciente pacienteBuscado = pacienteRepository.findByDni(dni);
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            pacienteDto = mapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(pacienteBuscado.getDomicilio()));
            LOGGER.info("🚹 Se ha encontrado al paciente con DNI número {}: {}", dni, pacienteBuscado);
        } else
            LOGGER.info("🛑 El paciente con DNI {} no está registrado en la base de datos", dni);
        return pacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) {
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;
        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteActualizadoDto = registrarPaciente(pacienteAActualizar);
            LOGGER.warn("🛑 El paciente con ID {} ha sido actualizado: {}", pacienteAActualizar.getId(), pacienteActualizadoDto);
        } else
            LOGGER.warn("🛑 No es posible actualizar el paciente porque no está registrado en la base de datos");
        return pacienteActualizadoDto;
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (pacienteRepository.existsById(id))
            pacienteRepository.deleteById(id);
        LOGGER.info("🚮 Se ha eliminado al paciente con ID: {}", id);
    }
}