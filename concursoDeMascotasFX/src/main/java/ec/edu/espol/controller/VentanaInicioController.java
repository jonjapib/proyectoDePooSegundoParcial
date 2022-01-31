
package ec.edu.espol.controller;

import ec.edu.espol.concursodemascotasfx.App;
import ec.edu.espol.model.Concurso;
import ec.edu.espol.util.Busqueda;
import ec.edu.espol.util.Util;
import ec.edu.espol.util.Validaciones;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class VentanaInicioController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private BorderPane borderRegistro;

    private VentanaInicioController vic;
    @FXML
    private TextField txtID;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vic = this;
    }    

    @FXML
    private void registrar(ActionEvent event) {
        cambiarVentanaRegistro();
    }

    @FXML
    private void avanzar(ActionEvent event) {
        if(Validaciones.esEntero(this.txtID.getText().trim())){
            Concurso concurso = Busqueda.buscarConcurso(Integer.parseInt(this.txtID.getText().trim()));
            if(concurso!=null)
                cambiarVentanaPrincipal(concurso);
            else
                Util.alertaError("CONCURSO NO ENCONTRADO", "No se ha encotnrado ningún concurso con ese id");
        }else
            Util.alertaError("FORMATO INCORRECTO", "Debe ingresar un id (valor numerico que se general al crear un concurso)");
        
    }
    
    private void cambiarVentanaPrincipal(Concurso concurso){               
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ventanaPrincipal.fxml"));
            Parent root = loader.load();
            VentanaPrincipalController vpc = loader.getController();
            vpc.recibirConcurso(concurso);
            Stage stage = new Stage();
            Scene scene = new Scene(root);        
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.borderPane.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            System.out.println(ex.getCause());
            Util.alertaError(ex.getMessage(), ex.getCause().toString()+"\n"+ex.toString());
        }
    }
    
    private void cambiarVentanaRegistro(){
        try {            
            borderRegistro.setDisable(false);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ventanaConcurso.fxml"));
            Parent root = loader.load();            
            VentanaConcursoController concurso = loader.getController();           
            concurso.recibirParametros(borderPane, borderRegistro);
            borderPane.setVisible(false);     
            borderRegistro.setVisible(true);
            borderRegistro.setTop(concurso.getRoot().getTop());
            borderRegistro.setCenter(concurso.getRoot().getCenter());
            borderRegistro.setBottom(concurso.getRoot().getBottom());            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
