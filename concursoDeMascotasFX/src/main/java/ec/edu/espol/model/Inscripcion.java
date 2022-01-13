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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pibaque Ponce
 */
public class Inscripcion {
    //Argumentos
    private int id;
    private int idMascota;
    private String nMascota;
    private String nConcurso;
    private Mascota mascota;
    private LocalDate fechaIns;
    private int idConcurso;
    private double valor;
    private double descuento;
    
    //Constructor

    public Inscripcion(int id,  String nMascota,String nConcuso, double valor,LocalDate fechaIns ) {
        this.id = id;
        this.nConcurso=nConcurso;
        this.nMascota=nMascota;
        this.idMascota = idMascota;
        this.mascota = mascota;
        this.idConcurso = idConcurso;
        this.valor = valor;
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getnMascota() {
        return nMascota;
    }

    public void setnMascota(String nMascota) {
        this.nMascota = nMascota;
    }

    public String getnConcurso() {
        return nConcurso;
    }

    public void setnConcurso(String nConcurso) {
        this.nConcurso = nConcurso;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public LocalDate getFechaIns() {
        return fechaIns;
    }

    public void setFechaIns(LocalDate fechaIns) {
        this.fechaIns = fechaIns;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    
    //Método nextInscripcion
    public static Inscripcion nextInscripcion(Scanner sc){
      //  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("Nombre de la mascota: ");
        String nombre = sc.nextLine();
        sc.nextLine();
        System.out.println("Nombre de Concurso: ");
        String nombreConcurso = sc.nextLine();
        System.out.println("Valor a pagar por la inscripción: ");
        double pagoInscripcion = sc.nextDouble();
        System.out.println("Fecha de la inscripción: ");
        String fechaInscripcion = sc.nextLine();
        LocalDate fecha_Inscripcion = LocalDate.parse(fechaInscripcion);
        // Descuento
        
        
        
        
        return new Inscripcion(Util.nextID("inscripciones.txt"),nombre,nombreConcurso,pagoInscripcion,fecha_Inscripcion);
        
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
        final Inscripcion other = (Inscripcion) obj;
        if (this.idMascota != other.idMascota) {
            return false;
        }
        return true;
    }
    
    //Método saveFile
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))){
           
           pw.println(this.id+"|"+this.idMascota+"|"+this.mascota+"|"+this.idConcurso+"|"+this.valor);
       }
       
       catch(Exception e){
           System.out.println(e.getMessage());
       }
        
    }



public static ArrayList<Inscripcion> readFile(String File){

        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
       try(Scanner sc = new Scanner(new File(File))) {
           while(sc.hasNextLine()){
              String linea=sc.nextLine();
              String[] tokens = linea.split("\\|");
               Inscripcion e =new Inscripcion(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Double.parseDouble(tokens[3]),LocalDate.parse(tokens[4]));
              inscripciones.add(e);
              
               
           }
       }
          catch(Exception e){
           System.out.println(e.getMessage());
       }
       return inscripciones;

  
}   
}
