/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Pibaque Ponce
 */
public class Premio {
    //Atributos
    private int id;
    private int lugar;
    private String descripcion;
    private int idConcurso;
    private Concurso j;
    
    //Constructor

    public Premio(int id, int lugar, String descripcion, int idConcurso) {
        this.id = id;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.idConcurso = idConcurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public Concurso getJ() {
        return j;
    }

    public void setJ(Concurso j) {
        this.j = j;
    }
    
    
public static Premio nextPremio(Scanner sc){
    
        System.out.println("Ingrese descripcion concurso: ");
        String descripcion = sc.next();
        sc.nextLine();
       sc.useDelimiter("\n");
        System.out.println("Ingrese lugar del premio:");
        int lugar= sc.nextInt();
       // sc.useDelimiter("\n");
       System.out.println("Ingrese el nombre del Concurso que pertenese el concurso:");
       String nombreConcurso= sc.next();
       int idMJ = 0;
                ArrayList<Concurso> j = Concurso.readFile("concurso.txt");
                 for(Concurso x: j){
                      if(x.getNombre().equals(nombreConcurso)){
                          idMJ = x.getId();
                                         }
                 }
        return new Premio(Util.nextID("premios.txt"),lugar, descripcion, idMJ );
          }
    
     public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))){
           
           pw.println(this.id+"|"+this.lugar+"|"+this.descripcion+"|"+this.idConcurso);
       }
       
       catch(Exception e){
           System.out.println(e.getMessage());
       }
   }

      public static ArrayList<Premio> readFile(String nomfile){

       ArrayList<Premio> premios = new ArrayList<>();
       try(Scanner sc = new Scanner(new File(nomfile))){
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] tokens = linea.split("\\|");
               Premio c = new Premio(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]));              
               premios.add(c);
           }
           
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
       return premios; 
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
        final Premio other = (Premio) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.idConcurso, other.idConcurso)) {
            return false;
        }
        return true;
        
        
    }

    @Override
    public String toString() {
        return "Premio{" + "id=" + id + ", lugar=" + lugar + ", descripcion=" + descripcion + ", idConcurso=" + idConcurso + '}';
    }
    
    
    
    
}
