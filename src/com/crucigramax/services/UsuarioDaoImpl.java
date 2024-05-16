package com.crucigramax.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.crucigramax.model.Usuario;
import com.crucigramax.model.Score;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public void insertarUsuario(Usuario usuario) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos
            connection = Conexion.conectar();

            // Consulta SQL para buscar el usuario por nickname
            String sqlSelectUsuario = "SELECT id FROM usuarios WHERE nickname = ?";

            // Preparar la consulta para buscar el usuario por nickname
            statement = connection.prepareStatement(sqlSelectUsuario);
            statement.setString(1, usuario.getNickname());

            // Ejecutar la consulta para buscar el usuario por nickname
            resultSet = statement.executeQuery();

            int userId;
            if (resultSet.next()) {
                // Si el usuario ya existe, obtener su ID
                userId = resultSet.getInt("id");
            } else {
                // Si el usuario no existe, insertarlo en la base de datos
                String sqlInsertUsuario = "INSERT INTO usuarios (nickname) VALUES (?)";

                statement = connection.prepareStatement(sqlInsertUsuario, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, usuario.getNickname());

                // Ejecutar la consulta para insertar el usuario
                statement.executeUpdate();

                // Obtener el ID del usuario insertado
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    userId = resultSet.getInt(1);
                } else {
                    throw new SQLException("Error al obtener el ID del usuario insertado.");
                }
            }

            // Consulta SQL para insertar los puntajes del usuario en la base de datos
            String sqlPuntaje = "INSERT INTO puntajes (usuario_id, puntaje) VALUES (?, ?)";

            // Preparar la consulta para insertar el puntaje
            statement = connection.prepareStatement(sqlPuntaje);

            for (Score score : usuario.getScore()) {
                statement.setInt(1, userId);
                statement.setInt(2, score.getPuntajeFinal());
                statement.addBatch();
            }

            // Ejecutar la consulta para insertar los puntajes
            statement.executeBatch();

            System.out.println("Usuario y puntajes insertados correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario y puntajes en la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión, el PreparedStatement y el ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}
