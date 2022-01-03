/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package juego;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import logica_negocio.JuegosUsuario_ln;
import logica_negocio.Palabras_ln;
import logica_negocio.Tipos_ln;
import logica_negocio.Usuario_ln;
import modelo.JuegosUsuario;
import modelo.Palabras;
import modelo.Tipopalabras;
import modelo.Usuario;

/**
 *
 * @author Misael
 */
@Named(value = "ahorcado")
@SessionScoped
public class Ahorcado implements Serializable {

    @EJB
    private Palabras_ln palabras_ln;
    @EJB
    private Tipos_ln tipos_ln;
    @EJB
    private Usuario_ln usuario_ln;
    @EJB
    private JuegosUsuario_ln ju_ln;

    private Palabras palabras;
    private Tipopalabras tipo;
    private Usuario user;
    private int idTP = 1;

    //atributos para el inicio de sesión
    private String alias;
    private int id;

    //atributos para la configuración del juego
    private int idTipo;
    private int nivel;
    private int intentos = 0; //auxiliar
    private int intentosr = 0; //intentos realizados
    private int intentost = 0; //intentos totales
    private JuegosUsuario ju;
    private boolean gano = false;
    
    //atributos para el juego
    private ArrayList<String> palabraOculta;
    private String[] palabrass = {"ACERTASTE", "ARTISTICO", "NACIONAL", "EVENTO", "CULTURAL"};
    private String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private String p = "DEFAULT"; //esta será la palabra que se selecciono al azar
    private ArrayList<Character> letras;
    private String idLetra = "";
    private Character idGuion;
    
    //atributos para mostrar la información del juego de cada usuario
    private List<JuegosUsuario> juegos;
    
    //atributos para saber si gano el jugador y mostrar mensaje
    private String mensaje = "";
    private int conta = 0;
    

    public Ahorcado() {
        palabras = new Palabras();
        tipo = new Tipopalabras();
        user = new Usuario();
        ju = new JuegosUsuario();
    }
    
    /**
     * método para rellenar de _ según en número de letras de la palabra
     * seleccionada
     */
    public void rellenaPalabraOculta() {
        palabraOculta = new ArrayList();
        for (int i = 0; i < p.length(); i++) {
            palabraOculta.add("__");
        }
    }
    
    /**
     * método para agregar el abecedario en el array list
     */
    public void agregar() {
        letras = new ArrayList();
        //hacemos un for para agregar el abecedario
        for (int l = 0; l < abecedario.length(); l++) {
            letras.add(abecedario.charAt(l));
        }
    }
    
    /**
     * actualiza cada que el usuario selecciona una letra
     * @param e 
     */
    public void actualiza(ActionEvent e) throws IOException {
        intentosr ++;
        idLetra = e.getComponent().getId();
        int pos = Integer.parseInt(idLetra.replace("j",""));
        idGuion = letras.get(pos);
        letras.set(pos,'_');
        
        for(int i= 0; i<p.length(); i++){            
            if(p.charAt(i) == idGuion){
                palabraOculta.set(i, idGuion +"-");
                conta ++;
            }
        }
        if(intentos > 1) {
            intentos --;
            if(conta == palabraOculta.size()) {
                mensaje = "Felicidades, ha ganado el juego";
                terminaJuego();
            }
        } else {
            mensaje = "Lo sentimos, suerte para la proxima";
            terminaJuego();
        }
    }
    
    private void terminaJuego() throws IOException {
        guardaInfoJuego();
        FacesContext.getCurrentInstance().getExternalContext().redirect("lista_juegos.xhtml");
    }
    
    /**
     * despues de jugar, tenemos que guardar la información del juego en la base de datos
     */
    private void guardaInfoJuego() {
        ju.setUsuario(user);
        ju.setPalabra(palabras);
        ju.setIntentosr(intentosr);
        ju.setIntentost(intentost);
        ju.setFechaHora(obtenerFecha());
        ju_ln.guardar(ju);
    }
    
    public ArrayList getPalabraoculta() {
        return palabraOculta;
    }
    
    public ArrayList<Character> getLetras() {
        return letras;
    }
    
    public String getIdLetra() {
        return idLetra;
    }

    //esto es para retornar las palabras
    public List<Palabras> lista_p() {
        return palabras_ln.lista_p();
    }

    public List<Usuario> lista_u() {
        return usuario_ln.lista_u();
    }
    
    public List<JuegosUsuario> lista_ju() {
        List<JuegosUsuario> j = ju_ln.lista_ju();
        juegos = new ArrayList<>();
        for(int i = 0; i < j.size(); i ++) {
            if(j.get(i).getUsuario().getIdusuario() == user.getIdusuario())
                juegos.add(j.get(i));
        }
        return juegos;
    }
    
    /**
     * método para cerrar sesión del usuario
     */
    public void cerrarSesion() throws IOException {
        user = new Usuario();
        FacesContext.getCurrentInstance().getExternalContext().redirect("inicio_sesion.xhtml");
    }
    
