package ec.edu.espol.controller;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.util.Util;
import ec.edu.espol.util.Validaciones;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Pibaque Ponce
 */
public class VentanaConcursoController implements Initializable {

    @FXML
    private BorderPane root;
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

    private VentanaConcursoController vcc;
    private BorderPane principal;    
    private BorderPane thisOne;
    private int id;
    @FXML
    private Button btnRegresar;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vcc = this;
        id = Util.nextID("Concurso.txt");
        this.txtID.setText(String.valueOf(id));
        this.dtPckFecha.setValue(LocalDate.now());
        this.dtPckInicio.setValue(LocalDate.now());
        confDatePickerInicio();
        confDatePickerFin();
    }    

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    private void registrar(ActionEvent event) {
        if(validarCampos())
            Util.alertaError("FALTA INFORMACION", "Todos los campos deben estar completos para continuar");
        else if (!Validaciones.esDecimal(this.txtCosto.getText().trim()))
            Util.alertaError("INFORMACION INCORRECTA", "El costo debe tener valores numericos");
        else{           
            Concurso concurso = new Concurso(id, this.txtNombre.getText().trim(), this.dtPckFecha.getValue(), this.dtPckInicio.getValue(),this.dtPckFin.getValue(),
            this.txtTematica.getText().trim(), Double.parseDouble(this.txtCosto.getText().trim()));
            concurso.saveFile("Concurso.txt");
            Util.alertaInfo("Concurso creado correctamente", "Para gestionar el concurso debe ingresar el id "+id);
            this.btnRegresar.fire();
        }
    }

    @FXML
    private void regresar(ActionEvent event) {     
        thisOne.getChildren().clear();
        thisOne.setDisable(true);
        principal.setVisible(true);
    }
    
    public void recibirParametros(BorderPane principal, BorderPane borderRegistro){
        this.principal = principal;        
        thisOne = borderRegistro;
    }
    
    private boolean validarCampos(){
        return this.txtNombre.getText().trim().isEmpty() || this.txtTematica.getText().trim().isEmpty() || this.txtCosto.getText().trim().isEmpty() 
                || this.dtPckInicio.getValue()==null || this.dtPckFin.getValue()==null;
    }
    
    private void confDatePickerInicio(){
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            this.setDisable(false);
            this.setBackground(null);
            this.setTextFill(Color.BLACK);
            // deshabilitar las fechas futuras
            if (item.isBefore(LocalDate.now())) 
                this.setDisable(true);            
            if (dtPckFin.getValue()!=null && item.isAfter(dtPckFin.getValue())) 
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
        this.dtPckInicio.setDayCellFactory(dayCellFactory);
    }
    
    private void confDatePickerFin(){
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            this.setDisable(false);
            this.setBackground(null);
            this.setTextFill(Color.BLACK);
            // deshabilitar las fechas futuras
            if (item.isBefore(dtPckInicio.getValue())) 
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
        this.dtPckFin.setDayCellFactory(dayCellFactory);
    }
}
