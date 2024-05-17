package com.crucigramax.controllers;

import com.crucigramax.model.Crucigrama;
import com.crucigramax.model.Score;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 * Clase controladora FXML para el nivel Difícil.
 *
 * Controlador para la vista dificilfx.fxml.
 *
 * Esta clase controladora inicializa la vista dificilfx.fxml, crea un
 * crucigrama, carga la matriz de caracteres del crucigrama, llena las
 * listas de enunciados y actualiza los campos de texto en la vista con la
 * matriz de caracteres y enunciados.
 *
 * Autor: a.cardenas
 */
public class DificilfxController implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private TextArea cajaPistas;
    private Crucigrama crucigrama;
    private Score score;
    @FXML
    private MenuItem menuItemAcercaDe;

    /**
     * Inicializa la vista y carga el crucigrama.
     *
     * @param url La URL de inicialización.
     * @param rb Los recursos para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        crucigrama = App.crearCrucigrama();
        score = App.crearScore();

        try {
            crucigrama.cargarMatriz(App.obtenerCrucigrama());

        } catch (SQLException ex) {
            Logger.getLogger(DificilfxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        App.actualizarTextField(crucigrama.getMatriz(), gridPane);
        App.llenarPalabras(crucigrama, gridPane);
        crucigrama.llenarListas(crucigrama.getMatriz());
        App.aplicarValidacionATextFields(gridPane);
        App.mostrarEnunciados(crucigrama.getListaHorizontales(), crucigrama.getListaVerticales(), cajaPistas);
        menuItemAcercaDe.setOnAction(event -> mostrarDialogoAcercaDe());
    }

    /**
     * Valida las palabras ingresadas en el crucigrama.
     *
     * @param event El evento del mouse.
     * @throws IOException Si hay un error de entrada/salida.
     */
    @FXML
    private void validar(MouseEvent event) throws IOException {
        // Call the method from the Utility class
        App.validarTextField(crucigrama.getMatriz(), gridPane, score);
    }

    /**
     * Abre la vista para seleccionar un nuevo juego.
     *
     * @param event El evento del botón.
     * @throws IOException Si hay un error de entrada/salida.
     */
    @FXML
    private void nuevoJuego(ActionEvent event) throws IOException {
        // Cargar la vista de niveles
        App.setRoot("nivelesfx");
    }

    /**
     * Cierra la aplicación.
     *
     * @param event El evento del botón.
     */
    @FXML
    private void cerrar(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Muestra un diálogo con información sobre el juego.
     *
     * Este método muestra un diálogo con información sobre el juego.
     * Proporciona detalles sobre el funcionamiento del juego en el nivel
     * difícil.
     */
    private void mostrarDialogoAcercaDe() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Acerca De...");
        dialog.setHeaderText(null);
        dialog.initStyle(StageStyle.UTILITY);

        // Contenido del diálogo
        TextArea textArea = new TextArea();
        textArea.setText("Descripción del juego:\n"
                + "En el nivel difícil encontrará un tablero de 10*10 y comenzará con un puntaje de 100 puntos.\n"
                + "Este nivel no cuenta con ayudas, así que el tablero estará totalmente vacío.\n"
                + "Deberá tener cuidado cuando valide pues cada palabra incorrecta restará 5 puntos al puntaje inicial.\n"
                + "Cuando termine el juego, debe asignar un nickname para asociarle un Score correspondiente.");

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
