package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.dto.TurnoDto;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody Turno turno) {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.registrarTurno(turno);
        if (turnoDto != null) response = ResponseEntity.status(HttpStatus.CREATED).body(turnoDto);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.buscarTurnoPorId(id);
        if (turnoDto != null) response = ResponseEntity.ok(turnoDto);
        return response;
    }

    @GetMapping
    public List<TurnoDto> listarTodosLosTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody Turno turno) {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.actualizarTurno(turno);
        if (turnoDto != null) response = ResponseEntity.ok(turnoDto);
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        ResponseEntity<?> response = ResponseEntity.notFound().build();
        if (buscarTurnoPorId(id).getStatusCode().is2xxSuccessful()) {
            turnoService.eliminarTurno(id);
            response = ResponseEntity.ok().build();
        }
        return response;
    }
}
