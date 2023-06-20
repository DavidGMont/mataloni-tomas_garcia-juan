package com.matalonigarcia.clinicaodontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDto {
    private Long id;
    private String matricula;
    private String nombre;
    private String apellido;

    public OdontologoDto() {}

    public OdontologoDto(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public static OdontologoDto fromOdontologo(Odontologo odontologo) {
        return new OdontologoDto(
                odontologo.getId(),
                odontologo.getMatricula(),
                odontologo.getNombre(),
                odontologo.getApellido()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
