/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logica_negocio;

import acceso_datos.TipopalabrasFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Tipopalabras;

/**
 *
 * @author Misael
 * Tipo_p -> asi lo tien el profe
 */
@Stateless
@LocalBean
public class Tipos_ln {
    
    @EJB
    private TipopalabrasFacade tipoPalabrasFacade;
    
    /*
    public void guardar(Tipopalabras tipos) {
        tipoPalabrasFacade.create(tipos);
    }
    */
    
    public List<Tipopalabras> lista_t() {
        return tipoPalabrasFacade.findAll();
    }
    
    public Tipopalabras recuperaTipo(int id) {
        //
        return tipoPalabrasFacade.find(id);
    }

}
