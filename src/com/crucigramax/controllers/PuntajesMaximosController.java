package com.crucigramax.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.crucigramax.model.Usuario;
import com.crucigramax.services.UsuarioDaoImpl;
import java.io.IOException;

import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Clase controladora FXML
 * 
 * Esta clase controladora gestiona la lógica para la vista de los puntajes máximos.
 * Permite cargar y mostrar los puntajes máximos de los usuarios en una tabla.
 */
public class PuntajesMaximosController {

    @FXML
    private TableView<Usuario> tablaPuntajes;

    @FXML
    private TableColumn<Usuario, String> colNickname;

    @FXML
    private TableColumn<Usuario, Integer> colPuntaje;

    /**
     * Inicializa la tabla de puntajes máximos.
     * 
     * Este método se llama automáticamente después de que se ha cargado el archivo FXML
     * y se utiliza para inicializar la tabla y configurar las columnas.
     */
    @FXML
    private void initialize() {
        // Configurar las propiedades de las columnas
        colNickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        colPuntaje.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getScore().get(0).getPuntajeFinal()).asObject());

        // Cargar los puntajes máximos
        cargarPuntajes();
    }

    /**
     * Carga los puntajes máximos desde la base de datos y los muestra en la tabla.
     * 
     * Este método obtiene los puntajes máximos de la base de datos y los muestra en la tabla.
     */
    private void cargarPuntajes() {
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        List<Usuario> puntajes = usuarioDao.obtenerPuntajesOrdenados();
        tablaPuntajes.getItems().setAll(puntajes);
    }

    /**
     * Maneja el evento de clic en el botón para volver a la vista de inicio.
     * 
     * Este método carga la vista de inicio cuando se hace clic en el botón "Volver".
     *
     * @throws IOException Si ocurre un error al cargar la vista de inicio.
     */
    @FXML
    private void volverAInicio() throws IOException {
        // Cargar la vista de inicio
        App.setRoot("iniciofx");
    }
}
