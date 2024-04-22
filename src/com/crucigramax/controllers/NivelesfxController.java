package com.crucigramax.controllers;

import com.crucigramax.services.Conexion;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Clase controladora FXML
 *
 * Esta clase controladora gestiona la lógica para la vista de los niveles del
 * juego. Permite manejar los eventos de clic en los botones de los niveles y
 * abrir la ventana correspondiente. Los niveles están representados por botones
 * con identificadores específicos.
 *
 * @author a.cardenas
 */
public class NivelesfxController implements Initializable {

    /**
     * Maneja el evento de click en un botón de nivel.
     *
     * @param event El evento de acción generado por el click en un botón.
     */
    @FXML
    private void manejarClick(ActionEvent event) {
        // Determina qué botón se le ha dado click
        if (event.getSource() instanceof Button clickedButton) {
            String level = null;
            // Asigna el nivel apropiado basado en el botón clicado
            if (clickedButton.getId().equals("facil")) {
                level = "facilfx";
            } else if (clickedButton.getId().equals("medio")) {
                level = "mediofx";
            } else if (clickedButton.getId().equals("dificil")) {
                level = "dificilfx";
            }
            // Abre el nivel correspondiente si se ha seleccionado un nivel válido
            if (level != null) {
                try {
                    abrirNiveles(level);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Abre la vista del nivel especificado.
     *
     * @param nivel El nombre del archivo FXML del nivel a abrir.
     * @throws IOException Si ocurre un error al cargar el archivo FXML del
     * nivel.
     */
    @FXML
    private void abrirNiveles(String nivel) throws IOException {
        // Establece la vista del nivel especificado
        App.setRoot(nivel);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
