package ci.objis.domaine;

import ci.objis.domaine.Client;
import ci.objis.domaine.Operation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-12-28T17:26:46")
@StaticMetamodel(Compte.class)
public abstract class Compte_ { 

    public static volatile SingularAttribute<Compte, String> numeroCompte;
    public static volatile SingularAttribute<Compte, Date> dateCreation;
    public static volatile SingularAttribute<Compte, Double> solde;
    public static volatile SingularAttribute<Compte, Client> client;
    public static volatile CollectionAttribute<Compte, Operation> Operation;

}