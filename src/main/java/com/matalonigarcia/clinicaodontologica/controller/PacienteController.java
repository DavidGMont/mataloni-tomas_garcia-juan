package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> registrarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<PacienteDto> response = ResponseEntity.badRequest().build();
        PacienteDto pacienteDto = pacienteService.registrarPaciente(paciente);
        if (pacienteDto != null) response = ResponseEntity.status(HttpStatus.CREATED).body(pacienteDto);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable int id) {
        ResponseEntity<PacienteDto> response = ResponseEntity.badRequest().build();
        PacienteDto pacienteDto = pacienteService.buscarPacientePorId(id);
        if (pacienteDto != null) response = ResponseEntity.ok(pacienteDto);
        return response;
    }

    @GetMapping("/s")
    public ResponseEntity<PacienteDto> buscarPacientePorDni(@RequestParam("dni") String dni) {
        ResponseEntity<PacienteDto> response = ResponseEntity.notFound().build();
        PacienteDto pacienteDto = pacienteService.buscarPacientePorDni(dni);
        if (pacienteDto != null) response = ResponseEntity.ok(pacienteDto);
        return response;
    }

    @GetMapping
    public List<PacienteDto> listarTodosLosPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<PacienteDto> response = ResponseEntity.badRequest().build();
        PacienteDto pacienteDto = pacienteService.actualizarPaciente(paciente);
        if (pacienteDto != null) response = ResponseEntity.status(HttpStatus.ACCEPTED).body(pacienteDto);
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPaciente(@PathVariable int id) {
        pacienteService.eliminarPaciente(id);
    }
}
