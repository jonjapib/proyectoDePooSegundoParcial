package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author megag
 */
public class Dueño {
    private int id;
    private String nombre;
    private String apeillido;
    private String direccion;
    private String telefono;
    private String email;
    private ArrayList<Mascota> mascotas;

    public Dueño(int id, String nombre, String apeillido, String direccion, String telefono, String email){
        this.id = id;
        this.nombre = nombre;
        this.apeillido = apeillido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.mascotas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApeillido() {
        return apeillido;
    }

    public void setApeillido(String apeillido) {
        this.apeillido = apeillido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    
    
        @Override
    public String toString() {
        return "Dueño{" + "id=" + id + ", nombre=" + nombre + ", apeillido=" + apeillido + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + "}"; //", mascotas=" + mascotas + '}';
    }
    
    public static Dueño nextDueño(Scanner sc){        
        int idD = Util.nextID("Dueño.txt");
        System.out.println("Ingrese Nombre: ");
        String nameD = sc.nextLine();
        System.out.println("Ingrese Apellido: ");
        String lnameD = sc.nextLine();
        System.out.println("Ingrese Direccion: ");
        String direccD = sc.nextLine();
        System.out.println("Ingrese Numero Telefonico: ");
        String phoneD = sc.nextLine();
        System.out.println("Ingrese e-mail: ");
        String emailD = sc.nextLine();
               
        ArrayList<Mascota> listaM = new ArrayList<>();
        for(Mascota m: Mascota.readFile("Mascota.txt")){
            if(idD==m.getDueño().getId())
                listaM.add(m);
        
        }        
        Dueño d = new Dueño(idD,nameD,lnameD,direccD,phoneD,emailD);
        d.setMascotas(listaM);
        d.saveFile("Dueño.txt");
        
        return d ;
    }
    
    public void saveFile(String file){
        
        try(BufferedWriter f = new BufferedWriter(new FileWriter(file,true))){
                      
            f.write(this.id+"|");
            f.write(this.nombre+"|");
            f.write(this.apeillido+"|");
            f.write(this.direccion+"|");
            f.write(this.telefono+"|");
            f.write(this.email+"|");
            f.newLine();
   
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("no se pudo guardar el archivo");
        }    
  }
    public static ArrayList<Dueño> readFile(String file){
        ArrayList<Dueño> dueños = new ArrayList<>();
        try(BufferedReader bf =new BufferedReader(new FileReader(file))){
        String linea;
        while((linea = bf.readLine()) !=null){
            
            String[] tokens=linea.split("\\|");
            Dueño d = new Dueño(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
            dueños.add(d);       
        }

        }catch(Exception e){
            System.out.println("No se pudo leer el archivo");
            System.out.println(e.getMessage());
        }
        return dueños;
    }    
    
    
}

