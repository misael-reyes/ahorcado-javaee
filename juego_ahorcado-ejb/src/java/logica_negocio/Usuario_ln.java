/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logica_negocio;

import acceso_datos.UsuarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Usuario;

/**
 *
 * @author Misael
 * 
 * Session Bean
 * 
 */
@Stateless
@LocalBean
public class Usuario_ln {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    public void guardar(Usuario user) {
        usuarioFacade.create(user);
    }
    
    public List<Usuario> lista_u() {
        return usuarioFacade.findAll();
    }
    
    public Usuario recuperaUsuario(int id) {
        return usuarioFacade.find(id);
    }
}
