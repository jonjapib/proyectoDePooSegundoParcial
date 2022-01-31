
package ec.edu.espol.util;

import java.io.File;
import java.util.Scanner;
import javafx.scene.control.Alert;

public class Util {
    
    // el constructor se lo ha declarado privado
    // ya que esta clase solo va a contener comportamientos estáticos
    // por lo tanto, no se van a permitir crear instancia de la clase Util
    private Util(){}
    
    public static int nextID(String nomfile){
        int id = 0;
        try(Scanner sc = new Scanner(new File(nomfile)))
        {
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] tokens = linea.split("\\|");
               id = Integer.parseInt(tokens[0]);
           }
        }
        catch(Exception e){
        }
        return id+1;
    }
    public static void alertaError(String encabezado, String mensaje){
        Alert a = new Alert(Alert.AlertType.ERROR, encabezado);
        a.setTitle(encabezado);
        a.setContentText(mensaje);
        a.show();
    }
    public static void alertaInfo(String encabezado, String mensaje){
        Alert a = new Alert(Alert.AlertType.INFORMATION, encabezado);
        a.setTitle(encabezado);
        a.setContentText(mensaje);
        a.show();
    }
    
}
