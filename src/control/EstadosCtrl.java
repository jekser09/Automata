/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import modelo.Estados;

/**
 *
 * @author johand
 */
public class EstadosCtrl {
    private Estados estEnteros;
    private Estados estReales;
    private Estados estNotC;
    private Estados estCorreo;
    private String correcto;

    public EstadosCtrl(String cadena){       
        validarEntero(cadena);
        validarReal(cadena);
        validarNotC(cadena);
        validarCorreo(cadena);
        correcto=valido();
    }
    
    private void validarEntero(String cadena){
        estEnteros=new Estados();
        estEnteros.setCadena(cadena);
        int i=0;
        int posError=0;
        String tipo="";       
        while(i<cadena.length()){            
            if(Character.toString(cadena.charAt(i)).equals("+")){
                if(estEnteros.getEstadoActual().equals("A")){
                    estEnteros.setEstadoActual("B");
                    tipo="positivo";
                    i++;
                }else{
                    estEnteros.setEstadoAcepta(false);
                    tipo="error: "+cadena.charAt(i);
                    break;
                }
            }else{
                if(Character.toString(cadena.charAt(i)).equals("-")){
                    if(estEnteros.getEstadoActual().equals("A")){
                        estEnteros.setEstadoActual("C");
                        tipo="negativo";
                        i++;
                    }else{                        
                        estEnteros.setEstadoAcepta(false);
                        tipo="error en: "+cadena.charAt(i);
                        posError=i;
                        break;
                    }
                }else{
                    if(Character.isDigit(cadena.charAt(i))){
                        switch (estEnteros.getEstadoActual()){
                            case "A":
                                estEnteros.setEstadoActual("D");
                                estEnteros.setEstadoAcepta(true);
                                tipo="sin signo";
                                i++;
                                break;
                            case "B":
                                estEnteros.setEstadoActual("D");
                                estEnteros.setEstadoAcepta(true);
                                i++;
                                break;
                            case "C":
                                estEnteros.setEstadoActual("D");
                                estEnteros.setEstadoAcepta(true);
                                i++;
                                break;
                            case "D":                                
                                i++;
                                break;
                            default:
                                estEnteros.setEstadoAcepta(false);
                                tipo="error en: "+cadena.charAt(i);
                                posError=i;
                                i=cadena.length();
                                break;
                        }
                    }else{
                        tipo="error en: "+cadena.charAt(i);
                        posError=i;
                        estEnteros.setEstadoAcepta(false);                        
                        break;
                    }
                }
            }          
        }
        if(estEnteros.isEstadoAcepta()){           
            if(estEnteros.getEstadoActual().equals("D")){
                tipo="Entero "+tipo;                
            }else{                
                tipo="error sintactico";
            }
        }else{            
            try{
            if(!tipo.equals("error en: "+cadena.charAt(posError)))tipo="error sintactico";
            }catch(StringIndexOutOfBoundsException ex){
                tipo="error sintactico";
            }           
        }
        estEnteros.setTipoCadena(tipo);
        //return tipo;
    }
    
    private void validarReal(String cadena){
        estReales=new Estados();
        estReales.setCadena(cadena);
        int i=0;
        String tipo="";       
        while(i<cadena.length()){            
            if(Character.toString(cadena.charAt(i)).equals("+")){
                if(estReales.getEstadoActual().equals("A")){
                    estReales.setEstadoActual("B");
                    tipo="positivo";
                    i++;
                }else{
                    tipo="error "+Character.toString(cadena.charAt(i));
                    estReales.setEstadoAcepta(false);
                    break;
                }
            }else{
                if(Character.toString(cadena.charAt(i)).equals("-")){
                    if(estReales.getEstadoActual().equals("A")){
                        estReales.setEstadoActual("C");
                        tipo="negativo";
                        i++;
                    }else{
                        tipo="error "+Character.toString(cadena.charAt(i));
                        estReales.setEstadoAcepta(false);
                        break;
                    }
                }else{
                    if(Character.isDigit(cadena.charAt(i))){
                    switch(estReales.getEstadoActual()){
                        case "A":                        
                            estReales.setEstadoActual("D");
                            tipo="sin signo";
                            i++;
                            break;
                        case "B":
                            estReales.setEstadoActual("D");
                            i++;
                            break;
                        case "C":
                            estReales.setEstadoActual("D");
                            i++;
                            break;
                        case "D":
                            estReales.setEstadoActual("D");
                            i++;
                            break;
                        case "E":
                            estReales.setEstadoActual("F");
                            estReales.setEstadoAcepta(true);
                            i++;
                            break;
                        case "F":
                            i++;
                            break;
                    }
                    }else{
                        if(Character.toString(cadena.charAt(i)).equals(".")){
                            if(estReales.getEstadoActual().equals("D")){
                                estReales.setEstadoActual("E");
                                i++;
                            }else{
                            tipo="error "+Character.toString(cadena.charAt(i));
                            estReales.setEstadoAcepta(false);
                            break;
                            }
                        }else{
                            tipo="error "+Character.toString(cadena.charAt(i));
                            estReales.setEstadoAcepta(false);
                            break;
                        }              
                    }
                }                
            }            
        }
        if(estReales.isEstadoAcepta()){
            if(estReales.getEstadoActual().equals("F")){
                tipo="Real "+tipo;
            }else{                
                if(!tipo.equals("error "+Character.toString(cadena.charAt(i))))tipo="error sintactico";                
            }
            
        }else{            
            try{
            if(!tipo.equals("error "+Character.toString(cadena.charAt(i))))tipo="error sintactico";
            }catch(StringIndexOutOfBoundsException ex){
                    tipo="error sintactico";
            }            
        }
        estReales.setTipoCadena(tipo);        
    }
    
