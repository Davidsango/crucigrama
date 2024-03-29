package com.crucigramax.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author a.cardenas
 */
public class FacilfxController{
    
    @FXML
    private GridPane gridPane; 
    /**
     * Referencia al GridPane que contiene todos los recuadros del crucigrama
     * cada espacio del GridPane contiene un StackPane, que a su vez contiene
     * un TextField y un label (para poder mostrar el número de filas/columnas)
     */

    /**
     * Inicializa el controlador. Este método se llama automáticamente después de que
     * se ha cargado el archivo FXML.
     */
    public void initialize() {
        applyValidationToTextFields(gridPane);
    }

    /**
     * Aplica la validación a todos los TextField dentro de un GridPane.
     * @param gridPane El GridPane que contiene los TextField.
     */
    private void applyValidationToTextFields(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof StackPane stackPane) {
                if (!stackPane.getChildren().isEmpty() && stackPane.getChildren().get(0) instanceof TextField)
                {
                    TextField textField = (TextField) stackPane.getChildren().get(0);
                    applyValidation(textField);
                }
            }
        }
    }

    /**
     * Aplica la validación a un TextField para permitir solo un carácter alfabético.
     * @param textField El TextField que se validará.
     */
    private void applyValidation(TextField textField) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue.length() > 1 || (!newValue.isEmpty() && !newValue.matches("[A-Za-z]"))) {
                textField.setText(oldValue);
                    // Mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese solo un carácter alfabético.");

            alert.showAndWait();
            }
        });
    }

    

}
