/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso_datos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.JuegosUsuario;

/**
 *
 * @author Misael
 */
@Stateless
public class JuegosUsuarioFacade extends AbstractFacade<JuegosUsuario> {

    @PersistenceContext(unitName = "juego_ahorcado-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JuegosUsuarioFacade() {
        super(JuegosUsuario.class);
    }
    
}
