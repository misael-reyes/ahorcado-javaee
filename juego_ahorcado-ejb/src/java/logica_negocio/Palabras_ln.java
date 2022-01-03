/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logica_negocio;

import acceso_datos.PalabrasFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Palabras;

/**
 *
 * @author Misael
 */
@Stateless
@LocalBean
public class Palabras_ln {
    //la utilización de un componente
    @EJB
    private PalabrasFacade palabrasFacade;
    
    public void guardar(Palabras palabra) {
        palabrasFacade.create(palabra);
    }
    
    public List<Palabras> lista_p() {
        return palabrasFacade.findAll();
    }
}
