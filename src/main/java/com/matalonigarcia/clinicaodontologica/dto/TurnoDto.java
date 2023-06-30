package com.matalonigarcia.clinicaodontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class TurnoDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "154")
    private Long id;

    @Schema(title = "Fecha y Hora", description = "La fecha y hora en que programaste tu turno.",
            example = "2023-06-29T13:25")
    private LocalDateTime fechaHora;

    @Schema(title = "Paciente DTO", description = "La información de tu paciente en DTO.")
    private PacienteDto pacienteDto;

    @Schema(title = "Odontólogo DTO", description = "La información de tu odontólogo en DTO.")
    private OdontologoDto odontologoDto;

    public static TurnoDto fromTurno(Turno turno) {
        return new TurnoDto(
                turno.getId(),
                turno.getFechaHora(),
                PacienteDto.fromPaciente(turno.getPaciente()),
                OdontologoDto.fromOdontologo(turno.getOdontologo())
        );
    }

    @Override
    public String toString() {
        return "🎫 Turno: [" +
                " 🆔 ID: " + id +
                " | 📅 Fecha y Hora: " + fechaHora +
                " | 🚹 Paciente: " + pacienteDto +
                " | 🦷 Odontólogo: " + odontologoDto +
                ']';
    }
}
