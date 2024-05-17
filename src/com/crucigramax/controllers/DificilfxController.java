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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
    @FXML
    private void acercaDe() {
        App.mostrarAyuda("Ayuda", """
                                  Descripci\u00f3n del juego:
                                  En el nivel dif\u00edcil encontrar\u00e1 un tablero de 10*10 y comenzar\u00e1 con un puntaje de 100 puntos.
                                  Este nivel no cuenta con ayudas, as\u00ed que el tablero estar\u00e1 totalmente vac\u00edo.
                                  Deber\u00e1 tener cuidado cuando valide pues cada palabra incorrecta restar\u00e1 5 puntos al puntaje inicial.
                                  Cuando termine el juego, debe asignar un nickname para asociarle un Score correspondiente.""");

    }
}
