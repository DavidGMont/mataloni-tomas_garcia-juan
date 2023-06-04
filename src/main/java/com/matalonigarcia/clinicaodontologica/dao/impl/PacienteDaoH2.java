package com.matalonigarcia.clinicaodontologica.dao.impl;

import com.matalonigarcia.clinicaodontologica.dao.H2Connection;
import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.guardar(paciente.getDomicilio());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID, TURNO_ID) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());
            preparedStatement.setInt(6, paciente.getTurno().getId());
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                paciente.setId(generatedKeys.getInt(1));
            }

            connection.commit();
            LOGGER.info("️🚹 Se guardó al paciente: " + paciente);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: " + e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("💥 Tuvimos un problema con el registro: " + e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    LOGGER.error("💥 Hay un problema con SQL: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pacientes.add(crearObjetoPaciente(resultSet));
            }

            LOGGER.info("👨‍👩‍👦‍👦 Listando todos los pacientes: " + pacientes);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return pacientes;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PACIENTES WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            connection.commit();
            LOGGER.info("🚮 Se ha eliminado al paciente con ID: " + id);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: " + e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("💥 Tuvimos un problema con el registro: " + e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    LOGGER.error("💥 Hay un problema con SQL: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public Paciente buscarPorId(int id) {
        Connection connection = null;
        Paciente paciente = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?");
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                paciente = crearObjetoPaciente(resultSet);
            }

            LOGGER.info("🚹 Se ha encontrado al paciente con ID " + id + ": " + paciente);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return paciente;
    }

    @Override
    public Paciente buscarPorCriterio(String criterio) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE DNI = ?");
            ps.setString(1, criterio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paciente = crearObjetoPaciente(rs);
            }
            LOGGER.info("Se ha encontrado el paciente con dni " + criterio + ": " + paciente);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return paciente;
    }

    private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {
        return new Paciente(
                resultSet.getInt("ID"),
                resultSet.getString("NOMBRE"),
                resultSet.getString("APELLIDO"),
                resultSet.getString("DNI"),
                resultSet.getDate("FECHA").toLocalDate(),
                new DomicilioDaoH2().buscarPorId(resultSet.getInt("DOMICILIO_ID")),
                new TurnoDaoH2().buscarPorId(resultSet.getInt("TURNO_ID"))
        );
    }
}
