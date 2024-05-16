package com.crucigramax.model;

/**
 * La clase Score representa el puntaje de un jugador en un juego de crucigrama,
 * incluyendo el número de errores, el número de ayudas utilizadas y el puntaje
 * final.
 */
public class Score {

    private int contadorErrores; // El contador de errores del jugador
    private int contadorAyudas; // El contador de ayudas utilizadas por el jugador
    private int puntajeFinal; // El puntaje final del jugador

    /**
     * Constructor de la clase Score.
     *
     * @param contadorErrores El contador de errores del jugador.
     * @param contadorAyudas El contador de ayudas utilizadas por el jugador.
     * @param puntajeFinal El puntaje final del jugador.
     */
    public Score(int contadorErrores, int contadorAyudas, int puntajeFinal) {
        this.contadorErrores = contadorErrores;
        this.contadorAyudas = contadorAyudas;
        this.puntajeFinal = puntajeFinal;
    }

    /**
     * Obtiene el contador de errores del jugador.
     *
     * @return El contador de errores del jugador.
     */
    public int getContadorErrores() {
        return contadorErrores;
    }

    /**
     * Establece el contador de errores del jugador.
     *
     * @param contadorErrores El contador de errores del jugador a establecer.
     */
    public void setContadorErrores(int contadorErrores) {
        this.contadorErrores = contadorErrores;
    }

    /**
     * Obtiene el contador de ayudas utilizadas por el jugador.
     *
     * @return El contador de ayudas utilizadas por el jugador.
     */
    public int getContadorAyudas() {
        return contadorAyudas;
    }

    /**
     * Establece el contador de ayudas utilizadas por el jugador.
     *
     * @param contadorAyudas El contador de ayudas utilizadas por el jugador a
     * establecer.
     */
    public void setContadorAyudas(int contadorAyudas) {
        this.contadorAyudas = contadorAyudas;
    }

    /**
     * Obtiene el puntaje final del jugador.
     *
     * @return El puntaje final del jugador.
     */
    public int getPuntajeFinal() {
        return puntajeFinal;
    }

    /**
     * Establece el puntaje final del jugador.
     *
     * @param finalScore El puntaje final del jugador a establecer.
     */
    public void setPuntajeFinal(int finalScore) {
        this.puntajeFinal = finalScore;
    }
    /**
     * Obtiene el puntaje final del jugador. y lo almacena en el atributo
     * puntajeFinal
     *
     */
    public void calcularPuntaje() {
        // Cada error y ayuda restan 5 puntos
        setPuntajeFinal(getPuntajeFinal() - ((getContadorErrores() * 5)+(getContadorAyudas() * 5)));

        // Asegurarse de que el puntaje no sea negativo
        if (this.puntajeFinal < 0) {
            setPuntajeFinal(0);
        }
    }
}
