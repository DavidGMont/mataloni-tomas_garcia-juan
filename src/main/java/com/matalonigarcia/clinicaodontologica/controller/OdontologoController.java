package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDto> response = ResponseEntity.badRequest().build();
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        if (odontologoDto != null) response = ResponseEntity.status(HttpStatus.CREATED).body(odontologoDto);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) {
        ResponseEntity<OdontologoDto> response = ResponseEntity.badRequest().build();
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);
        if (odontologoDto != null) response = ResponseEntity.ok(odontologoDto);
        return response;
    }

    @GetMapping
    public List<OdontologoDto> listarTodosLosOdontologos() {
        return odontologoService.listarTodosLosOdontologos();
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDto> response = ResponseEntity.badRequest().build();
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(odontologo);
        if (odontologoDto != null) response = ResponseEntity.ok(odontologoDto);
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        ResponseEntity<?> response = ResponseEntity.notFound().build();
        if (buscarOdontologoPorId(id).getStatusCode().is2xxSuccessful()) {
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.ok().build();
        }
        return response;
    }
}
