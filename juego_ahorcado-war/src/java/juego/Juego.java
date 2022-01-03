/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import logica_negocio.Palabras_ln;
import logica_negocio.Tipos_ln;
import modelo.Palabras;
import modelo.Tipopalabras;

/**
 *
 * @author Misael
 */
@Named(value = "juego")
@SessionScoped
public class Juego implements Serializable {

    /**
     * llamadas a componentes
     */
    
    @EJB
    private Palabras_ln palabras_ln;
    
    @EJB
    private Tipos_ln tipos_ln;
    
    private Palabras palabras;
    private Tipopalabras tipo;
    private int idTP = 1;

    private ArrayList<Character> letras;
    private ArrayList<String> palabraOculta;
    //podemos agregar más palabras
    private String[] palabrass = {"ACERTASTE", "ARTISTICO", "NACIONAL", "EVENTO", "CULTURAL"};
    private String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private String palabra = "";
    private String idLetra = "";
    private Character idGuion;

    public Juego() {
        Random pa = new Random();
        agregar();
        palabra = palabrass[pa.nextInt(palabrass.length)];
        //rellenamos la palabra con _ para mostrar la palabra oculta
        rellenaPalabraOculata();
        
        palabras = new Palabras();
        tipo = new Tipopalabras();
    }

    public void agregar() {
        letras = new ArrayList();
        //hacemos un for para agregar el abecedario
        for (int l = 0; l < abecedario.length(); l++) {
            letras.add(abecedario.charAt(l));
        }
    }

    public void actualiza(ActionEvent e) {
        idLetra = e.getComponent().getId();
        int pos = letras.indexOf(idLetra.charAt(0));
        //eliminamos la letra presionada
        letras.remove(pos);
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == idLetra.charAt(0))
                palabraOculta.set(i, idLetra);
        }
    }
    
    public void actualiza2(ActionEvent e) {
        idLetra = e.getComponent().getId();
        int pos = Integer.parseInt(idLetra.replace("j",""));
        idGuion = letras.get(pos);
        letras.set(pos,'_');
        
        for(int i= 0; i<palabra.length(); i++){            
            if(palabra.charAt(i) == idGuion){
                palabraOculta.set(i, idGuion +"-");
            }
        }
    }

    public String getIdLetra() {
        return idLetra;
    }

    public String getPalabra() {
        return palabra;
    }

    public ArrayList<Character> getLetras() {
        return letras;
    }

    /**
     * palabra con solo _
     *
     * @return
     */
    public ArrayList getPalabraoculta() {
        return palabraOculta;
    }

    /**
     * método para rellenar de _ según en número de letras de la palabra
     * seleccionada
     */
    public void rellenaPalabraOculata() {
        palabraOculta = new ArrayList();
        for (int i = 0; i < palabra.length(); i++) {
            palabraOculta.add("__");
        }
    }
    
    public Palabras getPalabras() {
        return palabras;
    }
    
    public void setPalabras(Palabras palabras) {
        this.palabras = palabras;
    }

    public int getIdTP() {
        return idTP;
    }

    public void setIdTP(int idTP) {
        this.idTP = idTP;
    }
    
    public void guardarPalabra() {
        tipo = tipos_ln.recuperaTipo(idTP);
        if (tipo!=null) {
            palabras.setTipo(tipo);
            palabras_ln.guardar(palabras);
        }
    }
}
