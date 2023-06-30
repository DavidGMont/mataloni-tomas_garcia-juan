package com.matalonigarcia.clinicaodontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class OdontologoDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "23")
    private Long id;

    @Schema(title = "Matrícula", description = "La matrícula profesional de tu odontólogo.",
            example = "61922-201578 GBR")
    private String matricula;

    @Schema(title = "Nombre", description = "El nombre de tu odontólogo.", example = "Wilbur")
    private String nombre;

    @Schema(title = "Apellido", description = "El apellido de tu odontólogo.", example = "Wonka")
    private String apellido;

    public static OdontologoDto fromOdontologo(Odontologo odontologo) {
        return new OdontologoDto(
                odontologo.getId(),
                odontologo.getMatricula(),
                odontologo.getNombre(),
                odontologo.getApellido()
        );
    }

    @Override
    public String toString() {
        return "👨‍⚕️ Odontólogo" +
                " [ 🆔 ID: " + id +
                " | 🔢 Número de Matrícula: " + matricula +
                " | 📛 Nombre: " + nombre +
                " | 📛 Apellido: " + apellido +
                ']';
    }
}
