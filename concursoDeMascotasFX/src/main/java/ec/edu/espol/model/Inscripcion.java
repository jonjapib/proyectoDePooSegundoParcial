
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
import java.util.Scanner;


public class Inscripcion {
    
    private int id;
    private int idMascota;
    private String nMascota;
    private Mascota mascota;
    private LocalDate fechaIns;
    private int idConcurso;
    private double valor;
    private double descuento;
    private String m;
    
    public Inscripcion(int id, String m, String nConcuso, double valor, LocalDate fechaIns ) {
        this.id = id;
        this.nMascota=nMascota;
        this.idMascota = idMascota;
        this.mascota = mascota;
        this.idConcurso = idConcurso;
        this.valor = valor;
        this.descuento = descuento;
        this.m = m;
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

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    
    public static Inscripcion nextInscripcion(Scanner sc){    
        System.out.println("Nombre de la mascota: ");
        String nombre = sc.next();
        sc.nextLine();
        System.out.println("Nombre de Concurso: ");
        String nombreConcurso = sc.nextLine();
        System.out.println("Valor a pagar por la inscripción: ");
        double pagoInscripcion = sc.nextDouble();
        System.out.println("Fecha de la inscripción (yyyy-mm-dd): ");
        String fechaInscripcion = sc.next();
        System.out.println(fechaInscripcion);
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
        sb.append(", idMascota=").append(idMascota);
        sb.append(", nMascota=").append(nMascota);
        sb.append(", mascota=").append(mascota);
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
            f.write(this.idMascota+"|");
            f.write(this.nMascota+"|");
            f.write(this.mascota+"|");
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
    
    public static ArrayList<Inscripcion> readFile(String file){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try(BufferedReader bf =new BufferedReader(new FileReader(file))){
        String linea;
        while((linea = bf.readLine()) !=null){
            
            String[] tokens=linea.split("\\|");
            Inscripcion e = new Inscripcion(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Double.parseDouble(tokens[3]),LocalDate.parse(tokens[4]));
            inscripciones.add(e);       
        }

        }catch(Exception e){
            System.out.println("No se pudo leer el archivo");
            System.out.println(e.getMessage());
        }
        return inscripciones;
    }
}
