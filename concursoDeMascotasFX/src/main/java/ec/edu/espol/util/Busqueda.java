
package ec.edu.espol.util;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.model.Premio;


public class Busqueda {
    
    public static Premio buscarPremio(int idPremio){
        return null;
    }
    
    public static Dueño buscarDueño(String email){
        for(Dueño d: Dueño.readFile("Dueño.txt")){
            if(d.getEmail().equalsIgnoreCase(email))
                return d;
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
    
    public static MiembroJurado buscarJurado(int cedula){
        for(MiembroJurado d: MiembroJurado.readFile("jurado.txt")){
            if(d.getIdMJCedula() == cedula)
                return d;
        }
        return null;
    }
    
}
