/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Pibaque Ponce
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private ComboBox<?> comBx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList item = FXCollections.observableArrayList();
        item.add("Registre Concurso");
        item.add("Registre Criterios");
        item.add("Registre Dueño");
        item.add("Registre Evaluacion");
        item.add("Registre Inscripcion");
        item.add("Registre Mascota");
        item.add("Registre Concurso");
        
        
        
       comBx.setItems(item);
    }    

    @FXML
    private void opciones(ActionEvent event) {
        
    }
    
}
