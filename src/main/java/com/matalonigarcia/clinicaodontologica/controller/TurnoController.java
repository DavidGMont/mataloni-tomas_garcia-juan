package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public Turno registrarTurno(@RequestBody Turno turno) {
        return turnoService.registrarTurno(turno);
    }

    @GetMapping("/{id}")
    public Turno buscarTurnoPorId(@PathVariable int id) {
        return turnoService.buscarTurnoPorId(id);
    }

    @GetMapping
    public List<Turno> listarTodosLosTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @PutMapping("/actualizar")
    public Turno actualizarTurno(@RequestBody Turno turno) {
        return turnoService.actualizarTurno(turno);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarTurno(@PathVariable int id) {
        turnoService.eliminarTurno(id);
    }
}
