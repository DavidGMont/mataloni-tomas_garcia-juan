package com.matalonigarcia.clinicaodontologica.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "DOMICILIOS")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "16")
    private Long id;

    @Size(min = 2, max = 50, message = "🌇 El nombre de la calle ingresado es inválido. Por favor ingresa un nombre " +
            "válido, puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' .#0-9]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🌇 Se encontraron caracteres inválidos en el nombre de calle ingresado. Puedes ingresar " +
                    "solamente caracteres latinos, dígitos, apóstrofo ('), guion (-), punto (.) y numeral (#).")
    @NotBlank(message = "🌇 Por favor ingresa un nombre de calle, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "Calle", description = "El nombre de la calle del domicilio.", example = "Pudding Lane")
    private String calle;

    @NotNull(message = "🔢 Por favor ingresa un número de calle, este no puede ser nulo.")
    @NonNull
    @Schema(title = "Número", description = "El número del domicilio.", example = "10")
    private Integer numero;

    @Size(min = 2, max = 50, message = "🗾 El nombre de la localidad ingresado es inválido. Por favor ingresa un " +
            "nombre válido, puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🗾 Se encontraron caracteres inválidos en el nombre de localidad ingresado. Por favor ingresa " +
                    "solamente caracteres latinos, apóstrofo (') o guion (-).")
    @NotBlank(message = "🗾 Por favor ingresa un nombre de localidad, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "Localidad", description = "El nombre de la localidad del domicilio.", example = "Birmingham")
    private String localidad;

    @Size(min = 2, max = 50, message = "🌍 El nombre de la provincia ingresado es inválido. Por favor ingresa un " +
            "nombre válido, puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🌍 Se encontraron caracteres inválidos en el nombre de provincia ingresado. Por favor ingresa " +
                    "solamente caracteres latinos, apóstrofo (') o guion (-).")
    @NotBlank(message = "🌍 Por favor ingresa un nombre de provincia, este no puede ser nulo, ni estar vacío.")
    @NonNull
    @Schema(title = "Provincia", description = "El nombre de la provincia del domicilio.",
            example = "Tierras Medias Occidentales")
    private String provincia;
}