    private void validarNotC(String cadena){
        estNotC=new Estados();
        estNotC.setCadena(cadena);
        int i=0;
        String tipo="";
        while(i<cadena.length()){
            if(Character.toString(cadena.charAt(i)).equals("+")){
                if(estNotC.getEstadoActual().equals("A")){
                    estNotC.setEstadoActual("B");
                    tipo="positivo";
                    i++;
                }else{
                    if(estNotC.getEstadoActual().equals("G")){
                        estNotC.setEstadoActual("H");                        
                        i++;
                    }else{
                        estNotC.setEstadoAcepta(false);
                        tipo="error en: "+cadena.charAt(i);
                        break;
                    }
                }
            }else{
                if(Character.toString(cadena.charAt(i)).equals("-")){
                    if(estNotC.getEstadoActual().equals("A")){
                        estNotC.setEstadoActual("C");
                        tipo="negativo";
                        i++;                        
                    }else{
                        if(estNotC.getEstadoActual().equals("G")){
                            estNotC.setEstadoActual("I");
                            i++;
                        }else{
                            estNotC.setEstadoAcepta(false);
                            tipo="error en: "+cadena.charAt(i);
                            break;
                        }
                    }
                }else{
                    if(Character.isDigit(cadena.charAt(i))){
                        switch(estNotC.getEstadoActual()){
                            case "A":
                                estNotC.setEstadoActual("D");
                                tipo= "sin signo";
                                i++;
                                break;
                            case "B":
                                estNotC.setEstadoActual("D");
                                i++;
                                break;
                            case "C":
                                estNotC.setEstadoActual("D");
                                i++;
                                break;
                            case "D":
                                i++;
                                break;
                            case "E":
                                estNotC.setEstadoActual("F");
                                i++;
                                break;
                            case "F":
                                i++;
                                break;
                            case "G":
                                estNotC.setEstadoActual("J");
                                estNotC.setEstadoAcepta(true);
                                i++;
                                break;
                            case "H":
                                estNotC.setEstadoActual("J");
                                estNotC.setEstadoAcepta(true);
                                i++;
                                break;
                            case "I":
                                estNotC.setEstadoActual("J");
                                estNotC.setEstadoAcepta(true);
                                i++;
                                break;
                            case "J":
                                i++;
                                break;
                                
                        }
                    }else{
                        if(Character.toString(cadena.charAt(i)).equals(".")){
                            if(estNotC.getEstadoActual().equals("D")){
                                estNotC.setEstadoActual("E");
                                i++;
                            }else{
                                tipo="error en: "+cadena.charAt(i);
                                estNotC.setEstadoAcepta(false);
                                break;
                            }
                        }else{
                            if(Character.toString(cadena.charAt(i)).equals("E")){
                                if(estNotC.getEstadoActual().equals("F")){
                                    estNotC.setEstadoActual("G");
                                    i++;
                                }else{
                                    tipo="error en: "+cadena.charAt(i);
                                    estNotC.setEstadoAcepta(false);
                                    break;
                                }
                            }else{
                                tipo="error en: "+cadena.charAt(i);
                                estNotC.setEstadoAcepta(false);
                                break;
                            }
                        }
                    }
                }                
            }
        }
        if(estNotC.isEstadoAcepta()){
            if(estNotC.getEstadoActual().equals("J")){
                tipo="NotC "+tipo;
            }else{
                if(!tipo.equals("error en: "+cadena.charAt(i))) tipo="error sintactico";
            }
        }else{
            try{
                if(!tipo.equals("error en: "+Character.toString(cadena.charAt(i))))tipo="error sintactico";
            }catch(StringIndexOutOfBoundsException ex){
                tipo="error sintactico";
            }
        }
        estNotC.setTipoCadena(tipo);        
    }
    
