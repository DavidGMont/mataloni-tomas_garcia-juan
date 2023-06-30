package com.matalonigarcia.clinicaodontologica.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "PACIENTES")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "16")
    private Long id;

    @Size(min = 2, max = 50, message = "🔠 El nombre ingresado es inválido. Por favor ingresa un nombre válido, " +
            "puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🔠 Se encontraron caracteres inválidos en el nombre ingresado. Por favor ingresa solamente " +
                    "caracteres latinos, apóstrofo (') o guion (-).")
    @NotBlank(message = "🔠 Por favor ingresa un nombre, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "Nombre", description = "El nombre de tu paciente.", example = "Willy")
    private String nombre;

    @Size(min = 2, max = 50, message = "🔠 El apellido ingresado es inválido. Por favor ingresa un apellido válido, " +
            "puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🔠 Se encontraron caracteres inválidos en el apellido ingresado. Por favor ingresa solamente " +
                    "caracteres latinos, apóstrofo (') o guion (-).")
    @NotBlank(message = "🔠 Por favor ingresa un apellido, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "Apellido", description = "El apellido de tu paciente.", example = "Wonka")
    private String apellido;

    @Size(min = 7, max = 12, message = "🆔 El DNI ingresado es inválido. Por favor ingresa un DNI válido, este debe " +
            "tener entre 7 y 12 números.")
    @Pattern(regexp = "^\\d+$", message = "🆔 Se encontraron caracteres inválidos en el DNI ingresado. Por favor " +
            "ingresa solamente números.")
    @NotBlank(message = "🆔 Por favor ingresa un DNI, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "DNI", description = "El DNI de tu paciente.", example = "1144668")
    private String dni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "📅 La fecha ingresada es inválida. Por favor ingresa una fecha que sea igual o " +
            "posterior a la fecha actual.")
    @NotNull(message = "📅 Por favor ingresa una fecha, esta no puede ser nula.")
    @NonNull
    @Schema(title = "Fecha de Ingreso", description = "La fecha en que registraste a tu paciente.",
            example = "2023-06-29")
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id", nullable = false)
    @NotNull(message = "🏡 Por favor ingresa un domicilio, este no puede ser nulo.")
    @NonNull
    @Schema(title = "Domicilio", description = "La información del domicilio de tu paciente.")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;
}
