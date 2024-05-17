package com.crucigramax.controllers;

import static com.crucigramax.controllers.App.letrasIgualesAPosicionesOcupadas;
import com.crucigramax.model.Crucigrama;
import com.crucigramax.model.Score;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

/**
 * Clase controladora FXML para el nivel Fácil.
 *
 * Controlador para la vista facilfx.fxml.
 *
 * Esta clase controladora inicializa la vista facilfx.fxml, crea un crucigrama,
 * carga la matriz de caracteres del crucigrama, llena las listas de enunciados
 * y actualiza los campos de texto en la vista con la matriz de caracteres y
 * enunciados.
 */
public class FacilfxController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private TextArea cajaPistas;
    private Crucigrama crucigrama;
    private Score score;
    @FXML
    private MenuItem menuItemAcercaDe;

    /**
     * @param url
     * @param rb
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
        App.mostrarPosicionesAleatorias(crucigrama.getMatriz(), gridPane, 20);
        menuItemAcercaDe.setOnAction(event -> mostrarDialogoAcercaDe());

    }

    @FXML
    private void validar(MouseEvent event) throws IOException {
        // Call the method from the Utility class
        App.validarTextField(crucigrama.getMatriz(), gridPane, score);
    }

    @FXML
    private void ayuda(MouseEvent event) throws IOException {

        if (!letrasIgualesAPosicionesOcupadas(crucigrama.getMatriz(), gridPane)) {
            App.mostrarPosicionesAleatorias(crucigrama.getMatriz(), gridPane, 1);
            score.setContadorAyudas(score.getContadorAyudas() + 1);
        }

    }

    @FXML
    private void nuevoJuego(ActionEvent event) throws IOException {
        // Cargar la vista de niveles
        App.setRoot("nivelesfx");
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Muestra un diálogo con información sobre el juego.
     *
     * Este método muestra un diálogo con información sobre el juego.
     * Proporciona detalles sobre el funcionamiento del juego en el nivel fácil.
     */
    private void mostrarDialogoAcercaDe() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Acerca De...");
        dialog.setHeaderText(null);
        dialog.initStyle(StageStyle.UTILITY);

        // Contenido del diálogo
        TextArea textArea = new TextArea();
        textArea.setText("Descripción del juego:\n"
                + "En el nivel Fácil encontrará un tablero de 10*10 y comenzará con un puntaje de 100.\n"
                + "En el habrán algunas palabras descubiertas para facilitar a la resolución del crucigrama.\n"
                + "Al dar clic sobre 'Ayuda' puede solicitar más palabras.\n"
                + "Al dar clic sobre 'Validar', se marcarán con color verde las palabras correctas y con color rojo las incorrectas.\n"
                + "Por cada error perderá 5 puntos de su puntaje inicial.\n"
                + "Cada ayuda solicitada restará 5 puntos de su puntaje inicial.\n"
                + "Cuando termine de llenar el juego y esté correcto, debe ingresar su nickname, el cual no podrá tener caracteres especiales.\n"
                + "Le será asignado un Score de acuerdo a los errores y ayudas utilizadas.");

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
        ButtonType closeButton = new ButtonType("Cerrar", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(closeButton);

        // Mostrar el diálogo
        dialog.showAndWait();
    }       
}