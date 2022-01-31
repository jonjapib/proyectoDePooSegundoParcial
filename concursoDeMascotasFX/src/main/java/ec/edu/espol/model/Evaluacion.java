
package ec.edu.espol.model;

import ec.edu.espol.util.Busqueda;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class Evaluacion {
    //Atributos
    
    private int id;
    private double nota;
    private Inscripcion inscripcion;
    private MiembroJurado miembroJurado;
    private Criterio criterio;

    public Evaluacion(int id, double nota, Inscripcion inscripcion, MiembroJurado miembroJurado, Criterio criterio) {
        this.id = id;
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

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
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

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }
    
  
    @Override
    public String toString(){     
        StringBuilder sb = new StringBuilder();
        sb.append("Evaluacion{id=").append(id);
        sb.append(", nota=").append(nota);
        sb.append(", idInscripcion=").append(this.inscripcion.getId());
        sb.append(", idMiembroJurado=").append(this.miembroJurado.getIdMJCedula());
        sb.append(", idCriterio=").append(this.criterio.getIdCriterio());
        sb.append('}');
        return sb.toString();
    }

    public void saveFile(String file) {
        try(BufferedWriter f = new BufferedWriter(new FileWriter(file,true))){                      
            f.write(this.id+"|");
            f.write(this.nota+"|");
            f.write(this.inscripcion.getId()+"|");
            f.write(this.miembroJurado.getIdMJCedula()+"|");            
            f.write(this.criterio.getIdCriterio()+"|");
            f.newLine();   
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("no se pudo guardar el archivo");
        }
    }
    
    public static ArrayList<Evaluacion> readFile(String file, Concurso c){
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        try(BufferedReader bf =new BufferedReader(new FileReader(file))){
            String linea;
            while((linea = bf.readLine()) !=null){            
                String[] tokens=linea.split("\\|");
                Inscripcion i = Busqueda.buscarInscripcion(Integer.parseInt(tokens[2]), c.getId());
                MiembroJurado m = Busqueda.buscarJurado(Integer.parseInt(tokens[3]));
                Criterio cr = Busqueda.buscarCriterio(Integer.parseInt(tokens[4]));                
                Evaluacion e = new Evaluacion(Integer.parseInt(tokens[0]),Double.parseDouble(tokens[1]),i,m,cr);
                evaluaciones.add(e);       
            }
        }catch(Exception e){
            System.out.println("No se pudo leer el archivo");
            System.out.println(e.getMessage());
        }
        return evaluaciones;
    }   
}