    public void juegoNuevo() throws IOException {
        //limpiamos estas variables para el nuevo juego
        intentost = 0;
        intentosr = 0;
        intentos = 0;
        conta = 0;
        FacesContext.getCurrentInstance().getExternalContext().redirect("configuracion_juego.xhtml");
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getIntentosr() {
        return intentosr;
    }

    public void setIntentosr(int intentosr) {
        this.intentosr = intentosr;
    }
    
    public int getIntentost() {
        return intentost;
    }

    public void setIntentost(int intentost) {
        this.intentost = intentost;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Tipopalabras getTipo() {
        return tipo;
    }

    public void setTipo(Tipopalabras tipo) {
        this.tipo = tipo;
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

    //guarda palabra en la base de datos
    public void guardarPalabra() {
        tipo = tipos_ln.recuperaTipo(idTP);
        if (tipo != null) {
            palabras.setTipo(tipo);
            palabras_ln.guardar(palabras);
        }
    }

    //guarda usuario en la base de datos
    public void guardarUsuario() throws IOException {
        user.setFechaRegistro(obtenerFecha());
        usuario_ln.guardar(user);
        FacesContext.getCurrentInstance().getExternalContext().redirect("inicio_sesion.xhtml");
    }

    /**
     * método para obtener la fecha actual del sistema
     * 
     *
     * @return
     */
    public Date obtenerFecha() {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        return date;
    }

    public List<Tipopalabras> lista_t() {
        return tipos_ln.lista_t();
    }

    public List<Niveles> niveles() {
        String[] nvs = {"Facil", "Regular", "Dificil"};
        List<Niveles> lnvs = new ArrayList<Niveles>();
        for (int i = 0; i < nvs.length; i++) {
            lnvs.add(new Niveles(i + 1, nvs[i]));
        }
        return lnvs;
    }

    public List<TipoPersona> tipoPersonas() {
        String[] tipos = {"Niño", "Joven", "Adulto"};
        List<TipoPersona> tp = new ArrayList<TipoPersona>();
        for (int i = 0; i < tipos.length; i++) {
            tp.add(new TipoPersona(i + 1, tipos[i]));
        }
        return tp;
    }

    /**
     * metodo para validar que un usuario este registrado en la base de datos
     *
     * @throws IOException
     */
    public void login() throws IOException {
        user = usuario_ln.recuperaUsuario(id);
        if (user != null) {
            if (user.getNombre().equals(alias) && user.getIdusuario() == id) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("configuracion_juego.xhtml");
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales invalidas", "Credenciales invalidas");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no encontrado", "Credenciales invalidas");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * obtenemos la palabra y redireccionamos al usaurio al juego con la palabra
     * ya seleccionada
     */
    public void obtenerPalabra() throws IOException {
        tipo = tipos_ln.recuperaTipo(idTipo);
        ArrayList<Palabras> ap = new ArrayList<>();
        //recuperamos a todas las palabras
        List<Palabras> lp = lista_p();
        if (lp.size() != 0) {
            for (int i = 0; i < lp.size(); i++) {
                if (lp.get(i).getTipo().getIdtipos() == tipo.getIdtipos() && lp.get(i).getNivel() == nivel) {
                    ap.add(lp.get(i));
                }
            }
        }
        //ahora queda obtener una palabra al azar de ap
        Random oran = new Random();
        if (ap.size() != 0) {
            palabras = ap.get(oran.nextInt(ap.size()));
            p = palabras.getDescription();
        }
        agregar();
        rellenaPalabraOculta();
        intentos = intentost;
        FacesContext.getCurrentInstance().getExternalContext().redirect("jugar.xhtml");
    }

    /**
     * Esta clase es exclusivo para los niveles al momento de guardar una
     * palabra
     */
    public class Niveles {

        private int gnivel;
        private String descripcion;

        public Niveles(int n, String d) {
            gnivel = n;
            descripcion = d;
        }

        public int getGnivel() {
            return gnivel;
        }

        public void setGnivel(int g) {
            gnivel = g;
        }

        public String getDesc() {
            return descripcion;
        }

        public void setDesc(String d) {
            descripcion = d;
        }
    }

    /**
     * Esta clase será para el tipo de persona al momento de agregar un usuario
     * a la base de datos
     */
    public class TipoPersona {

        //1: niño, 2: joven, 3: adulto
        private int tpersona;
        private String descrip;

        public TipoPersona(int t, String de) {
            tpersona = t;
            descrip = de;
        }

        public int getTpersona() {
            return tpersona;
        }

        public void setTpersona(int tpersona) {
            this.tpersona = tpersona;
        }

        public String getDescrip() {
            return descrip;
        }

        public void setDescrip(String descrip) {
            this.descrip = descrip;
        }
    }
}
