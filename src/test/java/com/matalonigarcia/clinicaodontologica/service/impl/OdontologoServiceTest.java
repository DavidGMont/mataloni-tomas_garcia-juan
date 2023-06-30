package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    private static Odontologo odontologo;
    private final OdontologoService odontologoService;
    private final ObjectMapper mapper;

    @Autowired
    OdontologoServiceTest(OdontologoService odontologoService, ObjectMapper mapper) {
        this.odontologoService = odontologoService;
        this.mapper = mapper;
    }

    @BeforeAll
    static void prepararOdontologo() {
        odontologo = new Odontologo("54774-877554 AUS", "Philip", "Sherman");
    }

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologoEnLaBaseDeDatos() {
        OdontologoDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        Assertions.assertEquals(1L, (long) odontologoRegistrado.getId());
        Assertions.assertEquals("Philip", odontologoRegistrado.getNombre());
        Assertions.assertEquals("Sherman", odontologoRegistrado.getApellido());
    }

    @Test
    @Order(2)
    void deberiaEncontrarUnOdontologoPorIdEnLaBaseDeDatos() throws ResourceNotFoundException {
        OdontologoDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(1L);

        Assertions.assertEquals(1L, odontologoEncontrado.getId());
        Assertions.assertEquals(odontologo.getNombre(), odontologoEncontrado.getNombre());
        Assertions.assertEquals(odontologo.getApellido(), odontologoEncontrado.getApellido());
    }

    @Test
    @Order(3)
    void deberiaActualizarUnOdontologoExistenteEnLaBaseDeDatos() throws ResourceNotFoundException {
        OdontologoDto odontoloParaActualizar = odontologoService.buscarOdontologoPorId(1L);
        odontoloParaActualizar.setNombre("Wilbur");
        odontoloParaActualizar.setApellido("Wonka");
        odontoloParaActualizar.setMatricula("61922-201578 GBR");

        odontologoService.actualizarOdontologo(mapper.convertValue(odontoloParaActualizar, Odontologo.class));
        OdontologoDto odontologoRecienActualizado = odontologoService.buscarOdontologoPorId(1L);

        Assertions.assertEquals(odontoloParaActualizar.getId(), odontologoRecienActualizado.getId());
        Assertions.assertEquals(odontoloParaActualizar.getNombre(), odontologoRecienActualizado.getNombre());
        Assertions.assertEquals(odontoloParaActualizar.getApellido(), odontologoRecienActualizado.getApellido());
    }

    @Test
    @Order(4)
    void deberiaValidarQueLaListaDeOdontologosTieneSoloUnRegistro() {
        Assertions.assertEquals(1, odontologoService.listarTodosLosOdontologos().size());
    }

    @Test
    @Order(5)
    void deberiaEliminarElUnicoOdontologoQueExiste() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(odontologo.getId());

        Assertions.assertTrue(odontologoService.listarTodosLosOdontologos().isEmpty());
    }

    @Test
    @Order(6)
    void deberiaLanzarResourceNotFoundExceptionAlBuscarElOdontologoEliminado() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologoPorId(1L));
    }

    @Test
    @Order(7)
    void deberiaLanzarConstraintViolationExceptionPorRegistrarUnOdontologoConDatosInvalidos() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(
                new Odontologo(
                        "12345678901234567890",
                        "{Un nombre con llavecitas}",
                        "[Un apellido entre corchetes]")
        ));
    }
}