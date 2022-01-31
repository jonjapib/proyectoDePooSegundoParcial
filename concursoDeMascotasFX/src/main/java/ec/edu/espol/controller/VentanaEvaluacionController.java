/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.concursodemascotasfx.App;
import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pibaque Ponce
 */
public class VentanaEvaluacionController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private FlowPane flowPane;
    
    private VentanaEvaluacionController vec;
    private Concurso c;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vec = this;
    }    
    
    public void recibirParametros(Concurso c){
        this.c = c;
         llenarConcursantes();
    }
    
    private void llenarConcursantes(){
        for(Inscripcion i: Inscripcion.readFile("Inscripciones.txt", c)){
            VBox con = new VBox(10);
            String nom = i.getMascota().getImagen();
            ImageView img;
            if (nom!=null)
                img = new ImageView(new Image("src/main/resources/ec/edu/espol/util/"+nom));
            else
                img = new ImageView(new Image("src/main/resources/ec/edu/espol/util/not found.png"));
            img.setFitHeight(50);
            img.setFitWidth(50);
            Label lbl = new Label(i.getMascota().getNombre());
            Label lblD = new Label(i.getMascota().getDueño().getEmail());
            con.getChildren().addAll(img,lbl,lblD);
            con.setOnMouseClicked(e->abrirEvaluacion(i));
            this.flowPane.getChildren().add(con);
        }
    }
    
    private void abrirEvaluacion(Inscripcion ins){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ventanaPrincipal.fxml"));
            Parent root = loader.load();
            VentanaEvaluarController vpc = loader.getController();
            vpc.recibirParametros(ins);
            Stage stage = new Stage();
            Scene scene = new Scene(root);        
            stage.setScene(scene);
            stage.show();            
        } catch (IOException ex) {
            System.out.println(ex.getCause());
            Util.alertaError(ex.getMessage(), ex.getCause().toString()+"\n"+ex.toString());
        }
    }

    public BorderPane getRoot() {
        return root;
    }
    
}
