package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Mascota {
    private int id; 
    private String nombre;
    private String raza;
    private LocalDate fechaNacimiento;
    private String tipo;          //perro o gato    
    private Dueño dueño;
    private ArrayList<Inscripcion> inscripciones;
    
    public Mascota(int id, String nombre, String raza, LocalDate fechaNacimiento, String tipo, Dueño dueño){ //ArrayList<Inscripcion> inscripciones) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;        
        this.dueño = dueño;
        this.inscripciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public Dueño getDueño() {
        return dueño;
    }

    public ArrayList<Inscripcion> getInscripciones() {
          return inscripciones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mascota other = (Mascota) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "Mascota{" + "id=" + id + ", nombre=" + nombre + ", raza=" + raza + ", fechaNacimiento=" + fechaNacimiento + ", tipo=" + tipo + ", due\u00f1o=" + dueño + '}';
    }
        
    
    public void saveFile(String file){
        try(BufferedWriter f=new BufferedWriter(new FileWriter(file, true))){

            f.write(this.id+"|");
            f.write(this.nombre+"|");
            f.write(this.raza+"|");            
            f.write(this.fechaNacimiento+"|");
            f.write(this.tipo+"|");
            f.write(this.dueño.getEmail()+"|");
            f.newLine();
        }catch(Exception e){
            System.out.println("No se puede guardar");
        }   
    }
 
        
        public static ArrayList<Mascota> readFile(String nomfile){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

       ArrayList<Mascota> mascotas = new ArrayList<>();
       try(Scanner sc = new Scanner(new File(nomfile))){
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] tokens = linea.split("//|");
               String[] duenio = tokens[6].split(",");
               Mascota m = new Mascota(Integer.parseInt(tokens[0]),tokens[1],tokens[2],LocalDate.parse(tokens[3]),tokens[4], new Dueño(Integer.parseInt(duenio[0]),duenio[1],duenio[2],duenio[3],duenio[4],duenio[5]));
               mascotas.add(m);
           }
           
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
       return mascotas; 
    } 
      
}
