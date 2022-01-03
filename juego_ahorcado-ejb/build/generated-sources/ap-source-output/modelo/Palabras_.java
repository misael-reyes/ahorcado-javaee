package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.JuegosUsuario;
import modelo.Tipopalabras;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-01-02T20:35:53")
@StaticMetamodel(Palabras.class)
public class Palabras_ { 

    public static volatile CollectionAttribute<Palabras, JuegosUsuario> juegosUsuarioCollection;
    public static volatile SingularAttribute<Palabras, Tipopalabras> tipo;
    public static volatile SingularAttribute<Palabras, Integer> idpalabra;
    public static volatile SingularAttribute<Palabras, String> description;
    public static volatile SingularAttribute<Palabras, Integer> nivel;

}