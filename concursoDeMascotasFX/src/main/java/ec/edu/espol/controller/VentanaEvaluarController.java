/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Inscripcion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class VentanaEvaluarController implements Initializable {

    private VentanaEvaluarController vec;
    private Inscripcion ins;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vec = this;
    }    
    
    public void recibirParametros(Inscripcion ins){
        this.ins = ins;
    }
}
