package com.crucigramax.controllers;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Clase controladora FXML Esta clase controladora gestiona la lógica para la
 * vista de los niveles del juego. Permite manejar los eventos de clic en los
 * botones de los niveles y abrir la ventana correspondiente. Los niveles están
 * representados por botones con identificadores específicos.
 */
public class NivelesfxController implements Initializable {

    /**
     * Inicializa el controlador después de que su elemento raíz haya sido
     * procesado por completo.
     *
     * Este método se llama automáticamente después de que el archivo FXML ha
     * sido cargado.
     *
     * @param url La ubicación utilizada para resolver rutas relativas para el
     * objeto raíz o null si no se usa.
     * @param rb El ResourceBundle que se puede utilizar para localizar objetos
     * de la interfaz de usuario, o null si no se necesita.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

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

    /**
     * Maneja el evento de clic en el botón para volver a la vista de inicio.
     *
     * Este método carga la vista de inicio cuando se hace clic en el botón
     * "Volver".
     *
     * @throws IOException Si ocurre un error al cargar la vista de inicio.
     */
    @FXML
    private void volverAInicio() throws IOException {
        // Cargar la vista de inicio
        App.setRoot("iniciofx");
    }

    /**
     * Maneja el evento de clic en el botón para cerrar la aplicación.
     *
     * Este método cierra la aplicación cuando se hace clic en el botón
     * "Cerrar".
     *
     * @param event El evento de acción generado por el clic en el botón.
     */
    @FXML
    private void cerrar(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Muestra el diálogo "Acerca De..." que proporciona información sobre el
     * juego.
     *
     * Este método crea y muestra un cuadro de diálogo con información sobre el
     * juego y sus niveles.
     */
    @FXML
    private void acercaDe() {
        App.mostrarAyuda("Ayuda", """
                                  Puede elegir entre los tres niveles de dificultad que se manejan, en los cuales se genera un tablero de 10x10 con un crucigrama totalmente aleatorio. Cuando las palabras sean correctas, se marcar\u00e1n con color verde las casillas. Las palabras incorrectas se resaltar\u00e1n en color rojo y por cada fallo se restar\u00e1n 5 puntos al puntaje inicial, que es de 100 puntos.
                                  
                                  F\u00e1cil: Al inicio del nivel se revelan letras de forma aleatoria y cuenta con una ayuda adicional para revelar letras.
                                  Medio: Cuenta con la misma ayuda que el nivel F\u00e1cil, pero no se revelan letras al inicio.
                                  Dif\u00edcil: El tablero est\u00e1 totalmente vac\u00edo y no cuenta con ayudas.
                                  Al finalizar cualquiera de los tres niveles deber\u00e1 ingresar un nickname. No puede continuar sin ello, pues con este apodo se le asignar\u00e1 un Score asociado que podr\u00e1 consultar en la secci\u00f3n de 'Puntajes M\u00e1ximos'.""");

    }

}
