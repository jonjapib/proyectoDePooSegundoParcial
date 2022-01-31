
package ec.edu.espol.controller;

import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.util.Busqueda;
import ec.edu.espol.util.Util;
import ec.edu.espol.util.Validaciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class VentanaJuradoController implements Initializable {

    @FXML
    private BorderPane rootJurado;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextArea txtAperfil;

    private VentanaJuradoController vjc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vjc= this;
    }    

    @FXML
    private void registrar(ActionEvent event) {        
        if(validarCampos())
            Util.alertaError("FALTA INFORMACION", "Debe completar todos los cmapos para registrar");
        else if (!Validaciones.esEntero(this.txtID.getText().trim()) || !Validaciones.esEntero(this.txtTelefono.getText().trim()))
            Util.alertaError("DATOS INCORRECTOS", "La cedula y el telefono deben ser datos numericos");
        else if(Busqueda.buscarJurado(Integer.parseInt(this.txtID.getText().trim()))!=null)
            Util.alertaError("JURADO PREVIAMENTE INGRESADO", "El jurado ya tiene una cedula registrada, ingrese otro");
        else{
            MiembroJurado jurado = new MiembroJurado(Integer.parseInt(this.txtID.getText().trim()), this.txtNombre.getText().trim(), this.txtApellido.getText().trim(),
            this.txtTelefono.getText().trim(), this.txtEmail.getText().trim(), this.txtAperfil.getText().trim());
            jurado.savefile("jurado.txt");
            limpiarCampos();
            Util.alertaInfo("REGISTRADO", "El jurado ha sido registrado");
        }
    }

    private boolean validarCampos(){
        return this.txtID.getText().trim().isEmpty() || this.txtNombre.getText().trim().isEmpty() || this.txtApellido.getText().trim().isEmpty()|| 
                this.txtTelefono.getText().trim().isEmpty() || this.txtEmail.getText().trim().isEmpty() || this.txtAperfil.getText().trim().isEmpty();
    }
    
    private void limpiarCampos(){
        this.txtID.clear();
        this.txtNombre.clear();
        this.txtApellido.clear();
        this.txtTelefono.clear();
        this.txtEmail.clear();
        this.txtAperfil.clear();
    }
    
    public BorderPane getRootJurado() {
        return rootJurado;
    }
    
}
