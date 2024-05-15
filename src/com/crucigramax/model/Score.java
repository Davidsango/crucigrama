package com.crucigramax.model;
public class Score {
    private int puntaje;
    private int errores;

    public Score() {
        this.puntaje = 100;
        this.errores = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getErrores() {
        return errores;
    }

    public void setErrores(int errores) {
        this.errores = errores;
    }

    public void incrementarErrores() {
        this.errores++;
        calcularPuntaje();
    }

    public void calcularPuntaje() {
        // Cada error resta 5 puntos
        this.puntaje = 100 - (errores * 5);
        // Asegurarse de que el puntaje no sea negativo
        if (this.puntaje < 0) {
            this.puntaje = 0;
        }
    }
}