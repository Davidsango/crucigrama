package com.crucigramax.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 * Clase controladora FXML
 *
 * Esta clase controladora gestiona la lógica para la vista de inicio del juego.
 * Permite abrir la vista de los niveles del juego al hacer clic en un botón.
 *
 * @author a.cardenas
 */
public class IniciofxController implements Initializable {
     @FXML
    private MenuItem menuItemAcercaDe;
    /**
     * Abre la vista de los niveles del juego.
     *
     * @throws IOException Si ocurre un error al cargar la vista de los niveles.
     */
    @FXML
    private void abrirNiveles() throws IOException {
        // Establece la vista de los niveles
        App.setRoot("nivelesfx");
    }
    
     @FXML
     public void mostrarPuntajesMaximos() throws IOException {
         // Establece la vista para los puntajes máximos
         App.setRoot("PuntajesMaximos");
     }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuItemAcercaDe.setOnAction(event -> mostrarDialogoAcercaDe());
    }
    
    @FXML
    private void cerrar(ActionEvent event) {
        Platform.exit();
    }
    
    private void mostrarDialogoAcercaDe() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Acerca De...");
        dialog.setHeaderText(null);
        dialog.initStyle(StageStyle.UTILITY);

        // Contenido del diálogo
        TextArea textArea = new TextArea();
        textArea.setText(
        "Es una aplicación de crucigramas desarrollada en Java con JavaFX. Que permite a los usuarios jugar este famoso " +
        "rompecabezas formado por filas de casillas que se entrecruzan y en las que hay que completar palabras de acuerdo " +
        "con el enunciado.\n\n" +
        "Para completar este proyecto se manejo el patrón de diseño Modelo, Vista y Controlador (MVC), un patrón de " +
        "arquitectura de software, que separa los datos y principalmente lo que es la lógica de negocio de una aplicación " +
        "de su representación y el módulo encargado de gestionar los eventos y las comunicaciones.\n\n" +
        "Para el diseño de la Base de Datos se realizó en PostgreSQL con su gestor PgAdmin4 y el JDBC correspondiente para " +
        "realizar la conexión con Java, para lo cual se usó otro patrón conocido como Data Access Object (DAO), el cual " +
        "permite separar la lógica de acceso a datos de los Objetos de negocios de tal forma que el DAO encapsula toda la " +
        "lógica de acceso de datos al resto de la aplicación.\n\n" +
         
        "Puede elegir entre en jugar un 'Nuevo Juego' y elegir entre los tres nivels de dificultad (Fácil, Medio y Díficl) "
         + "o puede optar por  visualizar los 'Putajes Máximos' para ver los mejores puntajes de los usuarios registrados"
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
