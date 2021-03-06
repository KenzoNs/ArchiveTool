package application.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RelationshipId implements Serializable {

    private int id;
    private String userLogin;
}
