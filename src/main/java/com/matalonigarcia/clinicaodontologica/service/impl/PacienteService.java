package com.matalonigarcia.clinicaodontologica.service.impl;

import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import com.matalonigarcia.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final IDao<Paciente> pacienteIDao;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public List<Paciente> listarTodosLosPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public Paciente buscarPacientePorDni(String dni) {
        return pacienteIDao.buscarPorCriterio(dni);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    @Override
    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }
}
