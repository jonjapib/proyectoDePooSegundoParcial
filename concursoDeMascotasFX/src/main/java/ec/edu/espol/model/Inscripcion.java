/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Busqueda;
import ec.edu.espol.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Pibaque Ponce
 */
public class Inscripcion {
    //Argumentos
    private int id;
    private Mascota mascota;
    private LocalDate fechaIns;
    private int idConcurso;
    private double valor;
    private double descuento;
    private String m;
    
    //Constructor

    public Inscripcion(int id, Mascota mascota, LocalDate fechaIns, int idConcurso, double valor, double descuento, String m) {
        this.id = id;
        this.mascota = mascota;
        this.fechaIns = fechaIns;
        this.idConcurso = idConcurso;
        this.valor = valor;
        this.descuento = descuento;
        this.m = m;
    }
    

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.mascota);
        return hash;
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
        if (!Objects.equals(this.mascota, other.mascota)) {
            return false;
        }
        return true;
    }
    
    
    
//    //Método saveFile
//    public void saveFile(String nomfile){
//        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))){
//           
//           pw.println(this.id+"|"+this.nMascota+"|"+this.m+"|"+this.valor+"|"+this.fechaIns);
//       }
//       
//       catch(Exception e){
//           System.out.println(e.getMessage());
//       }
//        
//    }
//
//
//
//public static ArrayList<Inscripcion> readFile(String File){
//
//        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
//       try(Scanner sc = new Scanner(new File(File))) {
//           while(sc.hasNextLine()){
//              String linea=sc.nextLine();
//              String[] tokens = linea.split("\\|");
//               Inscripcion e =new Inscripcion(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Double.parseDouble(tokens[3]),LocalDate.parse(tokens[4]));
//              inscripciones.add(e);
//              
//               
//           }
//       }
//          catch(Exception e){
//           System.out.println(e.getMessage());
//       }
//       return inscripciones;
//
//  
//}   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inscripcion{id=").append(id);
        sb.append(", mascota=").append(mascota.getId());
        sb.append(", fechaIns=").append(fechaIns);
        sb.append(", idConcurso=").append(idConcurso);
        sb.append(", valor=").append(valor);
        sb.append(", descuento=").append(descuento);
        sb.append(", m=").append(m);
        sb.append('}');
        return sb.toString();
    }
    
    
    public void saveFile(String file) {
        try(BufferedWriter f = new BufferedWriter(new FileWriter(file,true))){                      
            f.write(this.id+"|");
            f.write(this.mascota.getId()+"|");
            f.write(this.fechaIns+"|");
            f.write(this.idConcurso+"|");
            f.write(this.valor+"|");
            f.write(this.descuento+"|");
            f.write(this.m+"|");
            f.newLine();
   
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("no se pudo guardar el archivo");
        }
    }
    public static ArrayList<Inscripcion> readFile(String file, Concurso concurso){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try(BufferedReader bf =new BufferedReader(new FileReader(file))){
        String linea;
        while((linea = bf.readLine()) !=null){
            
            String[] tokens=linea.split("\\|");
            Mascota mascota = Busqueda.buscarMascota(Integer.parseInt(tokens[1]));
            Inscripcion e = new Inscripcion(Integer.parseInt(tokens[0]),mascota,LocalDate.parse(tokens[2]),Integer.parseInt(tokens[3]),Double.parseDouble(tokens[4]),
            Double.parseDouble(tokens[5]),tokens[6]);
            if(e.idConcurso == concurso.getId())
                inscripciones.add(e);       
        }

        }catch(Exception e){
            System.out.println("No se pudo leer el archivo");
            System.out.println(e.getMessage());
        }
        return inscripciones;
    }
}
