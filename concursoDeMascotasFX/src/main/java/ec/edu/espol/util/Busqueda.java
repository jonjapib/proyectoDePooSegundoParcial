
package ec.edu.espol.util;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Premio;


public class Busqueda {
    
    public static Premio buscarPremio(int idPremio){
        return null;
    }
    
    public static Concurso buscarConcurso(int idConcurso){
        for(Concurso c: Concurso.readFile("Concurso.txt")){
            if(c.getId() == idConcurso)
                return c;
        }
        return null;
    }
    
}
