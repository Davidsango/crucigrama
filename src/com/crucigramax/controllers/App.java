package com.crucigramax.controllers;

import com.crucigramax.model.Crucigrama;
import com.crucigramax.model.Pregunta;
import com.crucigramax.model.Score; // Importa la clase Score si aún no lo has hecho
import com.crucigramax.model.Usuario;
import com.crucigramax.services.Conexion;
import com.crucigramax.services.CrucigramaDaoImpl;
import com.crucigramax.services.UsuarioDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

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
    // Instancia de Score

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

    public static Score crearScore() {

        int contadorErrores = 0;
        int contadorAyudas = 0;
        int finalScore = 100;
        return new Score(contadorErrores, contadorAyudas, finalScore);
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
                        // Quitar comentario solo para pruebas:
                        //textField.setText(String.valueOf(character));
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
    public static Node obtenerNodoPorIndiceFilaColumna(final int fila, final int columna, GridPane gridPane) {
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
     * @param score El objeto donde se almacenará el puntaje
     * @throws java.io.IOException
     */
    public static void validarTextField(char[][] matriz, GridPane gridPane, Score score) throws IOException {
        boolean todosCamposCorrectos = true; // Variable para verificar si todos los campos no vacíos están correctos
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
                                textField.setEditable(false); // Establecer el TextField como no editable
                            } else {
                                // El contenido del TextField es diferente al de la matriz
                                textField.setStyle("-fx-background-color: red;");
                                todosCamposCorrectos = false; // Al menos un campo no está correcto
                                // Incrementar contador de errores y recalcular puntaje
                                score.setContadorErrores(score.getContadorErrores() + 1);
                            }
                        } else {
                            // El carácter en la matriz es '?', se ignora
                            textField.setStyle(""); // Restablecer estilo
                        }
                    } else if (caracter != '?') {
                        /* El TextField está vacío y el carácter en la matriz no es '?'
                    // no se marca como incorrecto
                    // pero aun faltan valores por ingresar
                         */
                        todosCamposCorrectos = false;
                    }
                }
            }
        }
        //Si todos los campos son correctos, actualiza puntaje en base de datos
        if (todosCamposCorrectos) {
            mostrarMensajeJuegoCompletado(score);
        }
    }

    /**
     * Muestra un mensaje de juego completado y solicita al usuario que ingrese
     * su nickname. Si el usuario proporciona un nickname válido, se crea un
     * objeto Usuario con el nickname y el puntaje actual, y se guarda en la
     * base de datos. Luego se muestra un mensaje con el puntaje final y la
     * cantidad de errores y ayudas. Si el usuario cancela la operación, se
     * muestra un mensaje en la consola.
     *
     * @param gridPane El panel de la cuadrícula donde se muestra el juego.
     * @param score El objeto Score que contiene la puntuación actual del
     * usuario.
     * @throws IOException Si ocurre un error durante la ejecución del método.
     */
    private static void mostrarMensajeJuegoCompletado(Score score) throws IOException {

        // Mostrar mensaje de juego completado
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Juego completado");
        alerta.setHeaderText(null);
        alerta.setContentText("¡Felicidades! Has completado el juego.");
        alerta.showAndWait();

        // Solicitar al usuario que ingrese su nickname
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingresar Nickname");
        dialog.setHeaderText(null);
        dialog.setContentText("Por favor, ingresa tu nickname:");
        Optional<String> result = dialog.showAndWait();
        String nickname;

        // Verificar si el usuario proporciona un nickname válido
        if (result.isPresent()) {
            nickname = result.get();
            if (validarNickname(nickname)) {
                // Crear un objeto Usuario con el nickname y el puntaje actual
                Usuario usuario = new Usuario(nickname, score);
                UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

                // Mostrar mensaje de puntaje final y cantidad de errores y ayudas
                Alert alertaPuntaje = new Alert(AlertType.INFORMATION);
                alertaPuntaje.setTitle("Puntaje");
                alertaPuntaje.setHeaderText(null);
                score.calcularPuntaje();
                alertaPuntaje.setContentText("Felicidades " + nickname + ". Tu puntaje es de: " + score.getPuntajeFinal() + " puntos.\nCantidad de errores: " + score.getContadorErrores() + "\nCantidad de ayudas: " + score.getContadorAyudas());
                alertaPuntaje.showAndWait();

                // Insertar el usuario en la base de datos
                usuarioDao.insertarUsuario(usuario);
                setRoot("iniciofx");
            } else {
                // Mostrar mensaje de error si el nickname no es válido
                Alert alertaError = new Alert(AlertType.ERROR);
                alertaError.setTitle("Error");
                alertaError.setHeaderText(null);
                alertaError.setContentText("El nickname debe tener al menos 3 caracteres y no contener caracteres especiales.");
                alertaError.showAndWait();
            }
        } else {
            // El usuario canceló la operación
            System.out.println("El usuario canceló la operación.");
        }
    }

    private static boolean validarNickname(String nickname) {
        // Verificar que el nickname tenga al menos 3 caracteres y no contenga caracteres especiales
        return nickname.length() >= 3 && nickname.matches("^[a-zA-Z0-9]+$");
    }

