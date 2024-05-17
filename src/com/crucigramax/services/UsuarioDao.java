package com.crucigramax.services;

import com.crucigramax.model.Usuario; //Importamos el usuario del modelo
import java.util.List;
/**
 * Esta interfaz define los métodos para interactuar con la base de datos para ingresar un usuario.
 * Maneja el método de insertar un usuario
 * 
 * Autor: Daniel Méndez
 */

 
public interface UsuarioDao {
    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario El usuario a insertar.
     */
       void insertarUsuario(Usuario usuario);
        /**
     * Obtiene los puntajes de los usuarios ordenados de manera descendente.
     *
     * @return Lista de usuarios con sus puntajes ordenados.
     */
    List<Usuario> obtenerPuntajesOrdenados();
}
