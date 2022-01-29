package ec.edu.espol.concursodemascotasfx;

import ec.edu.espol.util.Menu;
import static ec.edu.espol.util.Menu.imprimirMenu;
import static ec.edu.espol.util.Menu.menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ventanaPrincipal"), 640, 480);
      //scene = new Scene(loadFXML("ventanaPrincial"),500,500);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
//        while(true){
//        Menu.imprimirMenu();
//        Menu.menu();
//        }
    }
}