package com.matalonigarcia.clinicaodontologica.service.impl;

import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {
    private final IDao<Domicilio> domicilioIDao;

    @Autowired
    public DomicilioService(IDao<Domicilio> domicilioIDao) {
        this.domicilioIDao = domicilioIDao;
    }

    public Domicilio guardarDomicilio(Domicilio domicilio) {
        return domicilioIDao.guardar(domicilio);
    }

    public Domicilio buscarDomicilioPorId(int id) {
        return domicilioIDao.buscarPorId(id);
    }

    public List<Domicilio> listarTodosLosDomicilios() {
        return domicilioIDao.listarTodos();
    }

    public void eliminarDomicilio(int id) {
        domicilioIDao.eliminar(id);
    }
}
