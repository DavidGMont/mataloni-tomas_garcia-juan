package com.matalonigarcia.clinicaodontologica.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "154")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "🕔 La fecha y hora ingresada es inválida. Por favor ingresa una fecha y hora que " +
            "sea igual o posterior a la fecha y hora actual.")
    @NotNull(message = "🕔 Por favor ingresa una fecha y hora, esta no puede ser nula.")
    @NonNull
    @Schema(title = "Fecha y Hora", description = "La fecha y hora en que programaste tu turno.",
            example = "2023-06-29T13:25")
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "👩 Por favor ingresa un paciente, este no puede ser nulo.")
    @NonNull
    @Schema(title = "Paciente", description = "La información de tu paciente.")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", nullable = false)
    @NotNull(message = "👨‍⚕️ Por favor ingresa un odontólogo, este no puede ser nulo.")
    @NonNull
    @Schema(title = "Odontólogo", description = "La información de tu odontólogo.")
    private Odontologo odontologo;
}
