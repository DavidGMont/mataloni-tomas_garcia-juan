package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.DomicilioDto;
import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.matalonigarcia.clinicaodontologica.repository.PacienteRepository;
import com.matalonigarcia.clinicaodontologica.service.IPacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PacienteService implements IPacienteService {
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
        LOGGER.info("️🚹 Se registró correctamente al paciente: {}.", pacienteDto);
        return pacienteDto;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto;
        if (pacienteBuscado != null) {
            pacienteDto = mapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(pacienteBuscado.getDomicilio()));
            LOGGER.info("🚹 Se ha encontrado al paciente con ID {}: {}.", id, pacienteDto);
        } else {
            LOGGER.error("🛑 El paciente solicitado con ID {} no está registrado en la base de datos.", id);
            throw new ResourceNotFoundException("🛑 Lastimosamente, el paciente al que buscas ya no se encuentra "
                    .concat("con nosotros ¿y si buscas uno nuevo?"));
        }
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
        LOGGER.info("👨‍👩‍👦‍👦 Listando todos los pacientes: {}.", pacienteDtos);
        return pacienteDtos;
    }

    @Override
    public PacienteDto buscarPacientePorDni(String dni) throws ResourceNotFoundException {
        Paciente pacienteBuscado = pacienteRepository.findByDni(dni);
        PacienteDto pacienteDto;
        if (pacienteBuscado != null) {
            pacienteDto = mapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(pacienteBuscado.getDomicilio()));
            LOGGER.info("🚹 Se ha encontrado al paciente con DNI número {}: {}.", dni, pacienteDto);
        } else {
            LOGGER.error("🛑 El paciente solicitado con DNI {} no está registrado en la base de datos.", dni);
            throw new ResourceNotFoundException("🛑 Ese DNI que pasaste no lo tenemos en la base de datos "
                    .concat("¿y si lo verificas e intentas de nuevo?"));
        }
        return pacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto;
        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteActualizadoDto = registrarPaciente(pacienteAActualizar);
            LOGGER.warn("🛑 El paciente con ID {} ha sido actualizado: {}.", pacienteAActualizar.getId(),
                    pacienteActualizadoDto);
        } else {
            LOGGER.error("🛑 No es posible actualizar el paciente porque no está registrado en la base de datos.");
            throw new ResourceNotFoundException("🛑 No es posible actualizar a un paciente que no existe desde el "
                    .concat("principio, por favor registra a tu paciente, luego, si quieres actualizarlo, ")
                    .concat("ven con su ID."));
        }
        return pacienteActualizadoDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("🚮 Se ha eliminado al paciente con ID {}.", id);
        } else {
            LOGGER.error("🛑 Se intentó eliminar el paciente con ID {}, pero este no existe en la base de datos.", id);
            throw new ResourceNotFoundException("🛑 No puedes eliminar al paciente con ID ".concat(id.toString())
                    .concat(" porque ya no existe, recuerda que se retiró a sí mismo hace algún tiempo..."));
        }
    }
}