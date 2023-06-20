package com.matalonigarcia.clinicaodontologica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 15)
    @Pattern(regexp = "^\\d+$")
    private String matricula;
    @Size(max = 50, message = "El nombre puede tener hasta 50 caracteres")
    @Pattern(regexp = "[\\p{IsLatin}+?\\-' ]+", flags = Pattern.Flag.UNICODE_CASE)
    @NotNull
    private String nombre;
    @Size(max = 50, message = "El apellido puede tener hasta 50 caracteres")
    @Pattern(regexp = "[\\p{IsLatin}+?\\-' ]+", flags = Pattern.Flag.UNICODE_CASE)
    @NotNull
    private String apellido;

    public Odontologo() {}

    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
