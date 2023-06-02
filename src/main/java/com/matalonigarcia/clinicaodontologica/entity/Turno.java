package com.matalonigarcia.clinicaodontologica.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Turno {
    private LocalDateTime fechaHora;
    private Odontologo odontologo;

    public Turno(LocalDateTime fechaHora, Odontologo odontologo) {
        this.fechaHora = fechaHora;
        this.odontologo = odontologo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    @Override
    public String toString() {
        return "✅ Turno: [" +
                "📅 Fecha y Hora: " + fechaHora + " | " +
                "🦷 Odontólogo: " + odontologo +
                ']';
    }
}
