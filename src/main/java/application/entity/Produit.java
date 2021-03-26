package application.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@IdClass(RelationshipId.class)
@Table(name = "produit", schema = "public")
public class Produit implements Serializable {


    @Id
    @Column(name="identifiant_produit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifiant_produit;

    @Column(name="nom_produit")
    private String nom_produit;

    @Column(name="prix_unitaire_produit")
    private float prix_unitaire_produit;

    public int getIdentifiant_produit() {
        return identifiant_produit;
    }

    public void setIdentifiant_produit(int identifiant_produit) {
        this.identifiant_produit = identifiant_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public float getPrix_unitaire_produit() {
        return prix_unitaire_produit;
    }

    public void setPrix_unitaire_produit(float prix_unitaire_produit) {
        this.prix_unitaire_produit = prix_unitaire_produit;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "identifiant_produit=" + identifiant_produit +
                ", nom_produit='" + nom_produit + '\'' +
                ", prix_unitaire_produit=" + prix_unitaire_produit +
                '}';
    }

}
