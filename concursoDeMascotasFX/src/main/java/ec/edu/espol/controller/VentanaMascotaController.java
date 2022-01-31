/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.concursodemascotasfx.App;
import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.util.Busqueda;
import ec.edu.espol.util.Util;
import ec.edu.espol.util.Validaciones;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VentanaMascotaController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> cboTipo;
    @FXML
    private TextField txtRaza;
    @FXML
    private DatePicker dtPckNacimiento;
    @FXML
    private BorderPane borderRoot;

    private VentanaMascotaController vmc;
    private int id;
    private String nombreImagen;
    @FXML
    private Label lblNombre;
    private File selectedFile;
    private Path url;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vmc = this;
        this.cboTipo.getItems().clear();
        this.cboTipo.getItems().add("Perro");
        this.cboTipo.getItems().add("Gato");
        id = Util.nextID("Mascota.txt");
        this.txtID.setText(String.valueOf(id));
        configDatePicker();
    }    

    @FXML
    private void registrar(ActionEvent event) {
        Dueño dueno = Busqueda.buscarDueño(this.txtEmail.getText().trim()); 
        if(validarCampos())
            Util.alertaError("FALTA INFORMACION", "Debe completar todos los cmapos para registrar");
        else if (dueno==null)
            Util.alertaError("DUEÑO NO REGISTRADO", "El dueño de la mascota no se encuentra registrado");
        else if(this.nombreImagen==null)
            Util.alertaError("FALTA IMAGEN", "Debe subir una imagen de sus mascota");
        else{
            Mascota mascota = new Mascota(id, this.txtNombre.getText().trim(),this.txtRaza.getText().trim(), this.dtPckNacimiento.getValue(), this.cboTipo.getValue(), dueno, nombreImagen);
            guardarFoto();
            mascota.saveFile("Mascota.txt");
            limpiarCampos();
            Util.alertaInfo("REGISTRADO", "Las mascota "+this.txtNombre.getText().trim()+" ha sido registrada");
        }
    }

    @FXML
    private void seleccionarArchivo(ActionEvent event) {
        URI ubicacion;
        try {
            ubicacion = App.class.getResource("ventanaMascota" + ".fxml").toURI();
            url = Paths.get(ubicacion);            
            FileChooser fc = new FileChooser();
            fc.setTitle("Attach a file");
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", ".JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", ".PNG");
            fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            selectedFile = fc.showOpenDialog(null);    
            this.nombreImagen = selectedFile.getName();
            this.lblNombre.setText(nombreImagen);            
        } catch (URISyntaxException ex) {
            Util.alertaError("Error en archivo", ex.getMessage());
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void guardarFoto(){
        if (selectedFile != null) {
            Path from = Paths.get(selectedFile.toURI());
            System.out.println(url.getParent().getParent()+"\\util\\");
            Path to = Paths.get(url.getParent().getParent()+"\\util\\" + selectedFile.getName());
            try{
                FileOutputStream outputStream = new FileOutputStream("src/main/resources/ec/edu/espol/util/"+this.nombreImagen);                
                FileInputStream inputStream = new FileInputStream(this.selectedFile);
                FileChannel inChannel = inputStream.getChannel();
                FileChannel outChannel = outputStream.getChannel();
                try {
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    Util.alertaError("Error en archivo", ex.getMessage());
                }finally{
                    try {
                        inChannel.close();
                        outChannel.close();
                        inputStream.close();
                        outputStream.close();
                    } catch (IOException ex) {
                        // TODO Auto-generated catch block
                        Util.alertaError("Error en archivo", ex.getMessage());
                    }
                }
            } catch (IOException ex) {
                Util.alertaError("Error en archivo", ex.getMessage());
            }
        }   
    }
    
    public BorderPane getBorderRoot() {
        return borderRoot;
    }
    
    private void configDatePicker(){        
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            this.setDisable(false);
            this.setBackground(null);
            this.setTextFill(Color.BLACK);
            // deshabilitar las fechas futuras
            if (item.isAfter(LocalDate.now())) 
                this.setDisable(true);                        
            // marcar los dias de quincena
            int day = item.getDayOfMonth();
            if(day == 15 || day == 30) {
                this.setTextFill(Color.RED);
            }
            // fines de semana en color verde
            DayOfWeek dayweek = item.getDayOfWeek();
            if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) 
                this.setTextFill(Color.GREEN);            
            }
        };        
        this.dtPckNacimiento.setDayCellFactory(dayCellFactory);
    
    }
    
    private boolean validarCampos(){
        return this.txtID.getText().trim().isEmpty() || this.txtNombre.getText().trim().isEmpty() || this.cboTipo.getValue()==null || 
                dtPckNacimiento.getValue()==null || this.txtRaza.getText().trim().isEmpty() || this.txtEmail.getText().trim().isEmpty();
    }
    
    private void limpiarCampos(){
        id = Util.nextID("Dueño.txt");
        this.txtID.setText(String.valueOf(id));
        this.txtNombre.clear();
        this.cboTipo.setValue(null);
        this.dtPckNacimiento.setValue(null);
        this.txtRaza.clear();
        this.txtEmail.clear();
    }
}
