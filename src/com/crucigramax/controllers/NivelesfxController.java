package com.crucigramax.controllers;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 * Clase controladora FXML Esta clase controladora gestiona la lógica para la
 * vista de los niveles del juego. Permite manejar los eventos de clic en los
 * botones de los niveles y abrir la ventana correspondiente. Los niveles están
 * representados por botones con identificadores específicos.
 */
public class NivelesfxController implements Initializable {

    @FXML
    private MenuItem menuItemAcercaDe;

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
     * Inicializa el controlador después de que su elemento raíz haya sido procesado por completo.
     * 
     * Este método se llama automáticamente después de que el archivo FXML ha sido cargado.
     *
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz o null si no se usa.
     * @param rb El ResourceBundle que se puede utilizar para localizar objetos de la interfaz de usuario, o null si no se necesita.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuItemAcercaDe.setOnAction(event -> mostrarDialogoAcercaDe());
    }
     /**
     * Maneja el evento de clic en el botón para volver a la vista de inicio.
     * 
     * Este método carga la vista de inicio cuando se hace clic en el botón "Volver".
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
     * Este método cierra la aplicación cuando se hace clic en el botón "Cerrar".
     *
     * @param event El evento de acción generado por el clic en el botón.
     */
    @FXML
    private void cerrar(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * Muestra el diálogo "Acerca De..." que proporciona información sobre el juego.
     * 
     * Este método crea y muestra un cuadro de diálogo con información sobre el juego y sus niveles.
     */

    private void mostrarDialogoAcercaDe() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Acerca De...");
        dialog.setHeaderText(null);
        dialog.initStyle(StageStyle.UTILITY);

        // Contenido del diálogo
        TextArea textArea = new TextArea();
        textArea.setText(
                "Puede elegir entre los tres niveles de dificultad que se manejan, en los cuales se genera un tablero de 10*10 "
                + "con un crucigrama totalmente aleatorio. Cuando las palabras sean correctas, se marcarán con color verde las casillas. "
                + "Las palabras incorrectas se resaltarán en color rojo y por cada fallo se restarán 5 puntos al puntaje inicial, que es de 100 puntos.\n\n"
                + "- Fácil: Cuenta con algunas palabras descubiertas además de ayudas.\n"
                + "- Medio: Cuenta con menos palabras descubiertas que el nivel fácil y además sigue contando con ayudas.\n"
                + "- Difícil: El tablero está totalmente vacío y no cuenta con ayudas.\n\n"
                + "Al finalizar cualquiera de los tres niveles deberá ingresar un nickname. No puede continuar sin ello, pues con "
                + "este apodo se le asignará un Score asociado que podrá consultar en la sección de “Puntajes Máximos”."
        );

        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane gridPane = new GridPane();
        gridPane.add(textArea, 0, 0);

        dialog.getDialogPane().setContent(gridPane);

        // Botón de cierre
        ButtonType closeButton = new ButtonType("Cerrar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(closeButton);

        // Mostrar el diálogo
        dialog.showAndWait();
    }

}
