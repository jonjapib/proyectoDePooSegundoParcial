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
import java.util.Scanner;

/**
 *
 * @author Pibaque Ponce
 */
public class Evaluacion {
    //Atributos
    
    private int id;
    private double nota;
    private int idInscripcion;
    private Inscripcion inscripcion;
    private int idMiembroJurado;
    private MiembroJurado miembroJurado;
    private int idCriterio;
    private Criterio criterio;
    private String emailJurado;

    public String getEmailJurado() {
        return emailJurado;
    }

    public void setEmailJurado(String emailJurado) {
        this.emailJurado = emailJurado;
    }
    
    //Constructor

    public Evaluacion(int id,String emailJurado, int idInscripcion, int idMiembroJurado, int idCriterio,double nota) {
        this.id = id;
        this.emailJurado= emailJurado;
        this.idInscripcion = idInscripcion;
        this.idMiembroJurado = idMiembroJurado;
        this.idCriterio = idCriterio;
        this.nota = nota;
        this.inscripcion = inscripcion;
        this.miembroJurado = miembroJurado;
        this.criterio = criterio;
    }


    
    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getIdMiembroJurado() {
        return idMiembroJurado;
    }

    public void setIdMiembroJurado(int idMiembroJurado) {
        this.idMiembroJurado = idMiembroJurado;
    }

    public MiembroJurado getMiembroJurado() {
        return miembroJurado;
    }

    public void setMiembroJurado(MiembroJurado miembroJurado) {
        this.miembroJurado = miembroJurado;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }
    
    //Método nextEvaluacion
      public static Evaluacion nextEvaluacion(Scanner sc){
int idMJ=0;
        sc.useDelimiter("\n");
        System.out.println("Ingrese correo del Jurado: ");
        String emailD = sc.nextLine();
        sc.nextLine();
       
        ArrayList<MiembroJurado> j = MiembroJurado.readFile("jurado.txt");
                 for(MiembroJurado x: j){
                      if(x.getEmail().equals(emailD)){
                        idMJ = x.getIdMJCedula();
                          
               }
               else{
                 System.out.println("Ingrese correo del Jurado: ");
                 
                 emailD = sc.nextLine();
                 
                   }
                      
                   }
        System.out.println("ingrese nota de evaluacion");
                   double nota=sc.nextDouble();
int idCriterio = 0; 
        ArrayList<String> listCriterio = new ArrayList<>();
        ArrayList<Criterio> c = Criterio.readFile("Criterios.txt");   
        for(Criterio y: c){
        idCriterio = y.getIdCriterio();
        listCriterio.add(y.getDescripcion());
           
        } System.out.println(listCriterio);
int idInscripcion = 0; 
      
        ArrayList<Inscripcion> ii = Inscripcion.readFile("Inscripciones.txt");   
        for(Inscripcion z: ii){
        idInscripcion = z.getId();
        }
       Evaluacion evaluacion = new Evaluacion(Util.nextID("evaluacion.txt"), emailD,idInscripcion ,idMJ,idCriterio, nota);
    
        return evaluacion;
    
    }
   
    
 
   public void savefile(String File){
  try(PrintWriter pw= new PrintWriter(new FileOutputStream(new File(File),true))){
      
   
      pw.println(+this.id+"|"+this.emailJurado+"|"+this.inscripcion+"|"+this.idMiembroJurado+"|"+this.idCriterio+"|"+this.nota);
      
  }catch(Exception e){
      System.out.println(e.getMessage());
  }
   

  }
  public static ArrayList<Evaluacion> readFile(String File){
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
       try(Scanner sc = new Scanner(new File(File))) {
           while(sc.hasNextLine()){
              String linea=sc.nextLine();
              String[] tokens = linea.split("\\|");
               Evaluacion v =new Evaluacion(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]),Double.parseDouble(tokens[5]));
              evaluaciones.add(v);
              
               
           }
       }
          catch(Exception e){
           System.out.println(e.getMessage());
       }
       return evaluaciones;
    }   
}

