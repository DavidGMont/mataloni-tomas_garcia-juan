package com.matalonigarcia.clinicaodontologica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "DOMICILIOS")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, message = "El nombre de la calle puede tener hasta 50 caracteres")
    @Pattern(regexp = "[\\p{IsLatin}+?\\-' .#0-9]+", flags = Pattern.Flag.UNICODE_CASE)
    @NotNull
    private String calle;
    private int numero;
    @Size(max = 50, message = "El nombre de la localidad puede tener hasta 50 caracteres")
    @Pattern(regexp = "[\\p{IsLatin}+?\\-' ]+", flags = Pattern.Flag.UNICODE_CASE)
    @NotNull
    private String localidad;
    @Size(max = 50, message = "El nombre de la provincia puede tener hasta 50 caracteres")
    @Pattern(regexp = "[\\p{IsLatin}+?\\-' ]+", flags = Pattern.Flag.UNICODE_CASE)
    @NotNull
    private String provincia;

    public Domicilio() {}

    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
