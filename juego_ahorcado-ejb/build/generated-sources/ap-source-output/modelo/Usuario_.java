package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.JuegosUsuario;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-01-02T20:35:53")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile CollectionAttribute<Usuario, JuegosUsuario> juegosUsuarioCollection;
    public static volatile SingularAttribute<Usuario, Integer> tpersona;
    public static volatile SingularAttribute<Usuario, Date> fechaRegistro;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Integer> idusuario;

}