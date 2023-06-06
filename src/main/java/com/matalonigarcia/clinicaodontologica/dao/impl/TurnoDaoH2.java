package com.matalonigarcia.clinicaodontologica.dao.impl;

import com.matalonigarcia.clinicaodontologica.dao.H2Connection;
import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDaoH2 implements IDao<Turno> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoDaoH2.class);

    @Override
    public Turno guardar(Turno turno) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TURNOS(FECHA, ODONTOLOGO_ID) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(turno.getFechaHora()));
            preparedStatement.setInt(2, turno.getOdontologo().getId());
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                turno.setId(generatedKeys.getInt(1));
            }

            connection.commit();
            LOGGER.info("️🎫 Se guardó al turno: " + turno);
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

        return turno;
    }

    @Override
    public List<Turno> listarTodos() {
        List<Turno> turnos = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TURNOS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                turnos.add(crearObjetoTurno(resultSet));
            }

            LOGGER.info("🔢 Listando todos los turnos: " + turnos);
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

        return turnos;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM TURNOS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            connection.commit();
            LOGGER.info("🚮 Se ha eliminado el turno con ID: " + id);
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
    public Turno buscarPorId(int id) {
        Connection connection = null;
        Turno turno = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM TURNOS WHERE ID = ?");
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                turno = crearObjetoTurno(resultSet);
            }

            LOGGER.info("🎫 Se ha encontrado al turno con ID " + id + ": " + turno);
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

        return turno;
    }

    @Override
    public Turno buscarPorCriterio(String criterio) {
        return null;
    }

    @Override
    public Turno actualizar(Turno turno) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE TURNOS SET FECHA = ?, ODONTOLOGO_ID = ? WHERE ID = ?");
            preparedStatement.setTimestamp(1, Timestamp.valueOf(turno.getFechaHora()));
            preparedStatement.setInt(2, turno.getOdontologo().getId());
            preparedStatement.setInt(3, turno.getId());
            preparedStatement.execute();

            connection.commit();
            LOGGER.warn("🛑 El turno con ID " + turno.getId() + " ha sido actualizado: " + turno);
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

        return turno;
    }

    private Turno crearObjetoTurno(ResultSet resultSet) throws SQLException {
        return new Turno(
                resultSet.getInt("ID"),
                resultSet.getTimestamp("FECHA").toLocalDateTime(),
                new OdontologoDaoH2().buscarPorId(resultSet.getInt("ODONTOLOGO_ID"))
        );
    }
}
