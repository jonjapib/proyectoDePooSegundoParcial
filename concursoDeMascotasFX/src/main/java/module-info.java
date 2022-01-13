module ec.edu.espol.concursodemascotasfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.concursodemascotasfx to javafx.fxml;
    exports ec.edu.espol.concursodemascotasfx;
}
