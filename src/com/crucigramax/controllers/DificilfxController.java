package com.crucigramax.controllers;

import com.crucigramax.model.Crucigrama;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class DificilfxController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private TextArea cajaPistas;
    private Crucigrama crucigrama;

    /**
     * Clase controladora FXML.
     *
     * Controlador para la vista dificilfx.fxml.
     *
     * Esta clase controladora inicializa la vista dificilfx.fxml, crea un
     * crucigrama, carga la matriz de caracteres del crucigrama, llena las
     * listas de enunciados y actualiza los campos de texto en la vista con la
     * matriz de caracteres y enunciados.
     *
     * @param url
     * @param rb
     * @author a.cardenas
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        crucigrama = App.crearCrucigrama();

        try {
            crucigrama.cargarMatriz(App.obtenerCrucigrama());

        } catch (SQLException ex) {
            Logger.getLogger(DificilfxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        App.actualizarTextField(crucigrama.getMatriz(), gridPane);
        App.llenarPalabras(crucigrama,gridPane);
        crucigrama.llenarListas(crucigrama.getMatriz());
        App.aplicarValidacionATextFields(gridPane);
        App.mostrarEnunciados(crucigrama.getListaHorizontales(), crucigrama.getListaVerticales(), cajaPistas);

    }

}
