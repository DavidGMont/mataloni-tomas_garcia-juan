package com.matalonigarcia.clinicaodontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {
    private LocalDateTime fechaHora;
    private PacienteDto pacienteDto;
    private OdontologoDto odontologoDto;

    public TurnoDto() {}

    public TurnoDto(LocalDateTime fecha, PacienteDto pacienteDto, OdontologoDto odontologoDto) {
        this.fechaHora = fecha;
        this.pacienteDto = pacienteDto;
        this.odontologoDto = odontologoDto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public PacienteDto getPacienteDto() {
        return pacienteDto;
    }

    public void setPacienteDto(PacienteDto pacienteDto) {
        this.pacienteDto = pacienteDto;
    }

    public OdontologoDto getOdontologoDto() {
        return odontologoDto;
    }

    public void setOdontologoDto(OdontologoDto odontologoDto) {
        this.odontologoDto = odontologoDto;
    }
}
