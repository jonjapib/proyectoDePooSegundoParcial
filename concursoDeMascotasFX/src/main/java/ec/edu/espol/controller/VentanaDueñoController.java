
package ec.edu.espol.controller;

import ec.edu.espol.model.Dueño;
import ec.edu.espol.util.Busqueda;
import ec.edu.espol.util.Util;
import ec.edu.espol.util.Validaciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class VentanaDueñoController implements Initializable {

    @FXML
    private BorderPane rootDueno;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;

    private VentanaDueñoController vdc;
    private int id;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vdc = this;
        id = Util.nextID("Dueño.txt");
        this.txtID.setText(String.valueOf(id));
    }    

    public BorderPane getRootDueno() {
        return rootDueno;
    }

    @FXML
    private void registrar(ActionEvent event) {
        if(validarCampos())
            Util.alertaError("FALTA INFORMACION", "Debe completar todos los cmapos para registrar");
        else if(!Validaciones.esEntero(this.txtTelefono.getText().trim()))
            Util.alertaError("INFORMACION INCORRECTA", "El telefono solo puede contener valores numericos");
        else if (Busqueda.buscarDueño(this.txtEmail.getText().trim())!=null)
            Util.alertaError("DUEÑO YA REGISTRADO", "La persona que esta registrandose ya tiene un registro con el email ingresado");
        else{
            Dueño dueno = new Dueño(id, this.txtNombre.getText().trim(),this.txtApellido.getText().trim(), this.txtDireccion.getText().trim(),this.txtTelefono.getText().trim(), 
                    this.txtEmail.getText().trim());
            dueno.saveFile("Dueño.txt");
            limpiarCampos();
            Util.alertaInfo("REGISTRADO", "El dueño "+this.txtNombre.getText().trim()+" ha sido registrado");
        }
        
    }
    
    private boolean validarCampos(){
        return this.txtID.getText().trim().isEmpty() || this.txtNombre.getText().trim().isEmpty() || this.txtApellido.getText().trim().isEmpty() || 
                this.txtDireccion.getText().trim().isEmpty() || this.txtTelefono.getText().trim().isEmpty() || this.txtEmail.getText().trim().isEmpty();
    }
    
    private void limpiarCampos(){
        id = Util.nextID("Dueño.txt");
        this.txtID.setText(String.valueOf(id));
        this.txtNombre.clear();
        this.txtApellido.clear();
        this.txtDireccion.clear();
        this.txtTelefono.clear();
        this.txtEmail.clear();
    }
    
}
