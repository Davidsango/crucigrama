package com.crucigramax.controllers;

import com.crucigramax.model.Crucigrama;
import com.crucigramax.model.Pregunta;
import com.crucigramax.services.Conexion;
import com.crucigramax.services.CrucigramaDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
        stage.setWidth(800);
        stage.setHeight(600);
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
        String fxmlPath = "/com/crucigramax/view/" + fxml + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlPath));
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

    /**
     * Aplica la validación a todos los TextField dentro de un GridPane.
     *
     * @param gridPane El GridPane que contiene los TextField.
     */
    public static void aplicarValidacionATextFields(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof StackPane stackPane) {
                if (!stackPane.getChildren().isEmpty() && stackPane.getChildren().get(0) instanceof TextField) {
                    TextField textField = (TextField) stackPane.getChildren().get(0);
                    aplicarValidacion(textField);
                }
            }
        }
    }

    public static String obtenerCrucigrama() throws SQLException {
        Connection conn;
        conn = Conexion.conectar();
        CrucigramaDaoImpl dataBase = new CrucigramaDaoImpl(conn);
        String entrada = dataBase.cargarCrucigramaAleatorio();
        return entrada;

    }

    public static Crucigrama crearCrucigrama() {

        List<Pregunta> preguntas = new ArrayList<>();
        char[][] matriz = new char[10][10];
        List<String> listaVerticales = new ArrayList<>(), listaHorizontales = new ArrayList<>();
        return new Crucigrama(matriz, preguntas, listaVerticales, listaHorizontales);
    }

    /**
     * Aplica la validación a un TextField para permitir solo un carácter
     * alfabético.
     *
     * @param textField El TextField que se validará.
     */
    public static void aplicarValidacion(TextField textField) {
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

    /**
     * Actualiza los campos de texto en la vista con base en una matriz de
     * caracteres.
     *
     * @param matrix La matriz de caracteres que contiene los valores para
     * actualizar los campos de texto.
     * @param gridPane El GridPane que contiene los campos de texto a
     * actualizar. Se espera que cada campo de texto esté dentro de un StackPane
     * dentro del GridPane. El texto en cada campo de texto se actualizará según
     * el carácter correspondiente en la matriz. Los campos de texto con el
     * carácter '?' se mostrarán como no editables y con fondo negro.
     */
    public static void actualizarTextField(char[][] matrix, GridPane gridPane) {

        // Recorre el GridPane
        for (Node node : gridPane.getChildren()) {
            if (node instanceof StackPane stackPane) {
                if (!stackPane.getChildren().isEmpty() && stackPane.getChildren().get(0) instanceof TextField) {
                    TextField textField = (TextField) stackPane.getChildren().get(0);

                    // Obtiene el índice de fila y columna del TextField
                    int row = GridPane.getRowIndex(stackPane);
                    int col = GridPane.getColumnIndex(stackPane);

                    // Verifica si los índices de fila y columna son válidos
                    if (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length) {
                        // Actualiza el TextField en función del carácter en la matriz
                        char character = matrix[row][col];
                        textField.setText(""); // Limpia el texto
                        if (character == '?') {
                            textField.setEditable(false); // Establece editable en falso
                            textField.setStyle("-fx-background-color: black;"); // Cambia el color de fondo a negro
                            // Limpia la etiqueta asociada con el TextField
                            clearLabel(textField);
                        } else {
                            textField.setEditable(true); // Establece editable en verdadero
                            //this is for testing purpouses only:
                            textField.setText(String.valueOf(character));
                            textField.setStyle(""); // Restablece el color de fondo
                        }
                    }
                }
            }
        }
    }

    /**
     * Método para limpiar la etiqueta asociada con el TextField dado.
     *
     * @param textField El TextField cuya etiqueta asociada se va a limpiar. Se
     * espera que el TextField esté dentro de un StackPane que contiene la
     * etiqueta. La etiqueta se encuentra como un hijo del StackPane.
     */
    private static void clearLabel(TextField textField) {
        // Obtiene el padre del TextField
        Parent parent = textField.getParent();
        // Verifica si el padre es un StackPane
        if (parent instanceof StackPane stackPane) {
            // Obtiene los hijos del StackPane
            ObservableList<Node> children = stackPane.getChildren();
            // Itera sobre los hijos
            for (Node child : children) {
                // Verifica si el hijo es una etiqueta
                if (child instanceof Label label) {
                    // Limpia el texto de la etiqueta
                    label.setText("");
                    // Sale del bucle ya que se encontró la etiqueta
                    return;
                }
            }
        }
    }

    /**
     * Muestra los enunciados en el área de texto proporcionada.
     *
     * @param list1 La primera lista de enunciados a mostrar.
     * @param list2 La segunda lista de enunciados a mostrar.
     * @param cajaPistas El área de texto donde se mostrarán los enunciados.
     */
    public static void mostrarEnunciados(List<String> list1, List<String> list2, TextArea cajaPistas) {
        // Borra el contenido existente del área de texto
        cajaPistas.clear();
        // Habilita el ajuste de texto
        cajaPistas.setWrapText(true);

        // Agrega el título para la Lista 1 ("horizontales") con formato en negrita
        cajaPistas.appendText("Horizontales\n");
        // Agrega un separador entre el título y el contenido
        cajaPistas.appendText("--------------------\n");

        // Itera sobre la Lista 1 y agrega cada cadena al área de texto con números de línea
        for (int i = 0; i < list1.size(); i++) {
            String str = list1.get(i);
            // Agrega el número de línea y el elemento de la lista
            cajaPistas.appendText((i + 1) + ". " + str + "\n");
        }

        // Agrega un separador entre la Lista 1 y la Lista 2
        cajaPistas.appendText("\n");

        // Agrega el título para la Lista 2 ("verticales") con formato en negrita
        cajaPistas.appendText("Verticales\n");
        // Agrega un separador entre el título y el contenido
        cajaPistas.appendText("--------------------\n");

        // Itera sobre la Lista 2 y agrega cada cadena al área de texto con números de línea
        for (int i = 0; i < list2.size(); i++) {
            String str = list2.get(i);
            // Agrega el número de línea y el elemento de la lista
            cajaPistas.appendText((i + 1) + ". " + str + "\n");
        }
        // Mueve el cursor al principio del área de texto
        cajaPistas.positionCaret(0);

        // Esto mueve el cursor al inicio del área de texto. Si se desea asegurar que la
        // primera línea sea visible después de mover el cursor, también se puede establecer
        // la propiedad scrollTop en 0.
        cajaPistas.setScrollTop(0);
    }

    public static void llenarPalabras(Crucigrama crucigrama, GridPane gridPane) {
        Connection conn;
        try {
            conn = Conexion.conectar();
            CrucigramaDaoImpl cruci = new CrucigramaDaoImpl(conn);
            List<Pregunta> respuestas = new ArrayList<>();
            char[][] palabras = crucigrama.getMatriz();

            int horizontalWordOrder = 1; // Initialize horizontal word order
            int verticalWordOrder = 1; // Initialize vertical word order

            // Vertical traversal
            for (int col = 0; col < palabras[0].length; col++) {
                StringBuilder palabra = new StringBuilder();
                for (int row = 0; row < palabras.length; row++) {
                    char character = palabras[row][col];
                    if (character != '?') {
                        palabra.append(character);
                    } else {
                        if (palabra.length() >= 2) {
                            // Update the label adjacent to the first letter of each vertical word with the vertical word order
                            Node node = getNodeByRowColumnIndex(row - palabra.length(), col, gridPane);
                            if (node instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                                Label label = (Label) stackPane.getChildren().get(1); // Assuming the label is at index 1
                                String existingText = label.getText();
                                if (!existingText.isEmpty()) {
                                    label.setText(existingText + "/" + verticalWordOrder+"↓");
                                } else {
                                    label.setText(String.valueOf(verticalWordOrder+"↓"));
                                }
                                verticalWordOrder++; // Increment vertical word order for the next vertical word
                            }
                            // Retrieve the definition associated with the word from the database
                            String respuesta = palabra.toString().trim();
                            String enunciado = cruci.buscarDefinicion(respuesta);
                            respuestas.add(new Pregunta(respuesta, enunciado));
                        }
                        palabra.setLength(0);
                    }
                }
                if (palabra.length() >= 2) {
                    // Update the label adjacent to the first letter of each vertical word with the vertical word order
                    Node node = getNodeByRowColumnIndex(palabras.length - palabra.length(), col, gridPane);
                    if (node instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                        Label label = (Label) stackPane.getChildren().get(1); // Assuming the label is at index 1
                        String existingText = label.getText();
                        if (!existingText.isEmpty()) {
                            label.setText(existingText + "/" + verticalWordOrder+"↓");
                        } else {
                            label.setText(String.valueOf(verticalWordOrder+"↓"));
                        }
                        verticalWordOrder++; // Increment vertical word order for the next vertical word
                    }
                    // Retrieve the definition associated with the word from the database
                    String respuesta = palabra.toString().trim();
                    String enunciado = cruci.buscarDefinicion(respuesta);
                    respuestas.add(new Pregunta(respuesta, enunciado));
                }
            }

            // Horizontal traversal
            for (int row = 0; row < palabras.length; row++) {
                StringBuilder palabra = new StringBuilder();
                for (int col = 0; col < palabras[row].length; col++) {
                    char character = palabras[row][col];
                    if (character != '?') {
                        palabra.append(character);
                    } else {
                        if (palabra.length() >= 2) {
                            // Update the label adjacent to the first letter of each horizontal word with the horizontal word order
                            Node node = getNodeByRowColumnIndex(row, col - palabra.length(), gridPane);
                            if (node instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                                Label label = (Label) stackPane.getChildren().get(1); // Assuming the label is at index 1
                                String existingText = label.getText();
                                if (!existingText.isEmpty()) {
                                    label.setText(existingText + "/" + horizontalWordOrder+"→");
                                } else {
                                    label.setText(String.valueOf(horizontalWordOrder+"→"));
                                }
                                horizontalWordOrder++; // Increment horizontal word order for the next horizontal word
                            }
                            // Retrieve the definition associated with the word from the database
                            String respuesta = palabra.toString().trim();
                            String enunciado = cruci.buscarDefinicion(respuesta);
                            respuestas.add(new Pregunta(respuesta, enunciado));
                        }
                        palabra.setLength(0);
                    }
                }
                if (palabra.length() >= 2) {
                    // Update the label adjacent to the first letter of each horizontal word with the horizontal word order
                    Node node = getNodeByRowColumnIndex(row, palabras[row].length - palabra.length(), gridPane);
                    if (node instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                        Label label = (Label) stackPane.getChildren().get(1); // Assuming the label is at index 1
                        String existingText = label.getText();
                        if (!existingText.isEmpty()) {
                            label.setText(existingText + "/" + horizontalWordOrder+"→");
                        } else {
                            label.setText(String.valueOf(horizontalWordOrder+"→"));
                        }
                        horizontalWordOrder++; // Increment horizontal word order for the next horizontal word
                    }
                    // Retrieve the definition associated with the word from the database
                    String respuesta = palabra.toString().trim();
                    String enunciado = cruci.buscarDefinicion(respuesta);
                    respuestas.add(new Pregunta(respuesta, enunciado));
                }
            }

            // Add the generated questions to the existing list
            crucigrama.setPreguntas(respuestas);

        } catch (SQLException ex) {
            Logger.getLogger(DificilfxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Helper method to find the node in the GridPane by row and column index
    private static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

}
