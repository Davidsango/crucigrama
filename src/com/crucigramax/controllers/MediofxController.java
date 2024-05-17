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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Clase controladora FXML para el nivel medio.
 *
 * Controlador para la vista mediofx.fxml.
 *
 * Esta clase controladora inicializa la vista mediofx.fxml, crea un crucigrama,
 * carga la matriz de caracteres del crucigrama, llena las listas de enunciados
 * y actualiza los campos de texto en la vista con la matriz de caracteres y
 * enunciados.
 */
public class MediofxController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private TextArea cajaPistas;
    private Crucigrama crucigrama;
    private Score score;
    
    /**
     * Inicializa el controlador después de que se haya cargado el archivo FXML.
     *
     * Este método se llama automáticamente después de que se ha cargado el
     * archivo FXML y se utiliza para inicializar la vista y cargar el
     * crucigrama.
     *
     * @param url La ubicación para resolver las rutas relativas de recursos.
     * @param rb El objeto ResourceBundle que se puede usar para localizar
     * objetos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar el crucigrama y el score
        crucigrama = App.crearCrucigrama();
        score = App.crearScore();

        try {
            // Cargar la matriz del crucigrama desde la base de datos
            crucigrama.cargarMatriz(App.obtenerCrucigrama());

        } catch (SQLException ex) {
            Logger.getLogger(DificilfxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Actualizar la vista con la matriz del crucigrama y los enunciados
        App.actualizarTextField(crucigrama.getMatriz(), gridPane);
        App.llenarPalabras(crucigrama, gridPane);
        crucigrama.llenarListas(crucigrama.getMatriz());
        App.aplicarValidacionATextFields(gridPane);
        App.mostrarEnunciados(crucigrama.getListaHorizontales(), crucigrama.getListaVerticales(), cajaPistas);
    }

    /**
     * Maneja el evento de clic del mouse en el área del crucigrama.
     *
     * Este método se activa cuando se hace clic en el área del crucigrama.
     * Valida las palabras ingresadas por el usuario.
     *
     * @param event El evento de clic del mouse.
     * @throws IOException Si ocurre un error al validar las palabras.
     */
    @FXML
    private void validar(MouseEvent event) throws IOException {
        // Llamar al método de utilidad para validar las palabras ingresadas
        App.validarTextField(crucigrama.getMatriz(), gridPane, score);
    }

    /**
     * Maneja el evento de clic del mouse en el botón de ayuda.
     *
     * Este método se activa cuando se hace clic en el botón de ayuda. Muestra
     * posiciones aleatorias en el crucigrama como ayuda.
     *
     * @param event El evento de clic del mouse.
     * @throws IOException Si ocurre un error al mostrar la ayuda.
     */
    @FXML
    private void ayuda(MouseEvent event) throws IOException {
        // Verificar si las letras ingresadas son iguales a las posiciones ocupadas
        if (!App.letrasIgualesAPosicionesOcupadas(crucigrama.getMatriz(), gridPane)) {
            // Mostrar posiciones aleatorias en el crucigrama
            App.mostrarPosicionesAleatorias(crucigrama.getMatriz(), gridPane, 1);
            // Incrementar el contador de ayudas en el score
            score.setContadorAyudas(score.getContadorAyudas() + 1);
        }
    }

    /**
     * Maneja el evento de clic del mouse en el botón para iniciar un nuevo
     * juego.
     *
     * Este método se activa cuando se hace clic en el botón para iniciar un
     * nuevo juego. Carga la vista de niveles.
     *
     * @param event El evento de clic del mouse.
     * @throws IOException Si ocurre un error al cargar la vista de niveles.
     */
    @FXML
    private void nuevoJuego(ActionEvent event) throws IOException {
        // Cargar la vista de niveles
        App.setRoot("nivelesfx");
    }

    /**
     * Maneja el evento de clic en el botón para cerrar la aplicación.
     *
     * Este método se activa cuando se hace clic en el botón para cerrar la
     * aplicación. Cierra la aplicación.
     *
     * @param event El evento de clic del botón.
     */
    @FXML
    private void cerrar(ActionEvent event) {
        // Cerrar la aplicación
        Platform.exit();
    }

    /**
     * Muestra un diálogo con información sobre el juego.
     *
     * Este método muestra un diálogo con información sobre el juego.
     * Proporciona detalles sobre el funcionamiento del juego en el nivel medio.
     */
    @FXML
    private void acercaDe() {
        App.mostrarAyuda("Ayuda", """
        Descripci\u00f3n del juego:
        En el nivel medio, encontrar\u00e1 un tablero de 10*10 y comenzar\u00e1 con el puntaje en 100.
        Al dar clic sobre 'Ayuda' se revelar\u00e1 una letra aleatoria .
        Al dar clic sobre 'Validar', se marcar\u00e1n con color verde las letras correctas y con color rojo las incorrectas.
        Por cada error perder\u00e1 5 puntos de su puntaje inicial.
        Cada ayuda solicitada restar\u00e1 5 puntos de su puntaje inicial.
        Cuando termine de llenar el juego y est\u00e9 correcto, debe ingresar su nickname, el cual no podr\u00e1 tener caracteres especiales.
        Le ser\u00e1 asignado un Score de acuerdo a los errores y ayudas utilizadas.
        """);
    }
}
