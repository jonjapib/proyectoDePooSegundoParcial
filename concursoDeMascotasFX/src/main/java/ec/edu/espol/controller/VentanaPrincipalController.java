/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.concursodemascotasfx.App;
import ec.edu.espol.model.Concurso;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pibaque Ponce
 */
public class VentanaPrincipalController implements Initializable {
    private VentanaPrincipalController vpc;
    private Concurso concurso;
    @FXML
    private BorderPane contenedor;
    @FXML
    private Label lblNombre;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vpc = this;
    }    

    
    public void recibirConcurso(Concurso concurso){
        this.concurso = concurso;
        this.lblNombre.setText(concurso.getNombre());
    }

    @FXML
    private void registrarDueno(ActionEvent event) {
        
    }

    @FXML
    private void registrarMascota(ActionEvent event) {
    }

    @FXML
    private void registrarJurado(ActionEvent event) {
    }

    @FXML
    private void registrarPremio(ActionEvent event) {
    }

    @FXML
    private void registrarCriterio(ActionEvent event) {
    }

    @FXML
    private void registrarEvaluacion(ActionEvent event) {
    }

    @FXML
    private void mostrarInformacion(ActionEvent event) {
    }

    private void mostrarOriginal(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("ventanaPrincipal.fxml"));
        try {            
            Parent root = loader.load();
            VentanaPrincipalController principal = loader.getController();                      
            principal.recibirConcurso(concurso);
            this.contenedor.setLeft(principal.contenedor.getLeft());
            this.contenedor.setCenter(principal.contenedor.getCenter());
        } catch (IOException ex) {
            Util.alertaError("ERROR", ex.getMessage());
        }
    }
    
    @FXML
    private void regresar(ActionEvent event) {
        try {            
            App app = new App();
            app.start(new Stage());
            Stage myStage = (Stage) this.lblNombre.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Util.alertaError("ERROR", ex.getMessage());
        }
    }
    
    private void setearContenedor(String ventana){
        FXMLLoader loader = new FXMLLoader(App.class.getResource(ventana));
        try {            
            Parent root = loader.load();
            VentanaPrincipalController principal = loader.getController();                      
            principal.recibirConcurso(concurso);
            this.contenedor.setLeft(principal.contenedor.getLeft());
            this.contenedor.setCenter(principal.contenedor.getCenter());
        } catch (IOException ex) {
            Util.alertaError("ERROR", ex.getMessage());
        }
    }
}
