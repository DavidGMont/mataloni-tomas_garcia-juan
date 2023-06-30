package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.DomicilioDto;
import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Domicilio;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class PacienteServiceTest {
    private static Paciente paciente;
    private final PacienteService pacienteService;
    private final ObjectMapper mapper;

    @Autowired
    PacienteServiceTest(PacienteService pacienteService, ObjectMapper mapper) {
        this.pacienteService = pacienteService;
        this.mapper = mapper;
    }

    @BeforeAll
    static void prepararPacienteYDomicilio() {
        Domicilio domicilio = new Domicilio("Calle Wallaby", 42, "Sídney", "Nueva Gales del Sur");
        paciente = new Paciente(
                "Darla", "Sherman", "15477599",
                LocalDate.of(2023, 7, 10), domicilio
        );
    }

    @Test
    @Order(1)
    void deberiaRegistrarUnPacienteYSuDomicilioEnLaBaseDeDatos() {
        PacienteDto pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        Assertions.assertEquals(1L, pacienteRegistrado.getId());
        Assertions.assertEquals(
                "Darla Sherman",
                pacienteRegistrado.getNombre().concat(" ").concat(pacienteRegistrado.getApellido()));
        Assertions.assertEquals(1L, pacienteRegistrado.getDomicilioDto().getId());
        Assertions.assertEquals("Sídney", pacienteRegistrado.getDomicilioDto().getLocalidad());
        LOGGER.info("✅ Si estás viendo este mensaje es porque se guardó el paciente y su domicilio exitosamente. "
                .concat("¡Por fin tenemos un cliente! 😃"));
    }

    @Test
    @Order(2)
    void deberiaEncontrarUnPacienteYSuDomicilioPorElId1EnLaBaseDeDatos() throws ResourceNotFoundException {
        PacienteDto pacienteEncontrado = pacienteService.buscarPacientePorId(1L);

        Assertions.assertNotNull(pacienteEncontrado);
        Assertions.assertEquals(1L, pacienteEncontrado.getId());
        Assertions.assertEquals(paciente.getDni(), pacienteEncontrado.getDni());
        Assertions.assertEquals(paciente.getDomicilio().getNumero(), pacienteEncontrado.getDomicilioDto().getNumero());
        LOGGER.info("✅ Si estás viendo este mensaje es porque se encontró al paciente y su domicilio exitosamente. "
                .concat("Nuestro cliente nos es fiel 🤩"));
    }

    @Test
    @Order(3)
    void deberiaActualizarUnPacienteYSuDomicilioExistentesEnLaBaseDeDatos() throws ResourceNotFoundException {
        PacienteDto pacienteParaActualizar = pacienteService.buscarPacientePorId(1L);
        pacienteParaActualizar.setNombre("Willy");
        pacienteParaActualizar.setApellido("Wonka");
        pacienteParaActualizar.setDni("1144668");
        DomicilioDto domicilioParaActualizar = pacienteParaActualizar.getDomicilioDto();
        domicilioParaActualizar.setCalle("Pudding Lane");
        domicilioParaActualizar.setNumero(10);
        domicilioParaActualizar.setLocalidad("Birmingham");
        domicilioParaActualizar.setProvincia("Tierras Medias Occidentales");

        Paciente pacienteParaConvertir = mapper.convertValue(pacienteParaActualizar, Paciente.class);
        pacienteParaConvertir.setDomicilio(mapper.convertValue(domicilioParaActualizar, Domicilio.class));

        PacienteDto pacienteRecienActualizado = pacienteService.actualizarPaciente(pacienteParaConvertir);
        DomicilioDto domicilioRecienActualizado = pacienteRecienActualizado.getDomicilioDto();

        Assertions.assertEquals(pacienteParaActualizar.getId(), pacienteRecienActualizado.getId());
        Assertions.assertEquals(
                pacienteParaActualizar.getNombre().concat(" ").concat(pacienteParaActualizar.getApellido()),
                pacienteRecienActualizado.getNombre().concat(" ").concat(pacienteRecienActualizado.getApellido())
        );
        Assertions.assertEquals(domicilioParaActualizar.getLocalidad(), domicilioRecienActualizado.getLocalidad());
        LOGGER.info("✅ Si estás viendo este mensaje es porque se actualizó al paciente y su domicilio exitosamente. "
                .concat("¿Con que nuestro cliente no era quien decía ser? No importa, sigue siendo un cliente 🥰"));
    }

    @Test
    @Order(4)
    void deberiaValidarQueLaListaDePacienteTieneSoloUnRegistro() {
        Assertions.assertEquals(1, pacienteService.listarTodosLosPacientes().size());
        LOGGER.info("✅ Si estás viendo este mensaje es porque solo se encontró un paciente registrado. "
                .concat("Este cliente, aunque cambia, nos sigue siendo fiel 😎"));
    }

    @Test
    @Order(5)
    void deberiaEliminarElPacienteConId1() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(paciente.getId());

        Assertions.assertTrue(pacienteService.listarTodosLosPacientes().isEmpty());
        LOGGER.info("✅ Si estás viendo este mensaje es porque se eliminó exitosamente al único paciente que había. "
                .concat("¿Y la clientela? ¿Ahora cómo vamos a facturar? 😫"));
    }

    @Test
    @Order(6)
    void deberiaLanzarResourceNotFoundExceptionAlBuscarElPacienteEliminado() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPacientePorId(1L));
        LOGGER.info("✅ Si estás viendo este mensaje es porque se lanzó una excepción por buscar algo que no existe. "
                .concat("No seas así y busca cosas que sí existan 💅"));
    }

    @Test
    @Order(7)
    void deberiaLanzarConstraintViolationExceptionPorRegistrarUnPacienteConDatosInvalidosYSinDomicilio() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> pacienteService.registrarPaciente(
                new Paciente(
                        "Qué tal un número: 1",
                        "Qué tal dos: 22",
                        "Qué tal un DNI en letras",
                        LocalDate.of(2023, 5, 31),
                        new Domicilio(
                                "Una calle mágica 🧁",
                                475,
                                "👺🔪",
                                "👽🛸"
                        )
                )
        ));
        LOGGER.info("✅ Si estás viendo este mensaje es porque se lanzó una excepción por registrar mal a un paciente"
                .concat(" y su domicilio. Eso no es buena onda, por favor, cambia 🙃"));
    }
}