package application.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client", schema = "public")
public class Client implements Serializable {

    @Id
    @Column(name="identifiant_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifiant_client;

    @Column(name="nom_client")
    private String nom_client;

    @Column(name="num_tel_client ")
    private String num_tel_client;

    @Column(name="adresse_client")
    private String adresse_client;

    public int getIdentifiant_client() {
        return identifiant_client;
    }

    public void setIdentifiant_client(int identifiant_client) {
        this.identifiant_client = identifiant_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getNum_tel_client() {
        return num_tel_client;
    }

    public void setNum_tel_client(String num_tel_client) {
        this.num_tel_client = num_tel_client;
    }

    public String getAdresse_client() {
        return adresse_client;
    }

    public void setAdresse_client(String adresse_client) {
        this.adresse_client = adresse_client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "identifiant_client=" + identifiant_client +
                ", nom_client='" + nom_client + '\'' +
                ", num_tel_client='" + num_tel_client + '\'' +
                ", adresse_client='" + adresse_client + '\'' +
                '}';
    }
}
