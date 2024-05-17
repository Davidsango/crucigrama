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
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

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
    }

    @FXML
    private void validar(MouseEvent event) throws IOException {
        // Call the method from the Utility class
        App.validarTextField(crucigrama.getMatriz(), gridPane, score);
    }

    @FXML
    private void ayuda(MouseEvent event) throws IOException {

        if (!App.letrasIgualesAPosicionesOcupadas(crucigrama.getMatriz(), gridPane)) {
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
    @FXML
    private void acercaDe() {
        App.mostrarAyuda("Ayuda", """
            Descripci\u00f3n del juego:
            En el nivel F\u00e1cil encontrar\u00e1 un tablero de 10*10 y comenzar\u00e1 con un puntaje de 100.
            En el habr\u00e1n algunas letras descubiertas para facilitar a la resoluci\u00f3n del crucigrama.
            Al dar clic sobre 'Ayuda' se revelar\u00e1 una letra de forma aleatoria.
            Al dar clic sobre 'Validar', se marcar\u00e1n con color verde las letras correctas y con color rojo las incorrectas.
            Por cada error perder\u00e1 5 puntos de su puntaje inicial.
            Cada ayuda solicitada restar\u00e1 5 puntos de su puntaje inicial.
            Cuando termine de llenar el juego y est\u00e9 correcto, debe ingresar su nickname, el cual no podr\u00e1 tener caracteres especiales.
            Le ser\u00e1 asignado un Score de acuerdo a los errores y ayudas utilizadas.
            """);
    }
}
