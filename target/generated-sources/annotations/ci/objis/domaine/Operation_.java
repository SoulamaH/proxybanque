package ci.objis.domaine;

import ci.objis.domaine.Compte;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-12-28T17:26:46")
@StaticMetamodel(Operation.class)
public abstract class Operation_ { 

    public static volatile SingularAttribute<Operation, Double> montant;
    public static volatile SingularAttribute<Operation, Long> id;
    public static volatile SingularAttribute<Operation, Date> dateOperation;
    public static volatile SingularAttribute<Operation, String> type;
    public static volatile SingularAttribute<Operation, Compte> compte;

}