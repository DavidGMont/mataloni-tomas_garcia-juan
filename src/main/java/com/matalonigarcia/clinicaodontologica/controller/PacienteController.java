package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.dto.PacienteDto;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.matalonigarcia.clinicaodontologica.service.IPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "👨‍👩‍👧‍👦 Métodos CRUD para gestionar pacientes.")
public class PacienteController {
    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "️👩 Registrar un paciente",
            description = "Crea un paciente nuevo en nuestra API, solamente envíanos los datos como los requerimos, " +
                    "et voilà !",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "🙌 Paciente creado exitosamente ¡Cada vez tenemos más clientela!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }),
                    @ApiResponse(responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se creó al paciente, " +
                                    "por favor intenta nuevamente.",
                            content = @Content),
                    @ApiResponse(responseCode = "404",
                            description = "🔎 Eso que buscas puede que no exista, al menos en nuestra base de datos.",
                            content = @Content),
                    @ApiResponse(responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content)
            })
    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> registrarPaciente(@Valid @RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.registrarPaciente(paciente));
    }

    @Operation(summary = "🔍 Buscar un paciente por ID",
            description = "¿Necesitas investigar a un paciente? Solamente pásame su ID y te paso todo lo que sé.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👧 Encontraste a tu paciente ¿y ahora qué?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese paciente que pensabas que existía, ya no existe (al menos en " +
                                    "nuestra base de datos).",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscarPacientePorId(id));
    }

    @Operation(summary = "🔍 Buscar un paciente por DNI",
            description = "¿Por qué te sabes el DNI de este paciente? 😲",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🧑 Encontraste a tu paciente, estaría bueno que pararas de investigar.",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese paciente que pensabas que existía, ya no existe (al menos en " +
                                    "nuestra base de datos).",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @GetMapping("/s")
    public ResponseEntity<PacienteDto> buscarPacientePorDni(@RequestParam("dni") String dni)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscarPacientePorDni(dni));
    }

    @Operation(summary = "👨‍👩‍👧‍👦 Listar todos los pacientes",
            description = "Nuestra clientela es exclusiva, eso, sin dudas ¿quieres conocerla?",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👨‍👩‍👧‍👦 He aquí todos los pacientes que tenemos ¡Son nuestra razón de ser!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 La probabilidad de encontrar este error es mínima, y, aun así, lo " +
                                    "lograste encontrar ¿quieres un premio?",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Eso que buscas, no existe, al menos en nuestra base de datos.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @GetMapping
    public List<PacienteDto> listarTodosLosPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @Operation(summary = "🔁 Actualizar un paciente",
            description = "¿Con que tu paciente no era ese que había dicho ser? ¿O se te fue un dato errado? " +
                    "¡Corrígelo aquí! Solamente necesitas consultar los datos ya guardados y remplazarlos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👨‍️ Actualizaste tu paciente correctamente ¿acaso no quedó más lindo?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se actualizó al " +
                                    "paciente, por favor intenta nuevamente.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Eso que buscas, no existe, al menos en nuestra base de datos.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@Valid @RequestBody Paciente paciente)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
    }

    @Operation(summary = "🚮 Eliminar un paciente por ID",
            description = "¿Acaso ese paciente fue maleducado contigo? ¿Y si lo eliminas? 😈",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "️❌ ¡Felicitaciones! Ese paciente dejó de ser un problema, ahora " +
                                    "está eliminado.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese paciente que pensabas que existía, ya no existe (al menos en " +
                                    "nuestra base de datos).",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("🚮 Se ha eliminado al paciente con ID ".concat(id.toString()).concat("."));
    }
}
