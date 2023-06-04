package com.matalonigarcia.clinicaodontologica.service.impl;

import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private final IDao<Odontologo> odontologoIDao;

    @Autowired
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    public List<Odontologo> listarTodosLosOdontologos() {
        return odontologoIDao.listarTodos();
    }

    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }
}
