package com.matalonigarcia.clinicaodontologica.service.impl;

import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import com.matalonigarcia.clinicaodontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private final IDao<Turno> turnoIDao;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    @Override
    public Turno registrarTurno(Turno turno) {
        return turnoIDao.guardar(turno);
    }

    @Override
    public Turno buscarTurnoPorId(int id) {
        return turnoIDao.buscarPorId(id);
    }

    @Override
    public List<Turno> listarTodosLosTurnos() {
        return turnoIDao.listarTodos();
    }

    @Override
    public Turno actualizarTurno(Turno turno) {
        return null;
    }

    @Override
    public void eliminarTurno(int id) {
        turnoIDao.eliminar(id);
    }
}
