package com.matalonigarcia.clinicaodontologica.service.impl;

import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private final IDao<Turno> turnoIDao;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    public Turno guardarTurno(Turno turno) {
        return turnoIDao.guardar(turno);
    }

    public Turno buscarTurnoPorId(int id) {
        return turnoIDao.buscarPorId(id);
    }

    public List<Turno> listarTodosLosTurnos() {
        return turnoIDao.listarTodos();
    }

    public void eliminarTurno(int id) {
        turnoIDao.eliminar(id);
    }
}
