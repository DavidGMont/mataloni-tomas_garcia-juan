package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.dto.TurnoDto;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.repository.IDao;
import com.matalonigarcia.clinicaodontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private final IDao<Turno> turnoIDao;
    private final ObjectMapper mapper;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao, ObjectMapper objectMapper) {
        this.turnoIDao = turnoIDao;
        this.mapper = objectMapper;
    }

    @Override
    public TurnoDto registrarTurno(Turno turno) {
        TurnoDto turnoDto = mapper.convertValue(turnoIDao.guardar(turno), TurnoDto.class);
        turnoDto.setPacienteDto(PacienteDto.fromPaciente(turno.getPaciente()));
        turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turno.getOdontologo()));
        return turnoDto;
    }

    @Override
    public TurnoDto buscarTurnoPorId(int id) {
        TurnoDto turnoDto = mapper.convertValue(turnoIDao.buscarPorId(id), TurnoDto.class);
        turnoDto.setPacienteDto(PacienteDto.fromPaciente(turnoIDao.buscarPorId(id).getPaciente()));
        turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turnoIDao.buscarPorId(id).getOdontologo()));
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodosLosTurnos() {
        return turnoIDao.listarTodos().stream()
                .map(turno -> {
                    TurnoDto turnoDto = mapper.convertValue(turno, TurnoDto.class);
                    turnoDto.setPacienteDto(PacienteDto.fromPaciente(turno.getPaciente()));
                    turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turno.getOdontologo()));
                    return turnoDto;
                })
                .toList();
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        TurnoDto turnoDto = mapper.convertValue(turnoIDao.actualizar(turno), TurnoDto.class);
        turnoDto.setPacienteDto(PacienteDto.fromPaciente(turno.getPaciente()));
        turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turno.getOdontologo()));
        return turnoDto;
    }

    @Override
    public void eliminarTurno(int id) {
        turnoIDao.eliminar(id);
    }
}
