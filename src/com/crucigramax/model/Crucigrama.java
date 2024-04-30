package com.crucigramax.model;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Crucigrama representa un juego de crucigrama, que consta de una
 * matriz de caracteres, una lista de preguntas, y listas de palabras verticales
 * y horizontales.
 */
public class Crucigrama {

    private char[][] matriz; // Matriz de caracteres del crucigrama
    private List<Pregunta> preguntas; // Lista de preguntas asociadas al crucigrama
    private List<String> listaVerticales; // Lista de palabras verticales del crucigrama
    private List<String> listaHorizontales; // Lista de palabras horizontales del crucigrama

    /**
     * Constructor de la clase Crucigrama.
     *
     * @param matriz Matriz de caracteres del crucigrama.
     * @param preguntas Lista de preguntas asociadas al crucigrama.
     * @param listaVerticales Lista de palabras verticales del crucigrama.
     * @param listaHorizontales Lista de palabras horizontales del crucigrama.
     */
    public Crucigrama(char[][] matriz, List<Pregunta> preguntas, List<String> listaVerticales, List<String> listaHorizontales) {
        this.matriz = matriz;
        this.preguntas = preguntas;
        this.listaVerticales = listaVerticales;
        this.listaHorizontales = listaHorizontales;
    }

    /**
     * Obtiene la matriz de caracteres del crucigrama.
     *
     * @return La matriz de caracteres del crucigrama.
     */
    public char[][] getMatriz() {
        return matriz;
    }

    /**
     * Establece la matriz de caracteres del crucigrama.
     *
     * @param matriz La matriz de caracteres del crucigrama.
     */
    private void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    /**
     * Obtiene la lista de preguntas asociadas al crucigrama.
     *
     * @return La lista de preguntas asociadas al crucigrama.
     */
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * Establece la lista de preguntas asociadas al crucigrama.
     *
     * @param preguntas La lista de preguntas asociadas al crucigrama.
     */
    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * Obtiene la lista de palabras verticales del crucigrama.
     *
     * @return La lista de palabras verticales del crucigrama.
     */
    public List<String> getListaVerticales() {
        return listaVerticales;
    }

    /**
     * Establece la lista de palabras verticales del crucigrama.
     *
     * @param listaVerticales La lista de palabras verticales del crucigrama.
     */
    private void setListaVerticales(List<String> listaVerticales) {
        this.listaVerticales = listaVerticales;
    }

    /**
     * Obtiene la lista de palabras horizontales del crucigrama.
     *
     * @return La lista de palabras horizontales del crucigrama.
     */
    public List<String> getListaHorizontales() {
        return listaHorizontales;
    }

    /**
     * Establece la lista de palabras horizontales del crucigrama.
     *
     * @param listaHorizontales La lista de palabras horizontales del
     * crucigrama.
     */
    private void setListaHorizontales(List<String> listaHorizontales) {
        this.listaHorizontales = listaHorizontales;
    }

    /**
     * Crea una matriz de caracteres a partir de una cadena de entrada. La
     * longitud de la cadena de entrada debe ser exactamente 100 caracteres.
     *
     * @param entrada: El crucigrama se representa en una sola linea de String
     * almacenada en la base de datos, el cual se organiza en la matríz de 10x10
     * @throws IllegalArgumentException Si la longitud de la cadena de entrada
     * no es 100.
     */
    public void cargarMatriz(String entrada) {

        int length = Character.codePointCount(entrada, 0, entrada.length());

        // Verifica si la longitud de la cadena de entrada es exactamente 100
        if (length != 100) {
            // Si no es 100, lanza una excepción indicando el requisito de longitud
            throw new IllegalArgumentException("La cadena de entrada debe tener exactamente 100 caracteres.");
        }

        // Crea una matriz de caracteres de 10x10 para almacenar los caracteres de la entrada
        char[][] cruci = new char[10][10];

        // Llena la matriz con los caracteres de la cadena de entrada
        // Utiliza un índice para recorrer la cadena y asignar cada carácter a la posición correspondiente en la matriz
        int index = 0;
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                cruci[fila][columna] = entrada.charAt(index++);
            }
        }

        // Establece la matriz creada en el objeto actual
        this.setMatriz(cruci);
    }
    
    /**
     * Genera las listas de palabras horizontales y verticales del crucigrama y
     * sus respectivos enunciados.
     *
     * @param cruci La matriz de caracteres del crucigrama.
     */
    public void llenarListas(char[][] cruci) {
        List<String> enunciadosHorizontales = new ArrayList<>();
        List<String> enunciadosVerticales = new ArrayList<>();

        StringBuilder palabraHorizontal;
        StringBuilder palabraVertical;

        // Procesar palabras horizontales
        for (char[] cruci1 : cruci) {
            palabraHorizontal = new StringBuilder();
            for (int j = 0; j < cruci[0].length; j++) {
                char cHorizontal = cruci1[j];
                if (cHorizontal != '?') {
                    palabraHorizontal.append(cHorizontal);
                } else {
                    if (palabraHorizontal.length() >= 2) {
                        for (Pregunta pregunta : getPreguntas()) {
                            if (pregunta.getRespuesta().equalsIgnoreCase(palabraHorizontal.toString())) {
                                enunciadosHorizontales.add(pregunta.getEnunciado());
                                break;
                            }
                        }
                    }
                    palabraHorizontal.setLength(0);
                }
            }
            // Verificar la última palabra en la fila
            if (palabraHorizontal.length() >= 2) {
                for (Pregunta pregunta : getPreguntas()) {
                    if (pregunta.getRespuesta().equalsIgnoreCase(palabraHorizontal.toString())) {
                        enunciadosHorizontales.add(pregunta.getEnunciado());
                        break;
                    }
                }
            }
        }

        // Procesar palabras verticales
        for (int j = 0; j < cruci[0].length; j++) {
            palabraVertical = new StringBuilder();
            for (char[] cruci1 : cruci) {
                char cVertical = cruci1[j];
                if (cVertical != '?') {
                    palabraVertical.append(cVertical);
                } else {
                    if (palabraVertical.length() >= 2) {
                        for (Pregunta pregunta : getPreguntas()) {
                            if (pregunta.getRespuesta().equalsIgnoreCase(palabraVertical.toString())) {
                                enunciadosVerticales.add(pregunta.getEnunciado());
                                break;
                            }
                        }
                    }
                    palabraVertical.setLength(0);
                }
            }
            // Verificar la última palabra en la columna
            if (palabraVertical.length() >= 2) {
                for (Pregunta pregunta : getPreguntas()) {
                    if (pregunta.getRespuesta().equalsIgnoreCase(palabraVertical.toString())) {
                        enunciadosVerticales.add(pregunta.getEnunciado());
                        break;
                    }
                }
            }
        }

        // Asignar las listas de enunciados a sus respectivos destinos
        this.setListaHorizontales(enunciadosHorizontales);
        this.setListaVerticales(enunciadosVerticales);
    }

}
