package com.matalonigarcia.clinicaodontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class PacienteDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "16")
    private Long id;

    @Schema(title = "Nombre", description = "El nombre de tu paciente.", example = "Willy")
    private String nombre;

    @Schema(title = "Apellido", description = "El apellido de tu paciente.", example = "Wonka")
    private String apellido;

    @Schema(title = "DNI", description = "El DNI de tu paciente.", example = "1144668")
    private String dni;

    @Schema(title = "Fecha de Ingreso", description = "La fecha en que registraste a tu paciente.",
            example = "2023-06-29")
    private LocalDate fechaIngreso;

    @Schema(title = "Domicilio", description = "La información del domicilio de tu paciente.")
    private DomicilioDto domicilioDto;

    public static PacienteDto fromPaciente(Paciente paciente) {
        return new PacienteDto(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getDni(),
                paciente.getFechaIngreso(),
                DomicilioDto.fromDomicilio(paciente.getDomicilio())
        );
    }

    @Override
    public String toString() {
        return "🚹 Paciente [" +
                "🆔 ID: " + id +
                " | 📛 Nombre: " + nombre +
                " | 📛 Apellido: " + apellido +
                " | 🔢 DNI: " + dni +
                " | 📅 Fecha de Ingreso: " + fechaIngreso +
                " | 🏡 Domicilio: " + domicilioDto +
                ']';
    }
}
