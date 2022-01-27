/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
   
    
        
    @Override
              public String toString(){
        
        StringBuilder sb = new StringBuilder();
        sb.append("Evaluacion{id=").append(id);
        sb.append(", nota=").append(nota);
        sb.append(", idInscripcion=").append(idInscripcion);
        sb.append(", inscripcion=").append(inscripcion);
        sb.append(", idMiembroJurado=").append(idMiembroJurado);
        sb.append(", miembroJurado=").append(miembroJurado);
        sb.append(", idCriterio=").append(idCriterio);
        sb.append(", criterio=").append(criterio);
        sb.append(", emailJurado=").append(emailJurado);
        sb.append('}');
        return sb.toString();
  }

    public void saveFile(String file) {
        try(BufferedWriter f = new BufferedWriter(new FileWriter(file,true))){
                      
            f.write(this.id+"|");
            f.write(this.nota+"|");
            f.write(this.idInscripcion+"|");
            f.write(this.inscripcion+"|");
            f.write(this.idMiembroJurado+"|");
            f.write(this.idCriterio+"|");
            f.write(this.criterio+"|");
            f.write(this.emailJurado+"|");
            f.newLine();
   
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("no se pudo guardar el archivo");
        }
    }
    public static ArrayList<Evaluacion> readFile(String file){

        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        try(BufferedReader bf =new BufferedReader(new FileReader(file))){
        String linea;
        while((linea = bf.readLine()) !=null){
            
            String[] tokens=linea.split("\\|");
            Evaluacion e = new Evaluacion(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]),Double.parseDouble(tokens[5]));
            evaluaciones.add(e);       
        }

        }catch(Exception e){
            System.out.println("No se pudo leer el archivo");
            System.out.println(e.getMessage());
        }
        return evaluaciones;
    }
    
}

