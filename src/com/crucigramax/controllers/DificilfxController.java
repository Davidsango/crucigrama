package com.crucigramax.controllers;

import com.crucigramax.model.Crucigrama;
import com.crucigramax.model.Pregunta;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class DificilfxController implements Initializable {

    @FXML private GridPane gridPane;
    @FXML private TextArea cajaPistas;

/**
 * Clase controladora FXML.
 *
 * Controlador para la vista dificilfx.fxml.
 *
 * Esta clase controladora inicializa la vista dificilfx.fxml, crea un crucigrama,
 * carga la matriz de caracteres del crucigrama, llena las listas de enunciados y
 * actualiza los campos de texto en la vista con la matriz de caracteres y enunciados.
 *
 * @author a.cardenas
 */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Pregunta> preguntas = new ArrayList<>();
        char[][] matriz = new char[10][10];
        List<String> listaVerticales = new ArrayList<>(), listaHorizontales = new ArrayList<>();

        Crucigrama crucigrama = new Crucigrama(matriz, preguntas, listaVerticales, listaHorizontales);

        crucigrama.cargarMatriz();

        App.actualizarTextField(crucigrama.getMatriz(), gridPane);
        App.aplicarValidacionATextFields(gridPane);
        App.mostrarEnunciados(crucigrama.getListaHorizontales(), crucigrama.getListaVerticales(), cajaPistas);

    }

}
