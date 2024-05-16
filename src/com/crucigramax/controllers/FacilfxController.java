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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Clase controladora FXML.
 *
 * Controlador para la vista facilfx.fxml.
 *
 * Esta clase controladora inicializa la vista facilfx.fxml, crea un crucigrama,
 * carga la matriz de caracteres del crucigrama, llena las listas de enunciados
 * y actualiza los campos de texto en la vista con la matriz de caracteres y
 * enunciados.
 *
 * @author a.cardenas
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
        
        
        if (!letrasIgualesAPosicionesOcupadas(crucigrama.getMatriz(), gridPane)) {
            App.mostrarPosicionesAleatorias(crucigrama.getMatriz(), gridPane, 1);
            score.setContadorAyudas(score.getContadorAyudas() + 1);
        } 

    }

}
