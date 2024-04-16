package com.crucigramax.controllers;

import java.io.IOException;
import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación.
 *
 * Esta clase inicializa la aplicación JavaFX y carga la vista de inicio al
 * iniciar la aplicación. También proporciona métodos para cambiar la raíz de la
 * escena y cargar vistas FXML.
 *
 * @author a.cardenas
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Inicializa la aplicación y carga la vista de inicio.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar la vista de inicio.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("iniciofx"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Establece la raíz de la escena con la vista FXML especificada.
     *
     * @param fxml El nombre del archivo FXML que se va a cargar.
     * @throws IOException Si ocurre un error al cargar la vista FXML.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga y devuelve un nodo raíz de la vista FXML especificada.
     *
     * @param fxml El nombre del archivo FXML que se va a cargar.
     * @return El nodo raíz de la vista FXML.
     * @throws IOException Si ocurre un error al cargar la vista FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/crucigramax/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método principal de la aplicación.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }

}
