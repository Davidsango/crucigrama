package com.crucigramax.model;

import java.util.List;

/**
 * Clase que representa a un usuario del juego.
 */
public class Usuario {
    private String nickname;
    private List<Score> scores;

    /**
     * Constructor de la clase Usuario.
     * 
     * @param nickname El apodo del usuario.
     * @param scores La lista de puntajes del usuario.
     */
    public Usuario(String nickname, List<Score> scores) {
        this.nickname = nickname;
        this.scores = scores;
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
     * @param nickname El nuevo apodo del usuario.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Obtiene la lista de puntajes del usuario.
     * 
     * @return La lista de puntajes del usuario.
     */
    public List<Score> getScore() {
        return scores;
    }

    /**
     * Establece la lista de puntajes del usuario.
     * 
     * @param scores La nueva lista de puntajes del usuario.
     */
    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
