package com.crucigramax.model;

import java.util.*;

/**
 * La clase Usuario representa un usuario que participa en el juego de
 * crucigrama, con un apodo (nickname) y una lista de puntajes obtenidos.
 */
public class Usuario {

    private String nickname; // El apodo del usuario
    private List<Score> puntaje; // La lista de puntajes obtenidos por el usuario

    /**
     * Constructor de la clase Usuario.
     *
     * @param nickname El apodo del usuario.
     * @param puntaje La lista de puntajes obtenidos por el usuario.
     */
    public Usuario(String nickname, List<Score> puntaje) {
        this.nickname = nickname;
        this.puntaje = puntaje;
    }

    /**
     * Obtiene el apodo del usuario.
     *
     * @return El apodo del usuario.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Establece el apodo del usuario.
     *
     * @param nickname El apodo del usuario a establecer.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Obtiene la lista de puntajes obtenidos por el usuario.
     *
     * @return La lista de puntajes obtenidos por el usuario.
     */
    public List<Score> getPuntaje() {
        return puntaje;
    }

    /**
     * Establece la lista de puntajes obtenidos por el usuario.
     *
     * @param puntaje La lista de puntajes obtenidos por el usuario a
     * establecer.
     */
    public void setPuntaje(List<Score> puntaje) {
        this.puntaje = puntaje;
    }

}
