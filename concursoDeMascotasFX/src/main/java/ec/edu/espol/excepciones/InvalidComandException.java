/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.excepciones;

/**
 *
 * @author Ana
 */
public class InvalidComandException extends RuntimeException{
    /**
     * Constructor por defecto.
     * Con el mensaje: Ha ingresado un comando inválido.
     */
    public InvalidComandException() {
        super("Ha ingresado un comando inválido.");
    }
    
    /**
     * Constructor que recibe el comando inválido que se intentó realizar.
     * @param invalidComand 
     */
    public InvalidComandException(String invalidComand){
        super("Ha ingresado el comando inválido:\n" +invalidComand);
    }

    
}
