package com.matalonigarcia.clinicaodontologica.service.impl;

import com.matalonigarcia.clinicaodontologica.dto.TurnoDto;
import com.matalonigarcia.clinicaodontologica.entity.Domicilio;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.exceptions.BadRequestException;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class TurnoServiceTest {
    private static Turno turno;
    private static Paciente paciente;
    private static Odontologo odontologo;
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    TurnoServiceTest(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @BeforeAll
    static void prepararDomicilioPacienteOdontologoYTurno() {
        paciente = new Paciente(
                "Darla", "Sherman", "15477599",
                LocalDate.of(2023, 7, 10),
                new Domicilio("Calle Wallaby", 42, "Sídney", "Nueva Gales del Sur")
        );

        odontologo = new Odontologo("54774-877554 AUS", "Philip", "Sherman");

        turno = new Turno(LocalDateTime.of(2023, 7, 10, 10, 30), paciente, odontologo);
    }

    @Test
    @Order(1)
    void deberiaRegistrarUnTurnoUsandoElPacienteYOdontologoExistentesEnLaBaseDeDatos() throws BadRequestException {
        pacienteService.registrarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);

        TurnoDto turnoRegistrado = turnoService.registrarTurno(turno);

        Assertions.assertEquals(1L, turnoRegistrado.getId());
        Assertions.assertEquals(turno.getFechaHora(), turnoRegistrado.getFechaHora());
    }

    @Test
    @Order(2)
    void deberiaEliminarElTurnoConId1() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(3)
    void deberiaValidarQueLaListaDeTurnosEstaVacia() {
        List<TurnoDto> turnoDtoList = turnoService.listarTodosLosTurnos();
        Assertions.assertTrue(turnoDtoList.isEmpty());
    }

    @Test
    @Order(4)
    void cuandoSePaseUnaFechaPasada_deberiaLanzarConstraintViolationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> turnoService.registrarTurno(
                new Turno(LocalDateTime.of(2023, 5, 30, 15, 30),
                        paciente, odontologo)
        ));
    }

    @Test
    @Order(5)
    void cuandoSeBusqueUnIdQueNoEstaRegistrado_deberiaLanzarResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurnoPorId(1L));
    }
}