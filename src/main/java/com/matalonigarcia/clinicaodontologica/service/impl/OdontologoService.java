package com.matalonigarcia.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matalonigarcia.clinicaodontologica.dto.OdontologoDto;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import com.matalonigarcia.clinicaodontologica.repository.IDao;
import com.matalonigarcia.clinicaodontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final IDao<Odontologo> odontologoIDao;
    private final ObjectMapper mapper;

    @Autowired
    public OdontologoService(IDao<Odontologo> odontologoIDao, ObjectMapper mapper) {
        this.odontologoIDao = odontologoIDao;
        this.mapper = mapper;
    }

    @Override
    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
        return mapper.convertValue(odontologoIDao.guardar(odontologo), OdontologoDto.class);
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(int id) {
        return mapper.convertValue(odontologoIDao.buscarPorId(id), OdontologoDto.class);
    }

    @Override
    public List<OdontologoDto> listarTodosLosOdontologos() {
        return odontologoIDao.listarTodos().stream()
                .map(odontologo -> mapper.convertValue(odontologo, OdontologoDto.class))
                .toList();
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        return mapper.convertValue(odontologoIDao.actualizar(odontologo), OdontologoDto.class);
    }

    @Override
    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }
}
