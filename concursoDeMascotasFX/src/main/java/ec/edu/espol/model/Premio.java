/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Busqueda;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Premio {
    
    private int id;
    private int lugar;
    private String descripcion;    
    private Concurso concurso;
    
    public Premio(int id, int lugar, String descripcion, Concurso concurso) {
        this.id = id;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.concurso = concurso;
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

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))){
            pw.println(this.id+"|"+this.lugar+"|"+this.descripcion+"|"+this.concurso.getId());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Premio> readFile(String nomfile){
        ArrayList<Premio> premios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");                
                Premio c = new Premio(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],Busqueda.buscarConcurso(Integer.parseInt(tokens[3])));              
                premios.add(c);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return premios; 
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Premio other = (Premio) obj;
        return this.id == other.id;
    }


    @Override
    public String toString() {
        return "Premio{" + "id=" + id + ", lugar=" + lugar + ", descripcion=" + descripcion + ", idConcurso=" + concurso.getId() + '}';
    } 
    
    
    
    
    
    
}
