package com.crucigramax.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase Conexion proporciona métodos estáticos para establecer una conexión a una base de datos PostgreSQL.
 */
public class Conexion {

    // URL de conexión a la base de datos PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/crucigramproject";

    // Nombre de usuario para acceder a la base de datos
    private static final String USER = "postgres";

    // Contraseña del usuario para acceder a la base de datos
    private static final String PASSWORD = "DmD020827*";

    /**
     * Este método estático establece una conexión a la base de datos PostgreSQL.
     *
     * @return La conexión establecida a la base de datos.
     * @throws SQLException Si ocurre un error al intentar establecer la conexión.
     */
    public static Connection conectar() throws SQLException {
        try {
            // Se utiliza el método getConnection() de la clase DriverManager para establecer la conexión
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("¡Conexión establecida correctamente!");
            return conn;
        } catch (SQLException e) {
            // En caso de error al conectar, se captura la excepción y se imprime un mensaje de error
            System.out.println("Error conectando a la base de datos: " + e.getMessage());
            throw e;
        }
    }
}