/**
 * Muestra posiciones aleatorias de una matriz en un GridPane.
 * 
 * @param matriz    La matriz de caracteres de la que se seleccionarán las posiciones.
 * @param gridPane  El GridPane en el que se mostrarán las posiciones.
 * @param posiciones    El número de posiciones aleatorias que se mostrarán.
 */
    public static void mostrarPosicionesAleatorias(char[][] matriz, GridPane gridPane, int posiciones) {
        Random random = new Random();

        int posicionesAsignadas = 0;
        int fila, columna;
        // Obtener las posiciones ocupadas en el GridPane
        List<Pair<Integer, Integer>> posicionesOcupadas = obtenerPosicionesOcupadas(gridPane);

        // Verificar si no se encontraron posiciones vacías en el GridPane
        if (letrasIgualesAPosicionesOcupadas(matriz, gridPane)) {
            //System.out.println("No hay posiciones vacías en el GridPane. El método terminará.");
        } else {
            // Mientras no se hayan asignado todas las posiciones requeridas
            while (posicionesAsignadas < posiciones) {
                fila = random.nextInt(matriz.length);
                columna = random.nextInt(matriz[0].length);

                // Verificar si la posición en la matriz no es '?' y si no está en la lista de posiciones ocupadas
                if (matriz[fila][columna] != '?' && !posicionesOcupadas.contains(new Pair<>(fila, columna))) {
                    // Obtener el StackPane en la posición correspondiente del GridPane
                    StackPane stackPane = (StackPane) obtenerNodoPorIndiceFilaColumna(fila, columna, gridPane);

                    // Verificar si el StackPane contiene un TextField
                    if (stackPane != null) {
                        // Obtener el TextField existente o crear uno nuevo si no hay ninguno
                        TextField textField;
                        if (stackPane.getChildren().isEmpty()) {
                            textField = new TextField();
                            textField.setEditable(false);
                            stackPane.getChildren().add(textField);
                        } else {
                            textField = (TextField) stackPane.getChildren().get(0);
                        }

                        // Establecer el texto en el TextField
                        textField.setText(String.valueOf(matriz[fila][columna]));

                        // Incrementar el contador de posiciones asignadas
                        posicionesAsignadas++;

                        // Actualizar la lista de posiciones ocupadas
                        posicionesOcupadas.add(new Pair<>(fila, columna));
                    }
                }
            }
        }
    }
/**
 * Obtiene las posiciones ocupadas en un GridPane.
 * 
 * @param gridPane  El GridPane del que se obtendrán las posiciones ocupadas.
 * @return  Una lista de pares (fila, columna) que representan las posiciones ocupadas.
 */
    public static List<Pair<Integer, Integer>> obtenerPosicionesOcupadas(GridPane gridPane) {
        List<Pair<Integer, Integer>> posicionesOcupadas = new ArrayList<>();

        for (Node node : gridPane.getChildren()) {
            if (node instanceof StackPane stackPane) {
                if (!stackPane.getChildren().isEmpty() && stackPane.getChildren().get(0) instanceof TextField) {
                    TextField textField = (TextField) stackPane.getChildren().get(0);
                    if (!textField.getText().isEmpty()) {
                        int rowIndex = GridPane.getRowIndex(stackPane);
                        int colIndex = GridPane.getColumnIndex(stackPane);
                        posicionesOcupadas.add(new Pair<>(rowIndex, colIndex));
                    }
                }
            }
        }

        return posicionesOcupadas;
    }
    /**
     * Verifica si el número de letras diferentes a '?' en la matriz es igual al número de posiciones ocupadas en el GridPane.
     * 
     * @param matriz    La matriz de caracteres que se comparará.
     * @param gridPane  El GridPane del que se obtendrá el número de posiciones ocupadas.
     * @return  true si el número de letras contadas es igual al número de posiciones ocupadas;
     * de lo contrario, false.
     */
    public static boolean letrasIgualesAPosicionesOcupadas(char[][] matriz, GridPane gridPane) {
        int letrasContadas = 0;

        // Contar las letras diferentes a '?' en la matriz
        for (char[] fila : matriz) {
            for (char c : fila) {
                if (c != '?') {
                    letrasContadas++;
                }
            }
        }
        // Obtener el número de posiciones ocupadas en el GridPane
        int posicionesOcupadas = obtenerPosicionesOcupadas(gridPane).size();
        // Validar si el número de letras contadas es igual al número de posiciones ocupadas
        return letrasContadas == posicionesOcupadas;
    }

}
