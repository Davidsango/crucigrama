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
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                Node node = obtenerNodoPorIndiceFilaColumna(row, col, gridPane);
                if (node instanceof StackPane stackPane && !stackPane.getChildren().isEmpty() && stackPane.getChildren().get(0) instanceof TextField) {
                    TextField textField = (TextField) stackPane.getChildren().get(0);
                    char character = matrix[row][col];
                    textField.setText("");
                    if (character == '?') {
                        textField.setEditable(false);
                        textField.setStyle("-fx-background-color: black;");
                        clearLabel(textField);
                    } else {
                        textField.setEditable(true);
                        // Testing purposes only:
                        textField.setText(String.valueOf(character));
                        textField.setStyle("");
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

    /**
     * Llena las palabras del crucigrama en el GridPane y recopila las
     * definiciones asociadas desde la base de datos.
     *
     * @param crucigrama El crucigrama que se está llenando.
     * @param gridPane El GridPane en el que se colocarán las palabras del
     * crucigrama.
     */
    public static void llenarPalabras(Crucigrama crucigrama, GridPane gridPane) {
        Connection conn;
        try {
            conn = Conexion.conectar();
            CrucigramaDaoImpl cruci = new CrucigramaDaoImpl(conn);
            List<Pregunta> respuestas = new ArrayList<>();
            char[][] palabras = crucigrama.getMatriz();

            int ordenPalabraHorizontal = 1; // Inicializar el orden de palabra horizontal
            int ordenPalabraVertical = 1; // Inicializar el orden de palabra vertical

            // Recorrido vertical
            for (int col = 0; col < palabras[0].length; col++) {
                StringBuilder palabra = new StringBuilder();
                for (int row = 0; row < palabras.length; row++) {
                    char character = palabras[row][col];
                    if (character != '?') {
                        palabra.append(character);
                    } else {
                        if (palabra.length() >= 2) {
                            // Actualizar la etiqueta adyacente a la primera letra de cada palabra vertical con el orden de palabra vertical
                            Node nodo = obtenerNodoPorIndiceFilaColumna(row - palabra.length(), col, gridPane);
                            if (nodo instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                                Label etiqueta = (Label) stackPane.getChildren().get(1); // Suponiendo que la etiqueta está en el índice 1
                                String textoExistente = etiqueta.getText();
                                if (!textoExistente.isEmpty()) {
                                    etiqueta.setText(textoExistente + "/" + ordenPalabraVertical + "↓");
                                } else {
                                    etiqueta.setText(String.valueOf(ordenPalabraVertical + "↓"));
                                }
                                ordenPalabraVertical++; // Incrementar el orden de palabra vertical para la siguiente palabra vertical
                            }
                            // Obtener la definición asociada con la palabra desde la base de datos
                            String respuesta = palabra.toString().trim();
                            String enunciado = cruci.buscarDefinicion(respuesta);
                            respuestas.add(new Pregunta(respuesta, enunciado));
                        }
                        palabra.setLength(0);
                    }
                }
                if (palabra.length() >= 2) {
                    // Actualizar la etiqueta adyacente a la primera letra de cada palabra vertical con el orden de palabra vertical
                    Node nodo = obtenerNodoPorIndiceFilaColumna(palabras.length - palabra.length(), col, gridPane);
                    if (nodo instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                        Label etiqueta = (Label) stackPane.getChildren().get(1); // Suponiendo que la etiqueta está en el índice 1
                        String textoExistente = etiqueta.getText();
                        if (!textoExistente.isEmpty()) {
                            etiqueta.setText(textoExistente + "/" + ordenPalabraVertical + "↓");
                        } else {
                            etiqueta.setText(String.valueOf(ordenPalabraVertical + "↓"));
                        }
                        ordenPalabraVertical++; // Incrementar el orden de palabra vertical para la siguiente palabra vertical
                    }
                    // Obtener la definición asociada con la palabra desde la base de datos
                    String respuesta = palabra.toString().trim();
                    String enunciado = cruci.buscarDefinicion(respuesta);
                    respuestas.add(new Pregunta(respuesta, enunciado));
                }
            }
            // Recorrido horizontal
            for (int row = 0; row < palabras.length; row++) {
                StringBuilder palabra = new StringBuilder();
                for (int col = 0; col < palabras[row].length; col++) {
                    char character = palabras[row][col];
                    if (character != '?') {
                        palabra.append(character);
                    } else {
                        if (palabra.length() >= 2) {
                            // Actualizar la etiqueta adyacente a la primera letra de cada palabra horizontal con el orden de palabra horizontal
                            Node nodo = obtenerNodoPorIndiceFilaColumna(row, col - palabra.length(), gridPane);
                            if (nodo instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                                Label etiqueta = (Label) stackPane.getChildren().get(1); // Suponiendo que la etiqueta está en el índice 1
                                String textoExistente = etiqueta.getText();
                                if (!textoExistente.isEmpty()) {
                                    etiqueta.setText(textoExistente + "/" + ordenPalabraHorizontal + "→");
                                } else {
                                    etiqueta.setText(String.valueOf(ordenPalabraHorizontal + "→"));
                                }
                                ordenPalabraHorizontal++; // Incrementar el orden de palabra horizontal para la siguiente palabra horizontal
                            }
                            // Obtener la definición asociada con la palabra desde la base de datos
                            String respuesta = palabra.toString().trim();
                            String enunciado = cruci.buscarDefinicion(respuesta);
                            respuestas.add(new Pregunta(respuesta, enunciado));
                        }
                        palabra.setLength(0);
                    }
                }
                if (palabra.length() >= 2) {
                    // Actualizar la etiqueta adyacente a la primera letra de cada palabra horizontal con el orden de palabra horizontal
                    Node nodo = obtenerNodoPorIndiceFilaColumna(row, palabras[row].length - palabra.length(), gridPane);
                    if (nodo instanceof StackPane stackPane && !stackPane.getChildren().isEmpty()) {
                        Label etiqueta = (Label) stackPane.getChildren().get(1); // Suponiendo que la etiqueta está en el índice 1
                        String textoExistente = etiqueta.getText();
                        if (!textoExistente.isEmpty()) {
                            etiqueta.setText(textoExistente + "/" + ordenPalabraHorizontal + "→");
                        } else {
                            etiqueta.setText(String.valueOf(ordenPalabraHorizontal + "→"));
                        }
                        ordenPalabraHorizontal++; // Incrementar el orden de palabra horizontal para la siguiente palabra horizontal
                    }
                    // Obtener la definición asociada con la palabra desde la base de datos
                    String respuesta = palabra.toString().trim();
                    String enunciado = cruci.buscarDefinicion(respuesta);
                    respuestas.add(new Pregunta(respuesta, enunciado));
                }
            }
            // Agregar las preguntas generadas a la lista existente
            crucigrama.setPreguntas(respuestas);

        } catch (SQLException ex) {
            Logger.getLogger(DificilfxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método auxiliar para encontrar el nodo en el GridPane por índice de fila
     * y columna.
     *
     * @param fila El índice de fila del nodo a buscar.
     * @param columna El índice de columna del nodo a buscar.
     * @param gridPane El GridPane en el que buscar el nodo.
     * @return El nodo encontrado, o null si no se encuentra ningún nodo con los
     * índices especificados.
     */
    private static Node obtenerNodoPorIndiceFilaColumna(final int fila, final int columna, GridPane gridPane) {
        Node resultado = null;
        ObservableList<Node> hijos = gridPane.getChildren();

        // Iterar sobre los nodos hijos del GridPane
        for (Node nodo : hijos) {
            // Verificar si el nodo tiene el índice de fila y columna especificado
            if (GridPane.getRowIndex(nodo) == fila && GridPane.getColumnIndex(nodo) == columna) {
                resultado = nodo;
                break;
            }
        }

        return resultado;
    }

    /**
     * Valida los TextField dentro de un GridPane comparándolos con una matriz
     * de caracteres. Si un TextField contiene el mismo carácter que su
     * contraparte en la matriz, lo resalta en verde. Si no, lo resalta en rojo.
     * Además, muestra un mensaje si todos los campos no vacíos son correctos.
     *
     * @param matriz La matriz de caracteres con la que se compararán los
     * TextField.
     * @param gridPane El GridPane que contiene los TextField a validar.
     */
    public static void validarTextField(char[][] matriz, GridPane gridPane) throws IOException {
        boolean todosCamposCorrectos = true; // Bandera para verificar si todos los campos no vacíos están correctos

        // Iterar sobre cada celda de la matriz y su TextField correspondiente en el GridPane
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                Node nodo = obtenerNodoPorIndiceFilaColumna(fila, columna, gridPane);
                if (nodo instanceof StackPane stackPane && !stackPane.getChildren().isEmpty() && stackPane.getChildren().get(0) instanceof TextField) {
                    TextField textField = (TextField) stackPane.getChildren().get(0);
                    char caracter = matriz[fila][columna];
                    String textoTextField = textField.getText().toUpperCase(); // Convertir a mayúsculas
                    if (!textoTextField.isEmpty()) {
                        // El TextField no está vacío
                        if (caracter != '?') {
                            // El carácter en la matriz no es '?' (es decir, no se ignora)
                            if (textoTextField.charAt(0) == caracter) {
                                // El contenido del TextField es igual al de la matriz
                                textField.setStyle("-fx-background-color: #32CD32;");
                            } else {
                                // El contenido del TextField es diferente al de la matriz
                                textField.setStyle("-fx-background-color: red;");
                                todosCamposCorrectos = false; // Al menos un campo no está correcto
                            }
                        } else {
                            // El carácter en la matriz es '?', se ignora
                            textField.setStyle(""); // Restablecer estilo
                        }
                    } else if (caracter != '?') {
                        // El TextField está vacío y el carácter en la matriz no es '?', se marca como incorrecto
                        textField.setStyle("-fx-background-color: red;");
                        todosCamposCorrectos = false;
                    }
                }
            }
        }

        // Verificar si todos los campos no vacíos están correctos
        if (todosCamposCorrectos) {
            // Mostrar mensaje de juego completado
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Juego completado");
            alerta.setHeaderText(null);
            alerta.setContentText("¡Felicidades! Has completado el juego.");
            alerta.showAndWait();
            setRoot("nivelesfx");
        }
    }

}
