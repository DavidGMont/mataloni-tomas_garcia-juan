package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.registrarPaciente(paciente);
    }

    @GetMapping("/{id}")
    public Paciente buscarPacientePorId(@PathVariable int id) {
        return pacienteService.buscarPacientePorId(id);
    }

    @GetMapping("/dni")
    public Paciente buscarPacientePorDni(@RequestParam("dni") String dni) {
        return pacienteService.buscarPacientePorDni(dni);
    }

    @GetMapping
    public List<Paciente> listarTodosLosPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @PutMapping("/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPaciente(@PathVariable int id) {
        pacienteService.eliminarPaciente(id);
    }
}
