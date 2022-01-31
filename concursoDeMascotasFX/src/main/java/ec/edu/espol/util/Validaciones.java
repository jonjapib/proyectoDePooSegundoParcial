/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

/**
 *
 * @author Ana
 */
public class Validaciones {
    public static boolean esEntero(String cadena){
        try{
            int num = Integer.parseInt(cadena);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }catch(Exception ex){
            return false;
        }
    }
    
    public static boolean esDecimal(String cadena){
        try{
            double num = Double.parseDouble(cadena);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }catch(Exception ex){
            return false;
        }
    }
    
}
