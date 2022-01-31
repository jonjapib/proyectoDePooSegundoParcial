/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.Premio;
import ec.edu.espol.util.Util;
import ec.edu.espol.util.Validaciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VentanaCriterioController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtConcurso;
    
    private Concurso c;
    private VentanaCriterioController vcc;
    private int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vcc = this;
        id = Util.nextID("Criterios.txt");
        this.txtID.setText(String.valueOf(id));
    }    

    @FXML
    private void registrar(ActionEvent event) {
        if(validarCampos())
            Util.alertaError("FALTA INFORMACION", "Debe completar todos los campos para registrar");
        else{
            Criterio criterio = new Criterio(id, this.txtDescripcion.getText().trim(),c.getId());
            criterio.saveFile("Criterios.txt");
            limpiarCampos();
            Util.alertaInfo("REGISTRADO", "El criterio ha sido registrado");
        }
            
    }

    public void recibirParametros(Concurso c){
        this.c = c;
        this.txtConcurso.setText(c.getNombre()+"   id: "+c.getId());
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    private boolean validarCampos(){
        return this.txtDescripcion.getText().trim().isEmpty();
    }
    
    private void limpiarCampos(){
        id = Util.nextID("Criterios.txt");
        this.txtID.setText(String.valueOf(id));
        this.txtDescripcion.clear();
    }
}
