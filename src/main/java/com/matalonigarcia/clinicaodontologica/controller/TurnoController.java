package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.dto.TurnoDto;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.exceptions.BadRequestException;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.matalonigarcia.clinicaodontologica.service.ITurnoService;
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
@RequestMapping("/turnos")
@Tag(name = "Turnos", description = "🎫 Métodos CRUD para gestionar turnos.")
public class TurnoController {
    private final ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @Operation(summary = "️🎫 Registrar un turno",
            description = "Crea un turno nuevo en nuestra API, recuerda que primero necesitas un odontólogo y " +
                    "un paciente registrados, luego de eso ¡Te agendamos!",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "🙌 Turno creado exitosamente ¡Va llegando el trabajo!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
                            }),
                    @ApiResponse(responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se creó el turno, " +
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
    public ResponseEntity<TurnoDto> registrarTurno(@Valid @RequestBody Turno turno)
            throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.registrarTurno(turno));
    }

    @Operation(summary = "🔍 Buscar un turno por ID",
            description = "¿Se te olvidó cuándo y con quién agendaste tu turno? ¡Pásame el ID y te doy " +
                    "esa información!",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🎫 Encontraste a tu turno ¿dudas despejadas?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese turno que pensabas que existía, ya no existe (al menos en " +
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
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.buscarTurnoPorId(id));
    }

    @Operation(summary = "🎫 Listar todos los turnos",
            description = "¿Quieres ver toda la cantidad de trabajo que tenemos? Bien, aquí va.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🎫 He aquí todos los turnos que tenemos ¡eso se traduce en dinero!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
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
    public List<TurnoDto> listarTodosLosTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @Operation(summary = "🔁 Actualizar un turno",
            description = "¿Que tu paciente ya no viene? ¿Y si reagendas esa cita con otro odontólogo? Hazlo aquí " +
                    "pasándome toda la información.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🎫‍️ Actualizaste tu turno correctamente ¡El trabajo continúa!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se actualizó al turno, " +
                                    "por favor intenta nuevamente.",
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
    public ResponseEntity<TurnoDto> actualizarTurno(@Valid @RequestBody Turno turno)
            throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

    @Operation(summary = "🚮 Eliminar un turno por ID",
            description = "¿Acaso te equivocaste agendando ese turno? ¿Y si corriges tu error eliminándolo? 😈",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "️❌ ¡Felicitaciones! Pasado pisado, ahora ese turno está eliminado.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese turno que pensabas que existía, ya no existe (al menos en " +
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
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("🚮 Se ha eliminado el turno con ID ".concat(id.toString()).concat("."));
    }
}
