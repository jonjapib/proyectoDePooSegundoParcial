/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Pibaque Ponce
 */
public class VentanaConcursoController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTematica;
    @FXML
    private TextField txtCosto;
    @FXML
    private DatePicker dtPckInicio;
    @FXML
    private DatePicker dtPckFin;
    @FXML
    private DatePicker dtPckFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrar(ActionEvent event) {
    }

    @FXML
    private void regresar(ActionEvent event) {
    }
    
}
