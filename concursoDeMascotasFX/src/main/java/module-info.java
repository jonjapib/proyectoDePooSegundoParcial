module ec.edu.espol.concursodemascotasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.concursodemascotasfx to javafx.fxml;
    exports ec.edu.espol.concursodemascotasfx;
}
