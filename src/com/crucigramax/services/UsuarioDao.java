package com.crucigramax.services;

import com.crucigramax.model.Usuario; //Importamos el usuario del modelo

/**
 * @author Daaniel Méndez
 */
public interface UsuarioDao {
     /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario El usuario a insertar.
     */
    void insertarUsuario(Usuario usuario);    
}
