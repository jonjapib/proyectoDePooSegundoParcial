module ec.edu.espol.concursodemascotasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.concursodemascotasfx to javafx.fxml;
    exports ec.edu.espol.concursodemascotasfx;
      

        opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.controller;
    
        opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
}
