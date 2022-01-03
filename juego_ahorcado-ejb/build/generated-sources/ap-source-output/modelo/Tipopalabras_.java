package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Palabras;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-01-02T20:35:53")
@StaticMetamodel(Tipopalabras.class)
public class Tipopalabras_ { 

    public static volatile SingularAttribute<Tipopalabras, Integer> idtipos;
    public static volatile CollectionAttribute<Tipopalabras, Palabras> palabrasCollection;
    public static volatile SingularAttribute<Tipopalabras, String> description;

}