
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author megag
 */
public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fecha;
    private LocalDate fechaInscripcion;
    private LocalDate fechaCierreInscripcion;
    private String tematica;
    private double costo;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<MiembroJurado> jurados;
    
    public Concurso(int id, String nombre, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion, String tematica, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.tematica = tematica;
        this.costo = costo;
        this.inscripciones = new ArrayList<>();
        this.jurados = new ArrayList<>();
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public LocalDate getFechaCierreInscripcion() {
        return fechaCierreInscripcion;
    }

    public void setFechaCierreInscripcion(LocalDate fechaCierreInscripcion) {
        this.fechaCierreInscripcion = fechaCierreInscripcion;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public ArrayList<MiembroJurado> getJurados() {
        return jurados;
    }

    public void setJurados(ArrayList<MiembroJurado> jurados) {
        this.jurados = jurados;
    }

    @Override
    public String toString() {
        return "[" +id + "," + nombre + "," + fecha + "," + fechaInscripcion + "," + fechaCierreInscripcion + "," + tematica + "," + costo  + "]";
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
        final Concurso other = (Concurso) obj;
        return this.id == other.id;
    }
    
    public static Concurso nextConcurso(Scanner sc){
        System.out.println("Ingrese Nombre: ");
        sc.useDelimiter("\n");
        sc.nextLine();
        String nameM = sc.nextLine();
        System.out.println("Ingrese Fecha(yyyy-mm-dd) ej:2016-08-06:  ");    
        String fecha = sc.nextLine();
        LocalDate fecha0 = LocalDate.parse(fecha);
        System.out.println("Ingrese fecha de Inicio de Inscripcion(yyyy-mm-dd): ");
        String fechaI = sc.nextLine();
        LocalDate fechaI0 = LocalDate.parse(fechaI);
        System.out.println("Ingrese fecha de Fin de Inscripcion(yyyy-mm-dd): ");
        String fechaF = sc.nextLine();
        LocalDate fechaF0 = LocalDate.parse(fechaF);
        System.out.println("Ingrese Tematica: ");
        String tematica = sc.nextLine();
        System.out.println("Ingrese Costo: ");
        double costo = sc.nextDouble();
        
        return new Concurso(Util.nextID("Concurso.txt"),nameM,fecha0,fechaI0,fechaF0,tematica,costo);        
    }
    
    public void saveFile(String file){
        try(BufferedWriter f = new BufferedWriter(new FileWriter(file,true))){
            f.write(this.id+"|");
            f.write(this.nombre+"|");
            f.write(this.fecha+"|");
            f.write(this.fechaInscripcion+"|");
            f.write(this.fechaCierreInscripcion+"|");
            f.write(this.tematica+"|");
            f.write(this.costo+"|");

            f.newLine();

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("no se pudo guardar el archivo");
            Util.alertaError(e.getLocalizedMessage(), e.toString()+"\nNo se pudo guardar el archivo con los datos");
        }    
    }
    
    public static ArrayList<Concurso> readFile(String file){

        ArrayList<Concurso> concursos = new ArrayList<>();
        try(BufferedReader bf =new BufferedReader(new FileReader(file))){
        String linea;
        while((linea = bf.readLine()) !=null){
            
            String[] tokens=linea.split("\\|");
            Concurso c = new Concurso(Integer.parseInt(tokens[0]),tokens[1],LocalDate.parse(tokens[2]),LocalDate.parse(tokens[3]),LocalDate.parse(tokens[4]),tokens[5],Double.parseDouble(tokens[6]));
            concursos.add(c);       
        }

        }catch(Exception e){
            System.out.println("No se pudo leer el archivo");
            System.out.println(e.getMessage());
            Util.alertaError(e.getLocalizedMessage(), e.toString()+"\nNo se pudo leer el archivo con los datos");
        }
        return concursos;
    }
}