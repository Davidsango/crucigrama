package com.crucigramax.model;

/**
 * La clase Score representa el puntaje de un jugador en un juego de crucigrama,
 * incluyendo el número de errores, el número de ayudas utilizadas y el puntaje
 * final.
 */
public class Score {

    private int contadorErrores; // El contador de errores del jugador
    private int contadorAyudas; // El contador de ayudas utilizadas por el jugador
    private int finalScore; // El puntaje final del jugador

    /**
     * Constructor de la clase Score.
     *
     * @param contadorErrores El contador de errores del jugador.
     * @param contadorAyudas El contador de ayudas utilizadas por el jugador.
     * @param finalScore El puntaje final del jugador.
     */
    public Score(int contadorErrores, int contadorAyudas, int finalScore) {
        this.contadorErrores = contadorErrores;
        this.contadorAyudas = contadorAyudas;
        this.finalScore = finalScore;
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
    public int getFinalScore() {
        return finalScore;
    }

    /**
     * Establece el puntaje final del jugador.
     *
     * @param finalScore El puntaje final del jugador a establecer.
     */
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    /**
     * Calcula el puntaje del jugador basado en el número de errores y ayudas
     * utilizadas.
     *
     * @param errores El número de errores del jugador.
     * @param ayudas El número de ayudas utilizadas por el jugador.
     * @return El puntaje calculado del jugador.
     */
    private int calcularScore(int errores, int ayudas) {
        // TODO implementar el cálculo del puntaje aquí
        return 0;
    }

    /**
     * Muestra una ayuda para el jugador.
     *
     * @return Un arreglo que representa la ayuda proporcionada al jugador.
     */
    private int[] mostrarAyuda() {
        // TODO implementar la lógica para mostrar una ayuda aquí
        return null;
    }

}
