package com.crucigramax.services;

/**
 * Esta interfaz define los métodos para interactuar con la base de datos y obtener información relacionada con crucigramas.
 * Implementaciones de esta interfaz deben proporcionar funcionalidad para cargar crucigramas aleatorios y buscar definiciones de palabras.
 * 
 * Autor: Daniel Méndez
 */
public interface CrucigramaDao {

    /**
     * Carga un crucigrama aleatorio desde la base de datos.
     *
     * @return Una cadena que representa el crucigrama cargado.
     */
    String cargarCrucigramaAleatorio();

    /**
     * Busca la definición de una palabra en la base de datos.
     *
     * @param enunciado La palabra cuya definición se desea buscar.
     * @return La definición de la palabra especificada.
     */
    String buscarDefinicion(String enunciado);
}