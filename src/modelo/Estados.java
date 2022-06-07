/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author johand
 */
public final class Estados {
    private String cadena="";
    private String estadoActual="A";
    private boolean estadoAcepta=false;    
    private String tipoCadena="";

    public Estados(String cadena, String estadoActual, boolean estadoAcepta, String tipocadena) {
        this.cadena=cadena;
        this.estadoActual=estadoActual;
        this.estadoAcepta=estadoAcepta;
        this.tipoCadena=tipocadena;
    }
    public Estados(){
        
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public boolean isEstadoAcepta() {
        return estadoAcepta;
    }

    public void setEstadoAcepta(boolean estadoAcepta) {
        this.estadoAcepta = estadoAcepta;
    }
    
    public String getTipoCadena() {
        return tipoCadena;
    }

    public void setTipoCadena(String tipoCadena) {
        this.tipoCadena = tipoCadena;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
    
    
    
    
}
