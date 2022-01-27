/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Pibaque Ponce..
 */
public class Criterio {
    private int idCriterio;
    private String descripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private int idConcurso;
    private int concurso;

       public Criterio(int idCriterio, String descripcion, int concurso) {
        this.idCriterio = idCriterio;
        this.descripcion = descripcion;
        this.concurso=concurso;
        this.evaluaciones=new ArrayList<>();
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public int getConcurso() {
        return concurso;
    }

    public void setConcurso(int concurso) {
        this.concurso = concurso;
    }
    


    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
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
        Criterio other = (Criterio) obj;
        if (this.idCriterio != other.idCriterio) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
   
        }
        return true;
    }
    


      public static Criterio nextCriterio(Scanner sc){
            
         
          ArrayList<String> d = new ArrayList<>();
             for(Concurso c: Concurso.readFile("Concurso.txt")){
            d.add(String.valueOf(c.getId()));
            d.add(c.getNombre());     
        } 
           sc.nextLine();
      
          System.out.println("Ingrese descripcion:");
            String descripcion=sc.nextLine();
            sc.useDelimiter("\n");
           
       System.out.println("Ingrese el nombre del Concurso:");
       System.out.println(d);
       String nombreConcurso= sc.next();
       int idP = 0;
                ArrayList<Concurso> j = Concurso.readFile("concurso.txt");
                 for(Concurso x: j){
                      if(x.getNombre().equals(nombreConcurso)){
                          idP = x.getId();
                                         }
                 }    
                    
       Criterio criterio = new Criterio(Util.nextID("Criterios.txt"), descripcion, idP );
    
        return criterio;
    
    }
      
      
           public void saveFile(String file){
        
        try(BufferedWriter f = new BufferedWriter(new FileWriter(file,true))){
                        
            f.write(this.idCriterio+"|");
            f.write(this.descripcion+"|");
            f.write(this.concurso+"|");
            f.newLine();
   
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("no se pudo guardar el archivo");
        }    
    

  }
  public static ArrayList<Criterio> readFile(String File){
        ArrayList<Criterio> criterios = new ArrayList<>();
       try(Scanner sc = new Scanner(new File(File))) {
           while(sc.hasNextLine()){
              String linea=sc.nextLine();
              String[] tokens = linea.split("\\|");
               Criterio vc =new Criterio(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]));
              criterios.add(vc);
    }
       }
          catch(Exception e){
           System.out.println(e.getMessage());
       }
       return criterios;
    }
  
  
}