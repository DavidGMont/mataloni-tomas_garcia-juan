package com.matalonigarcia.clinicaodontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private String nombre;
    private String apellido;
    private String dni;
    private DomicilioDto domicilioDto;

    public PacienteDto() {}

    public PacienteDto(String nombre, String apellido, String dni, DomicilioDto domicilioDto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilioDto = domicilioDto;
    }

    public static PacienteDto fromPaciente(Paciente paciente) {
        return new PacienteDto(
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getDni(),
                DomicilioDto.fromDomicilio(paciente.getDomicilio())
        );
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public DomicilioDto getDomicilioDto() {
        return domicilioDto;
    }

    public void setDomicilioDto(DomicilioDto domicilioDto) {
        this.domicilioDto = domicilioDto;
    }
}
