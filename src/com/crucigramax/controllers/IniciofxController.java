
package com.crucigramax.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author a.cardenas
 */
public class IniciofxController{

    @FXML
    private void abrirNiveles() throws IOException {
        App.setRoot("/com/crucigramax/fxml/nivelesfx");
    }  
    
}
