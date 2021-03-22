package application.entity;

import application.model.RelationshipId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
//@IdClass(RelationshipId.class)
@Table(name = "utilisateur", schema = "public")
public class Utilisateur implements Serializable{


    @Id
    @Column(name="identifiant_utilisateur")
    private String identifiant_utilisateur;

    @Column(name = "sexe_utilisateur")
    private String sexe_utilisateur;

    @Column(name="mail_utilisateur")
    private String mail_utilisateur;

    @Column(name="nom_utilisateur")
    private String nom_utilisateur;

    @Column(name="prenom_utilisateur")
    private String prenom_utilisateur;

    @Column(name="mot_de_passe_utilisateur")
    private String mot_de_passe_utilisateur;

    @Column(name="date_inscription")
    private java.util.Date date_inscription;

    @Column(name="privilege_utilisateur")
    private int privilege_utilisateur;

    public Utilisateur(){

    }

    public Utilisateur(String identifiant_utilisateur, String sexe_utilisateur, String mail_utilisateur, String nom_utilisateur, String prenom_utilisateur, String mot_de_passe_utilisateur, Date date_inscription, int privilege_utilisateur){
        this.identifiant_utilisateur = identifiant_utilisateur;
        this.sexe_utilisateur = sexe_utilisateur;
        this.mail_utilisateur = mail_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.mot_de_passe_utilisateur = mot_de_passe_utilisateur;
        this.date_inscription = date_inscription;
        this.privilege_utilisateur = privilege_utilisateur;
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "identifiant_utilisateur='" + identifiant_utilisateur + '\'' +
                ", sexe_utilisateur='" + sexe_utilisateur + '\'' +
                ", mail_utilisateur='" + mail_utilisateur + '\'' +
                ", nom_utilisateur='" + nom_utilisateur + '\'' +
                ", prenom_utilisateur='" + prenom_utilisateur + '\'' +
                ", mot_de_passe_utilisateur='" + mot_de_passe_utilisateur + '\'' +
                ", date_inscription=" + date_inscription +
                ", privilege_utilisateur=" + privilege_utilisateur +
                '}';
    }

    public String getIdentifiant_utilisateur() {
        return identifiant_utilisateur;
    }

    public void setIdentifiant_utilisateur(String identifiant_utilisateur) {
        this.identifiant_utilisateur = identifiant_utilisateur;
    }

    public String getSexe_utilisateur() {
        return sexe_utilisateur;
    }

    public void setSexe_utilisateur(String sexe_utilisateur) {
        this.sexe_utilisateur = sexe_utilisateur;
    }

    public String getMail_utilisateur() {
        return mail_utilisateur;
    }

    public void setMail_utilisateur(String mail_utilisateur) {
        this.mail_utilisateur = mail_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
    }

    public String getMot_de_passe_utilisateur() {
        return mot_de_passe_utilisateur;
    }

    public void setMot_de_passe_utilisateur(String mot_de_passe_utilisateur) {
        this.mot_de_passe_utilisateur = mot_de_passe_utilisateur;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public int getPrivilege_utilisateur() {
        return privilege_utilisateur;
    }

    public void setPrivilege_utilisateur(int privilege_utilisateur) {
        this.privilege_utilisateur = privilege_utilisateur;
    }
}
