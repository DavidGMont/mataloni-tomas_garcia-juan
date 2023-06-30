package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.matalonigarcia.clinicaodontologica.service.impl.OdontologoService;
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
@RequestMapping("/odontologos")
@Tag(name = "Odontólogos", description = "👨‍⚕️ Métodos CRUD para gestionar odontólogos.")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @Operation(summary = "👨‍⚕️ Registrar un odontólogo",
            description = "Crea un odontólogo nuevo en nuestra API, solamente envíanos los datos como los " +
                    "requerimos, et voilà !",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "🙌 Odontólogo creado exitosamente ¡Se ha unido un nuevo integrante " +
                                    "al equipo!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = OdontologoDto.class))
                            }),
                    @ApiResponse(
                            responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se creó al odontólogo, " +
                                    "por favor intenta nuevamente.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Eso que buscas, no existe, al menos en nuestra base de datos.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content)
            })
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registrarOdontologo(@Valid @RequestBody Odontologo odontologo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.registrarOdontologo(odontologo));
    }

    @Operation(summary = "🔍 Buscar un odontólogo por ID",
            description = "Consulta a ese odontólogo que creaste antes, solamente pásame su ID y te paso toda " +
                    "su información.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👨‍⚕️ Encontraste a tu odontólogo ¿y si ahora agendas un turno con él?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = OdontologoDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese odontólogo que pensabas que existía, ya no existe (al menos en " +
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
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.buscarOdontologoPorId(id));
    }

    @Operation(summary = "🦷 Listar todos los odontólogos",
            description = "Consulta nuestro variado catálogo de odontólogos ¿Buscas a Philip Sherman? ¡Lo tenemos! " +
                    "¿O acaso a Wilbur Wonka? ¡Pues también lo tenemos! 💅",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🦷 He aquí todos los odontólogos que tenemos, seguro encuentras a tu " +
                                    "favorito.",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = OdontologoDto.class))
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
    public List<OdontologoDto> listarTodosLosOdontologos() {
        return odontologoService.listarTodosLosOdontologos();
    }

    @Operation(summary = "🔁 Actualizar un odontólogo",
            description = "¿Con que tu odontólogo no era ese que había dicho ser? ¿O se te fue un dato errado? " +
                    "¡Corrígelo aquí! Solamente necesitas consultar los datos ya guardados y remplazarlos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👨‍⚕️ Actualizaste tu odontólogo correctamente ¿acaso no quedó más lindo?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = OdontologoDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se actualizó al " +
                                    "odontólogo, por favor intenta nuevamente.",
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
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@Valid @RequestBody Odontologo odontologo)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
    }

    @Operation(summary = "🚮 Eliminar un odontólogo por ID",
            description = "En términos de soportar ¿no soportaste más la presencia de aquel odontólogo en la " +
                    "clínica? ¿Y si lo eliminas? 😈",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "️❌ ¡Felicitaciones! Ese odontólogo dejó de ser un problema, ahora " +
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
                            description = "🔎 Ese odontólogo que pensabas que existía, ya no existe (al menos en " +
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
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("🚮 Se ha eliminado al odontólogo con ID ".concat(id.toString()).concat("."));
    }
}
