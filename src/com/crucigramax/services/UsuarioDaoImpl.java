package com.crucigramax.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.crucigramax.model.Usuario;
//import com.crucigramax.model.Score;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public void insertarUsuario(Usuario usuario) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establecer conexión a la base de datos
            connection = Conexion.conectar();

            // Consulta SQL para insertar el usuario en la base de datos
            String sql = "INSERT INTO usuarios (nickname) VALUES (?)";

            // Preparar la consulta para insertar el usuario
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usuario.getNickname());

            // Ejecutar la consulta para insertar el usuario
            statement.executeUpdate();

            // Obtener el ID del usuario insertado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Error al obtener el ID del usuario insertado.");
            }

            // Consulta SQL para insertar el puntaje del usuario en la base de datos
            sql = "INSERT INTO puntajes (usuario_id, puntaje) VALUES (?, ?)";

            // Preparar la consulta para insertar el puntaje
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, usuario.getScore().getPuntajeFinal());
            

            // Ejecutar la consulta para insertar el puntaje
            statement.executeUpdate();

            System.out.println("Usuario y puntaje insertados correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario y puntaje en la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión y el PreparedStatement
            try {
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

