package com.matalonigarcia.clinicaodontologica.service;

import com.matalonigarcia.clinicaodontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo registrarOdontologo(Odontologo odontologo);

    Odontologo buscarOdontologoPorId(int id);

    List<Odontologo> listarTodosLosOdontologos();

    Odontologo actualizarOdontologo(Odontologo odontologo);

    void eliminarOdontologo(int id);
}
