package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.DomicilioDto;
import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.repository.IDao;
import com.matalonigarcia.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final IDao<Paciente> pacienteIDao;
    private final ObjectMapper mapper;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ObjectMapper mapper) {
        this.pacienteIDao = pacienteIDao;
        this.mapper = mapper;
    }

    @Override
    public PacienteDto registrarPaciente(Paciente paciente) {
        PacienteDto pacienteDto = mapper.convertValue(pacienteIDao.guardar(paciente), PacienteDto.class);
        pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
        return pacienteDto;
    }

    @Override
    public PacienteDto buscarPacientePorId(int id) {
        PacienteDto pacienteDto = mapper.convertValue(pacienteIDao.buscarPorId(id), PacienteDto.class);
        pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(pacienteIDao.buscarPorId(id).getDomicilio()));
        return pacienteDto;
    }

    @Override
    public List<PacienteDto> listarTodosLosPacientes() {
        return pacienteIDao.listarTodos().stream()
                .map(paciente -> {
                    PacienteDto pacienteDto = mapper.convertValue(paciente, PacienteDto.class);
                    pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
                    return pacienteDto;
                })
                .toList();
    }

    @Override
    public PacienteDto buscarPacientePorDni(String dni) {
        PacienteDto pacienteDto = mapper.convertValue(pacienteIDao.buscarPorCriterio(dni), PacienteDto.class);
        pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(pacienteIDao.buscarPorCriterio(dni).getDomicilio()));
        return pacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) {
        PacienteDto pacienteDto = mapper.convertValue(pacienteIDao.actualizar(paciente), PacienteDto.class);
        pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
        return pacienteDto;
    }

    @Override
    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }
}
