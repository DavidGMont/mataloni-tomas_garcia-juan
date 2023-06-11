package com.matalonigarcia.clinicaodontologica.repository.impl;

import com.matalonigarcia.clinicaodontologica.entity.Domicilio;
import com.matalonigarcia.clinicaodontologica.repository.H2Connection;
import com.matalonigarcia.clinicaodontologica.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDaoH2 implements IDao<Domicilio> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DomicilioDaoH2.class);

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                domicilio.setId(generatedKeys.getInt(1));
            }

            connection.commit();
            LOGGER.info("🏡 Se guardó el domicilio: {}", domicilio);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: {}", e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("💥 Tuvimos un problema con el registro: {}", e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    LOGGER.error("💥 Hay un problema con SQL: {}", ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: {}", e.getMessage());
                e.printStackTrace();
            }
        }

        return domicilio;
    }

    @Override
    public List<Domicilio> listarTodos() {
        List<Domicilio> domicilios = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM DOMICILIOS");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                domicilios.add(crearObjetoDomicilio(resultSet));
            }

            LOGGER.info("🏡 Listando todos los domicilios: {}", domicilios);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: {}", e.getMessage());
                e.printStackTrace();
            }
        }

        return domicilios;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM DOMICILIOS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            connection.commit();
            LOGGER.info("🚮 Se ha eliminado el domicilio con ID: {}", id);

        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: {}", e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("💥 Tuvimos un problema con el registro: {}", e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    LOGGER.error("💥 Hay un problema con SQL: {}", ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: {}", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public Domicilio buscarPorId(int id) {
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                domicilio = crearObjetoDomicilio(resultSet);
            }

            LOGGER.info("🏡 Se ha encontrado el domicilio con ID {}: {}", id, domicilio);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: {}", e.getMessage());
                e.printStackTrace();
            }
        }

        return domicilio;
    }

    @Override
    public Domicilio buscarPorCriterio(String criterio) {
        return null;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DOMICILIOS SET CALLE = ?, NUMERO = ?, LOCALIDAD = ?, PROVINCIA = ? WHERE ID = ?");
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setInt(5, domicilio.getId());
            preparedStatement.execute();

            connection.commit();
            LOGGER.warn("🛑 El domicilio con ID {} ha sido actualizado: {}", domicilio.getId(), domicilio);
        } catch (Exception e) {
            LOGGER.error("💥 Te encontraste con un gran error: {}", e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("💥 Tuvimos un problema con el registro: {}", e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    LOGGER.error("💥 Hay un problema con SQL: {}", ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (Exception e) {
                LOGGER.error("🚫 No se pudo cerrar la conexión: {}", e.getMessage());
                e.printStackTrace();
            }
        }

        return domicilio;
    }

    private Domicilio crearObjetoDomicilio(ResultSet resultSet) throws SQLException {
        return new Domicilio(
                resultSet.getInt("ID"),
                resultSet.getString("CALLE"),
                resultSet.getInt("NUMERO"),
                resultSet.getString("LOCALIDAD"),
                resultSet.getString("PROVINCIA")
        );
    }
}
