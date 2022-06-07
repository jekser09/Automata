/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
/**
 *
 * @author johand
 */
public class Archivo {
    private String nombreArchivo;
    private String nombreArchivo2;
    private BufferedWriter bw;
    private BufferedReader br;
    
    
    public Archivo(){
        this.nombreArchivo="cadenas.txt";
        this.nombreArchivo2="validacion.txt";
        try{
        br=new BufferedReader(new FileReader(nombreArchivo));        
        }catch(FileNotFoundException fx){
            crearArchivoCadenas();            
        }
    }
    
    public void crearArchivoCadenas(){
        try{
            bw=new BufferedWriter(new FileWriter(nombreArchivo,true));
            bw.write("");
            bw.flush();
            bw.close();
            JOptionPane.showMessageDialog(null,"Archivo creado con exito","informacion",JOptionPane.INFORMATION_MESSAGE);
        }catch(IOException iox){
            JOptionPane.showMessageDialog(null,"Error al crear archivo: "+iox.getMessage(),"ALERTA",JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    public void crearArchivoSalida(){        
        try{
            bw=new BufferedWriter(new FileWriter("validacion.txt",true));
            bw.write("");
            bw.flush();
            bw.close();
            JOptionPane.showMessageDialog(null,"Archivo creado con exito","informacion",JOptionPane.INFORMATION_MESSAGE);
        }catch(IOException iox){
            JOptionPane.showMessageDialog(null,"Error al crear archivo: "+iox.getMessage(),"ALERTA",JOptionPane.ERROR_MESSAGE);
        }       
    }
    
    public String leerArchivo(){
        
        String linea="";        
        try{                        
            if(!(linea==null)){            
            linea=br.readLine();                        
            }
        }catch(IOException iox){
            JOptionPane.showMessageDialog(null,"Error de lectura de archivo"+"("+iox.getMessage()+")","ALERTA",JOptionPane.ERROR_MESSAGE);            
        }      
        return linea;
    }
    
    public void escribir(String cadena){        
        try{
            bw=new BufferedWriter(new FileWriter(nombreArchivo2,true));
            bw.append(cadena);            
            bw.newLine();
            bw.close();         
        }catch(IOException iox){
            JOptionPane.showMessageDialog(null,"archivo no encontrado");
        }        
        
    }
    
    public void borrar(){
        try{
            bw=new BufferedWriter(new FileWriter(nombreArchivo2));
            bw.append("");          
            bw.close();         
        }catch(IOException iox){
            JOptionPane.showMessageDialog(null,"archivo no encontrado");
        } 
    }
    
    
}
