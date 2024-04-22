package com.crucigramax.model;

/**
 * La clase Pregunta representa una pregunta en un crucigrama, que consta de un
 * enunciado y una respuesta.
 */
public class Pregunta {

    private String enunciado; // El enunciado de la pregunta
    private String respuesta; // La respuesta de la pregunta

    /**
     * Constructor de la clase Pregunta.
     *
     * @param enunciado El enunciado de la pregunta.
     * @param respuesta La respuesta de la pregunta.
     */
    public Pregunta(String respuesta, String enunciado) {
        this.respuesta = respuesta;
        this.enunciado = enunciado;
    }

    /**
     * Obtiene el enunciado de la pregunta.
     *
     * @return El enunciado de la pregunta.
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Establece el enunciado de la pregunta.
     *
     * @param enunciado El enunciado de la pregunta a establecer.
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    /**
     * Obtiene la respuesta de la pregunta.
     *
     * @return La respuesta de la pregunta.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta de la pregunta.
     *
     * @param respuesta La respuesta de la pregunta a establecer.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