    private void validarCorreo(String cadena){
        estCorreo=new Estados();
        estCorreo.setCadena(cadena);
        int i=0;
        int posError=0;
        String confirmacion="";
        while(i<cadena.length()){
            if(Character.isLetter(cadena.charAt(i))){                
                switch (estCorreo.getEstadoActual()){
                    case "A":
                        estCorreo.setEstadoActual("B");
                        i++;
                        break;
                    case "B":
                        i++;
                        break;
                    case "C":
                        estCorreo.setEstadoActual("B");
                        i++;
                        break;
                    case "D":
                        estCorreo.setEstadoActual("B");
                        i++;
                        break;
                    case "E":
                        estCorreo.setEstadoActual("F");
                        i++;
                        break;
                    case "F":
                        i++;
                        break;
                    case "G":
                        estCorreo.setEstadoActual("F");
                        i++;
                        break;
                    case "H":
                        estCorreo.setEstadoActual("I");
                        estCorreo.setEstadoAcepta(true);
                        i++;
                        break;
                    case "I":
                        i++;
                        break;
                }                               
            }else{
                if(Character.isDigit(cadena.charAt(i))){
                    switch(estCorreo.getEstadoActual()){
                        case "A":
                            estCorreo.setEstadoActual("C");
                            i++;
                            break;
                        case "B":
                            estCorreo.setEstadoActual("C");
                            i++;
                            break;
                        case "C":
                            i++;
                            break;
                        case "D":
                            estCorreo.setEstadoActual("C");
                            i++;
                            break;
                        case "E":
                            estCorreo.setEstadoActual("G");
                            i++;
                            break;
                        case "F":
                            estCorreo.setEstadoActual("G");
                            i++;
                            break;
                        case "G":
                            i++;
                            break;
                        default:
                            estCorreo.setEstadoAcepta(false);
                            confirmacion="error en: "+cadena.charAt(i);
                            posError=i;
                            i=cadena.length();                            
                            break;
                    }
                }else{
                    if(caracterEspecial(cadena.charAt(i))){
                        switch (estCorreo.getEstadoActual()){
                            case "A":
                                estCorreo.setEstadoActual("D");
                                i++;
                                break;
                            case "B":
                                estCorreo.setEstadoActual("D");
                                i++;
                                break;
                            case "C":
                                estCorreo.setEstadoActual("D");
                                i++;
                                break;
                            case "D":
                                i++;
                                break;
                            default:
                                estCorreo.setEstadoAcepta(false);
                                confirmacion="error en: "+cadena.charAt(i);
                                posError=i;
                                i=cadena.length();
                                break;
                        }
                    }else{
                        if(Character.toString(cadena.charAt(i)).equals("@")){                            
                            if(estCorreo.getEstadoActual().equals("B")){
                                estCorreo.setEstadoActual("E");
                                i++;
                            }else{
                                if(estCorreo.getEstadoActual().equals("C")){
                                    estCorreo.setEstadoActual("E");
                                    i++;
                                }else{
                                    if(estCorreo.getEstadoActual().equals("D")){
                                        estCorreo.setEstadoActual("E");
                                        i++;
                                    }else{
                                        confirmacion="error en: "+cadena.charAt(i);
                                        estCorreo.setEstadoAcepta(false);
                                        break;
                                    }
                                        
                                }                               
                                
                            }
                        }else{
                            if(Character.toString(cadena.charAt(i)).equals(".")){
                                if(estCorreo.getEstadoActual().equals("F")){
                                    estCorreo.setEstadoActual("H");
                                    i++;
                                }else{
                                    if(estCorreo.getEstadoActual().equals("G")){
                                        estCorreo.setEstadoActual("H");
                                        i++;
                                    }else{
                                        confirmacion="error en: "+cadena.charAt(i);
                                        estCorreo.setEstadoAcepta(false);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(estCorreo.isEstadoAcepta()){
            if(estCorreo.getEstadoActual().equals("I")){
                confirmacion="correo valido";
            }
        }else{
            if(!confirmacion.equals("error en: "+cadena.charAt(posError)))confirmacion="correo invalido";
        }
        estCorreo.setTipoCadena(confirmacion);        
    }
    
    private boolean caracterEspecial(char caracter){
        boolean bandera=false;
        if(caracter >32 && caracter<48 || caracter >57 && caracter<64){
            if(caracter!=46){
                bandera=true;
            }
        }else{
            if(caracter>90 && caracter<97 || caracter>122 && caracter<128){                
                bandera=true;
            }
        }       
        return bandera;
    }
    
    private String valido(){
        String palabra="";
        if(estEnteros.isEstadoAcepta()){
            palabra=estEnteros.getTipoCadena();
        }else{
            if(estReales.isEstadoAcepta()){
                palabra=estReales.getTipoCadena();
            }else{
                if(estNotC.isEstadoAcepta()){
                    palabra=estNotC.getTipoCadena();
                }else{
                    if(estCorreo.isEstadoAcepta()){
                        palabra=estCorreo.getTipoCadena();
                    }else{
                        palabra="error";
                    }
                }
            }
        }
               
        return palabra;
    }

    public Estados getEstEnteros() {
        return estEnteros;
    }

    public Estados getEstReales() {
        return estReales;
    }

    public Estados getEstNotC() {
        return estNotC;
    }

    public Estados getEstCorreo() {
        return estCorreo;
    }

    public String getCorrecto() {
        return correcto;
    }  
    
}

