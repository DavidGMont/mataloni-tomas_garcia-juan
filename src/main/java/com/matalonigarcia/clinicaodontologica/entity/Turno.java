package com.matalonigarcia.clinicaodontologica.entity;

import java.time.LocalDateTime;

public class Turno {
    private int id;
    private LocalDateTime fechaHora;
    private Odontologo odontologo;
    private Paciente paciente;

    public Turno() {
    }

    public Turno(int id, LocalDateTime fechaHora, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Turno(LocalDateTime fechaHora, Odontologo odontologo) {
        this.fechaHora = fechaHora;
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "🎫 Turno: [" +
                " 🆔 ID: " + id +
                " | 📅 Fecha y Hora: " + fechaHora +
                " | 🚹 Paciente: " + paciente +
                " | 🦷 Odontólogo: " + odontologo +
                ']';
    }
}
