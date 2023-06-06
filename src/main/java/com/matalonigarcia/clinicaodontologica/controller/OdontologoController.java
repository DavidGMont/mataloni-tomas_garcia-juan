package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) {
        return odontologoService.registrarOdontologo(odontologo);
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorId(@PathVariable int id) {
        return odontologoService.buscarOdontologoPorId(id);
    }

    @GetMapping
    public List<Odontologo> listarTodosLosOdontologos() {
        return odontologoService.listarTodosLosOdontologos();
    }

    @PutMapping("/actualizar")
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo) {
        return odontologoService.actualizarOdontologo(odontologo);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable int id) {
        odontologoService.eliminarOdontologo(id);
    }
}
