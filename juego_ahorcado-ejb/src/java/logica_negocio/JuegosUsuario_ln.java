/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logica_negocio;

import acceso_datos.JuegosUsuarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.JuegosUsuario;

/**
 *
 * @author Misael
 */
@Stateless
@LocalBean
public class JuegosUsuario_ln {

    @EJB
    private JuegosUsuarioFacade juegosUserFacade;
    
    public void guardar(JuegosUsuario ju) {
        juegosUserFacade.create(ju);
    }
    
    public List<JuegosUsuario> lista_ju() {
        return juegosUserFacade.findAll();
    }
}
