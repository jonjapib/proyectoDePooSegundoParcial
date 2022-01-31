
package ec.edu.espol.util;

import ec.edu.espol.model.*;


public class Busqueda {
    
    public static Mascota buscarMascota(int id){
        for(Mascota c: Mascota.readFile("Mascota.txt")){
            if(c.getId() == id)
                return c;
        }
        return null;
    }
    
    public static Concurso buscarConcurso(int idConcurso){
        for(Concurso c: Concurso.readFile("Concurso.txt")){
            if(c.getId() == idConcurso)
                return c;
        }
        return null;
    }
    
    public static Dueño buscarDueño(String email){
        for(Dueño d: Dueño.readFile("Dueño.txt")){
            if(d.getEmail().equalsIgnoreCase(email))
                return d;
        }
        return null;
    }        
    
    public static MiembroJurado buscarJurado(int cedula){
        for(MiembroJurado d: MiembroJurado.readFile("jurado.txt")){
            if(d.getIdMJCedula() == cedula)
                return d;
        }
        return null;
    }
    
    public static Inscripcion buscarInscripcion(int id, int idConcurso){
        for(Inscripcion d: Inscripcion.readFile("Inscripciones.txt", buscarConcurso(idConcurso))){
            if(d.getId() == id)
                return d;
        }
        return null;
    }
    
    public static Criterio buscarCriterio(int idCriterio){
        for(Criterio c: Criterio.readFile("Criterios.txt")){
            if(c.getIdCriterio() == idCriterio)
                return c;
        }
        return null;
    }
}
