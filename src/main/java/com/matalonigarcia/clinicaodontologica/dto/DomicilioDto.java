package com.matalonigarcia.clinicaodontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matalonigarcia.clinicaodontologica.entity.Domicilio;
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
public class DomicilioDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "16")
    private Long id;

    @Schema(title = "Calle", description = "El nombre de la calle del domicilio.", example = "Pudding Lane")
    private String calle;

    @Schema(title = "Número", description = "El número del domicilio.", example = "10")
    private Integer numero;

    @Schema(title = "Localidad", description = "El nombre de la localidad del domicilio.", example = "Birmingham")
    private String localidad;

    @Schema(title = "Provincia", description = "El nombre de la provincia del domicilio.",
            example = "Tierras Medias Occidentales")
    private String provincia;

    public static DomicilioDto fromDomicilio(Domicilio domicilio) {
        return new DomicilioDto(
                domicilio.getId(),
                domicilio.getCalle(),
                domicilio.getNumero(),
                domicilio.getLocalidad(),
                domicilio.getProvincia()
        );
    }

    @Override
    public String toString() {
        return "🏡 Domicilio [" +
                "🆔 ID: " + id +
                " | 🌇 Calle: " + calle +
                " | 🔢 Número: " + numero +
                " | 🗾 Localidad: " + localidad +
                " | 🌍 Provincia: " + provincia +
                ']';
    }
}
