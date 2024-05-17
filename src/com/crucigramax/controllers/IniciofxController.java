package com.crucigramax.controllers;

import com.crucigramax.controllers.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Clase controladora FXML
 *
 * Esta clase controladora gestiona la lógica para la vista de inicio del juego.
 * Permite abrir la vista de los niveles del juego al hacer clic en un botón.
 *
 * @author a.cardenas
 */
public class IniciofxController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

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

    @FXML
    private void cerrar(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Muestra un diálogo con información sobre el juego.
     */
    @FXML
    private void acercaDe() {
        App.mostrarAyuda("Ayuda", """
        Es una aplicaci\u00f3n de crucigramas desarrollada en Java con JavaFX. Permite a los usuarios jugar este famoso rompecabezas formado por filas de casillas que se entrecruzan y en las que hay que completar palabras de acuerdo con el enunciado.
        Para completar este proyecto se manej\u00f3 el patr\u00f3n de dise\u00f1o Modelo-Vista-Controlador (MVC), un patr\u00f3n de arquitectura de software que separa los datos y principalmente la l\u00f3gica de negocio de una aplicaci\u00f3n de su representaci\u00f3n y el m\u00f3dulo encargado de gestionar los eventos y las comunicaciones.
        Para el dise\u00f1o de la Base de Datos se utiliz\u00f3 PostgreSQL con su gestor PgAdmin4 y el JDBC correspondiente para realizar la conexi\u00f3n con Java. Para ello, se us\u00f3 otro patr\u00f3n conocido como Data Access Object (DAO), que permite separar la l\u00f3gica de acceso a datos de los objetos de negocio, encapsulando toda la l\u00f3gica de acceso de datos al resto de la aplicaci\u00f3n.
        Puede elegir jugar un 'Nuevo Juego' y seleccionar entre los tres niveles de dificultad (F\u00e1cil, Medio y Dif\u00edcil) o puede optar por visualizar los 'Puntajes M\u00e1ximos' para ver los mejores puntajes de los usuarios registrados.
        """);
    }
}
