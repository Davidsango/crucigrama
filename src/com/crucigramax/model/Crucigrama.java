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
    private void setPreguntas(List<Pregunta> preguntas) {
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
     * @param entrada La cadena de entrada.
     * @throws IllegalArgumentException Si la longitud de la cadena de entrada
     * no es 100.
     */
    public void cargarMatriz(String entrada) {
        // Calcula la longitud de la cadena de entrada utilizando el método codePointCount de la clase Character.
        int length = Character.codePointCount(entrada, 0, entrada.length());

        // Verifica si la longitud de la cadena de entrada es exactamente 100.
        // Si no es así, lanza una excepción IllegalArgumentException con un mensaje indicando el requisito de longitud.
        if (length != 100) {
            throw new IllegalArgumentException("La cadena de entrada debe tener exactamente 100 caracteres.");
        }

        // Crea una matriz de caracteres de 10x10 para almacenar los caracteres de la entrada.
        char[][] matriz = new char[10][10];

        // Llena la matriz con los caracteres de la cadena de entrada.
        // Utiliza un índice para recorrer la cadena y asignar cada carácter a la posición correspondiente en la matriz.
        int index = 0;
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                matriz[fila][columna] = entrada.charAt(index++);
            }
        }
        //carga la matriz con su setter.
        setMatriz(matriz);
    }

    /**
     * Genera la lista de palabras horizontales del crucigrama y sus respectivos
     * enunciados.
     *
     * @param matriz La matriz de caracteres del crucigrama.
     */
    public void llenarHorizontales(char[][] matriz) {
        List<StringBuilder> enunciadosTemporales = new ArrayList<>();
        for (char[] fila : matriz) {
            List<String> palabrasTemporales = new ArrayList<>();
            StringBuilder palabra = new StringBuilder();
            for (char c : fila) {
                if (c != '?') {
                    palabra.append(c);
                } else {
                    if (palabra.length() >= 2) {
                        // Busca una pregunta con respuesta coincidente
                        boolean encontrado = false;
                        for (Pregunta pregunta : this.getPreguntas()) {
                            if (pregunta.getRespuesta().equalsIgnoreCase(palabra.toString())) {
                                palabrasTemporales.add(pregunta.getEnunciado());
                                encontrado = true;
                                break; // Detener la búsqueda después de encontrar una coincidencia
                            }
                        }
                        if (!encontrado) {
                            palabrasTemporales.add(". "); // Agregar cadena vacía si no se encuentra una coincidencia
                        }
                    }
                    palabra.setLength(0); // Restablecer la palabra independientemente de si es una palabra o no
                }
            }
            // Agrega cualquier palabra restante a palabrasTemporales
            if (palabra.length() >= 2) {
                // Busca una pregunta con respuesta coincidente
                boolean encontrado = false;
                for (Pregunta pregunta : this.getPreguntas()) {
                    if (pregunta.getRespuesta().equalsIgnoreCase(palabra.toString())) {
                        palabrasTemporales.add(pregunta.getEnunciado());
                        encontrado = true;
                        break; // Detener la búsqueda después de encontrar una coincidencia
                    }
                }
                if (!encontrado) {
                    palabrasTemporales.add(". "); // Agregar cadena vacía si no se encuentra una coincidencia
                }
            }
            // Agrega palabrasTemporales a enunciadosTemporales como una línea
            if (!palabrasTemporales.isEmpty()) {
                StringBuilder enunciadoLine = new StringBuilder();
                for (String palabraStr : palabrasTemporales) {
                    if (!palabraStr.isEmpty()) {
                        enunciadoLine.append(palabraStr).append(". ");
                    }
                }
                if (enunciadoLine.length() > 0) {
                    enunciadoLine.setLength(enunciadoLine.length() - 2); // Elimina el último ". "
                    enunciadosTemporales.add(enunciadoLine);
                }
            }
        }
        // Convierte enunciadosTemporales en una lista de Strings
        List<String> enunciados = new ArrayList<>();
        for (StringBuilder enunciadoLine : enunciadosTemporales) {
            enunciados.add(enunciadoLine.toString());
        }
        //Agrega los enunciados a la lista para ser mostrados en pantalla
        setListaHorizontales(enunciados);
    }

    /**
     * Genera la lista de palabras verticales del crucigrama y sus respectivos
     * enunciados.
     *
     * @param matriz La matriz de caracteres del crucigrama.
     */
    public void llenarVerticales(char[][] matriz) {
        List<StringBuilder> enunciadosTemporales = new ArrayList<>();
        for (int i = 0; i < matriz[0].length; i++) {
            List<String> palabrasTemporales = new ArrayList<>();
            StringBuilder palabra = new StringBuilder();
            for (char[] columna : matriz) {
                char c = columna[i];
                if (c != '?') {
                    palabra.append(c);
                } else {
                    if (palabra.length() >= 2) {
                        // Busca una pregunta con respuesta coincidente
                        boolean encontrado = false;
                        for (Pregunta pregunta : this.getPreguntas()) {
                            if (pregunta.getRespuesta().equalsIgnoreCase(palabra.toString())) {
                                palabrasTemporales.add(pregunta.getEnunciado());
                                encontrado = true;
                                break; // Detener la búsqueda después de encontrar una coincidencia
                            }
                        }
                        if (!encontrado) {
                            palabrasTemporales.add("."); // Agregar cadena vacía si no se encuentra una coincidencia
                        }
                    }
                    palabra.setLength(0); // Restablecer la palabra independientemente de si es una palabra o no
                }
            }
            // Agrega cualquier palabra restante a palabrasTemporales
            if (palabra.length() >= 2) {
                // Busca una pregunta con respuesta coincidente
                boolean encontrado = false;
                for (Pregunta pregunta : this.getPreguntas()) {
                    if (pregunta.getRespuesta().equalsIgnoreCase(palabra.toString())) {
                        palabrasTemporales.add(pregunta.getEnunciado());
                        encontrado = true;
                        break; // Detener la búsqueda después de encontrar una coincidencia
                    }
                }
                if (!encontrado) {
                    palabrasTemporales.add("."); // Agregar cadena vacía si no se encuentra una coincidencia
                }
            }
            // Agrega palabrasTemporales a enunciadosTemporales como una línea
            if (!palabrasTemporales.isEmpty()) {
                StringBuilder enunciadoLine = new StringBuilder();
                for (String palabraStr : palabrasTemporales) {
                    if (!palabraStr.isEmpty()) {
                        enunciadoLine.append(palabraStr).append(". ");
                    }
                }
                if (enunciadoLine.length() > 0) {
                    enunciadoLine.setLength(enunciadoLine.length() - 2); // Elimina el último ". "
                    enunciadosTemporales.add(enunciadoLine);
                }
            }
        }
        // Convierte enunciadosTemporales en una lista de Strings
        List<String> enunciados = new ArrayList<>();
        for (StringBuilder enunciadoLine : enunciadosTemporales) {
            enunciados.add(enunciadoLine.toString());
        }
        //Agrega los enunciados a la lista para ser mostrados en pantalla
        setListaVerticales(enunciados);
    }

}
