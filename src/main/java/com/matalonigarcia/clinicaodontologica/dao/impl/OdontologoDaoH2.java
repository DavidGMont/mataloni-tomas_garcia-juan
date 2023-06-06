package com.matalonigarcia.clinicaodontologica.dao.impl;

import com.matalonigarcia.clinicaodontologica.dao.H2Connection;
import com.matalonigarcia.clinicaodontologica.dao.IDao;
import com.matalonigarcia.clinicaodontologica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologo.setId(resultSet.getInt(1));
            }

            connection.commit();
            LOGGER.info("👨‍⚕️ Se guardó al odontólogo: " + odontologo);
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

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologos.add(crearObjetoOdontologo(resultSet));
            }

            LOGGER.info("🦷 Listando todos los odontólogos: " + odontologos);
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

        return odontologos;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            connection.commit();
            LOGGER.info("🚮 Se ha eliminado al odontólogo con ID: " + id);
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
    public Odontologo buscarPorId(int id) {
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologo = crearObjetoOdontologo(resultSet);
            }

            LOGGER.info("👨‍⚕️ Se ha encontrado al odontólogo con ID " + id + ": " + odontologo);
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

        return odontologo;
    }

    @Override
    public Odontologo buscarPorCriterio(String criterio) {
        return null;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ODONTOLOGOS SET MATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID = ?");
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.setInt(4, odontologo.getId());
            preparedStatement.execute();

            connection.commit();
            LOGGER.warn("🛑 El odontólogo con ID " + odontologo.getId() + " ha sido actualizado: " + odontologo);
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

        return odontologo;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
        return new Odontologo(
                resultSet.getInt("ID"),
                resultSet.getString("MATRICULA"),
                resultSet.getString("NOMBRE"),
                resultSet.getString("APELLIDO")
        );
    }
}
