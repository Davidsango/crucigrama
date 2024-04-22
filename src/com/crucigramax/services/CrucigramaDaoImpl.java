package com.crucigramax.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Implementación concreta de la interfaz CrucigramaDAO que proporciona métodos para interactuar con la base de datos
 * y obtener información relacionada con crucigramas.
 */
public class CrucigramaDaoImpl extends Conexion implements CrucigramaDao {
    private Connection connection;

    /**
     * Constructor de la clase CrucigramaDAOImp.
     *
     * @param connection La conexión a la base de datos que se utilizará para ejecutar las consultas.
     */
    public CrucigramaDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método para cargar un crucigrama aleatorio desde la base de datos.
     *
     * @return Una cadena que representa el crucigrama cargado.
     */
    @Override
    public String cargarCrucigramaAleatorio() {
        String datosCrucigrama = null;
        String query = "SELECT crucigrama FROM preguntas ORDER BY RANDOM() LIMIT 1";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                datosCrucigrama = rs.getString("crucigrama");
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar el crucigrama: " + e.getMessage());
        }
        return datosCrucigrama;
    }

    /**
     * Método para buscar la definición de una palabra en la base de datos.
     *
     * @param respuesta La palabra cuya definición se desea buscar.
     * @return El enunciado de la palabra especificada, o un mensaje de error si la definición no se encuentra.
     */
    @Override
    public String buscarDefinicion(String respuesta) {
        String enunciado = "Enunciado no encontrada para: " + respuesta;
        respuesta = respuesta.toUpperCase();
        String query = "SELECT definicion FROM crucigrama WHERE palabra = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, respuesta);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    enunciado = rs.getString("enunciado");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el enunciado: " + e.getMessage());
        }
        return enunciado;
    }
}