package com.matalonigarcia.clinicaodontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "ODONTOLOGOS")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "23")
    private Long id;

    @Size(min = 15, max = 16, message = "🆔 La matrícula ingresada es inválida. Por favor ingresa una matrícula " +
            "válida, esta debe tener entre 15 y 16 caracteres.")
    @Pattern(regexp = "\\d{5}-\\d{5,6} \\p{IsLatin}{3}+", flags = Pattern.Flag.UNICODE_CASE,
            message = "🆔 Se validó la matrícula y esta no cumple con los requerimientos. El formato es el " +
                    "siguiente: 12345-123456 ABC [5 dígitos | 1 guion | 5 a 6 dígitos | 1 espacio | 3 letras].")
    @NotBlank(message = "🆔 Por favor ingresa una matrícula, esta no puede ser nula, ni estar vacía.")
    @NonNull
    @Schema(title = "Matrícula", description = "La matrícula profesional de tu odontólogo.",
            example = "61922-201578 GBR")
    private String matricula;

    @Size(min = 2, max = 50, message = "🔠 El nombre ingresado es inválido. Por favor ingresa un nombre válido, " +
            "puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🔠 Se encontraron caracteres inválidos en el nombre ingresado. Por favor ingresa solamente " +
                    "caracteres latinos, apóstrofo (') o guion (-).")
    @NotBlank(message = "🔠 Por favor ingresa un nombre, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "Nombre", description = "El nombre de tu odontólogo.", example = "Wilbur")
    private String nombre;

    @Size(min = 2, max = 50, message = "El apellido puede tener hasta 50 caracteres")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🔠 Se encontraron caracteres inválidos en el apellido ingresado. Por favor ingresa solamente " +
                    "caracteres latinos, apóstrofo (') o guion (-).")
    @NotBlank(message = "🔠 Por favor ingresa un apellido, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "Apellido", description = "El apellido de tu odontólogo.", example = "Wonka")
    private String apellido;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;
}
