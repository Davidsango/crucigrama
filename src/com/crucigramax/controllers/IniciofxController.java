package com.crucigramax.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Clase controladora FXML
 *
 * Esta clase controladora gestiona la lógica para la vista de inicio del juego.
 * Permite abrir la vista de los niveles del juego al hacer clic en un botón.
 *
 * @author a.cardenas
 */
public class IniciofxController implements Initializable {

    /**
     * Abre la vista de los niveles del juego.
     *
     * @throws IOException Si ocurre un error al cargar la vista de los niveles.
     */
    @FXML
    private void abrirNiveles() throws IOException {
        // Establece la vista de los niveles
        App.setRoot("/com/crucigramax/fxml/nivelesfx");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
