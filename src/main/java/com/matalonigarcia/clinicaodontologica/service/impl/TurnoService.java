package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.dto.TurnoDto;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.exceptions.BadRequestException;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.matalonigarcia.clinicaodontologica.repository.OdontologoRepository;
import com.matalonigarcia.clinicaodontologica.repository.PacienteRepository;
import com.matalonigarcia.clinicaodontologica.repository.TurnoRepository;
import com.matalonigarcia.clinicaodontologica.service.ITurnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TurnoService implements ITurnoService {
    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final ObjectMapper mapper;

    @Autowired
    public TurnoService(
            TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService,
            PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository, ObjectMapper objectMapper
    ) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.mapper = objectMapper;
    }

    @Override
    public TurnoDto registrarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoDto;
        boolean coincidenciaPaciente = pacienteRepository.existsById(turno.getPaciente().getId());
        boolean coincidenciaOdontologo = odontologoRepository.existsById(turno.getOdontologo().getId());
        if (!coincidenciaPaciente || !coincidenciaOdontologo) {
            if (!coincidenciaPaciente && !coincidenciaOdontologo) {
                LOGGER.error("🛑 El paciente solicitado y el odontólogo solicitado no están registrados en la "
                        .concat("base de datos."));
                throw new BadRequestException("💀 ¡Error grave! Ni el paciente y ni el odontólogo con los que quieres "
                        .concat("agendar tu turno están registralos en nuestra base de datos, regístralos y ")
                        .concat("vuelve de nuevo a este módulo."));
            } else if (!coincidenciaPaciente) {
                LOGGER.error("🛑 El paciente solicitado no está registrado en la base de datos.");
                throw new BadRequestException("🥺 El paciente al que le estás intentando agendar un turno no se "
                        .concat("encuentra registrado en nuestra base de datos, no puedes dejar al odontólogo ")
                        .concat("solito ¡Le daría frío!"));
            } else {
                LOGGER.error("🛑 El odontólogo solicitado no está registrado en la base de datos.");
                throw new BadRequestException("🥺 El odontólogo al que le estás intentando agendar un turno no se "
                        .concat("encuentra registrado en nuestra base de datos, no puedes dejar al paciente ")
                        .concat("solito ¡Eso no es ético y profesional!"));
            }
        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("️🎫 Se registró correctamente al turno: {}.", turnoDto);
        }
        return turnoDto;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto;
        if (turnoBuscado != null) {
            turnoDto = mapper.convertValue(turnoBuscado, TurnoDto.class);
            turnoDto.setPacienteDto(PacienteDto.fromPaciente(turnoBuscado.getPaciente()));
            turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turnoBuscado.getOdontologo()));
            LOGGER.info("🎫 Se ha encontrado al turno con ID {}: {}.", id, turnoDto);
        } else {
            LOGGER.error("🛑 El turno solicitado con ID {} no está registrado en la base de datos.", id);
            throw new ResourceNotFoundException("🛑 ¡Estás en problemas! Aquí entre nosotros, te cuento que el "
                    .concat("turno con ID ").concat(id.toString()).concat(" no existe ¿estás seguro que ")
                    .concat("reservaste cita?"));
        }
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodosLosTurnos() {
        List<TurnoDto> turnoDtos = turnoRepository
                .findAll()
                .stream()
                .map(turno -> {
                    TurnoDto turnoDto = mapper.convertValue(turno, TurnoDto.class);
                    turnoDto.setPacienteDto(PacienteDto.fromPaciente(turno.getPaciente()));
                    turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turno.getOdontologo()));
                    return turnoDto;
                })
                .toList();
        LOGGER.info("🔢 Listando todos los turnos: {}.", turnoDtos);
        return turnoDtos;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) throws BadRequestException, ResourceNotFoundException {
        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto;
        if (turnoAActualizar != null) {
            turnoAActualizar = turno;
            turnoActualizadoDto = registrarTurno(turnoAActualizar);
            LOGGER.warn("🛑 El turno con ID {} ha sido actualizado: {}.", turnoAActualizar.getId(),
                    turnoActualizadoDto);
        } else {
            LOGGER.error("🛑 No es posible actualizar el turno porque no está registrado en la base de datos.");
            throw new ResourceNotFoundException("🛑 No es posible actualizar un turno que no existe desde el "
                    .concat("principio, por favor registra a tu turno, luego, si quieres actualizarlo, ")
                    .concat("ven con su ID."));
        }
        return turnoActualizadoDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (turnoRepository.existsById(id)) {
            turnoRepository.deleteById(id);
            LOGGER.warn("🚮 Se ha eliminado el turno con ID: {}.", id);
        } else {
            LOGGER.error("🛑 Se intentó eliminar el turno con ID {}, pero no existe en la base de datos.", 1);
            throw new ResourceNotFoundException("🛑 El turno que intentas eliminar te dijo ¡Te aviso, te anuncio, "
                    .concat("que hoy renuncio! Porque en nuestra base de datos ya no figura..."));
        }
    }
}
