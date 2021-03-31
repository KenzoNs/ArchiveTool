package application.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transaction", schema = "public")
public class Transaction implements Serializable {

    @Id
    @Column(name="identifiant_transaction")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifiant_transaction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="identifiant_client", nullable=false)
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="identifiant_produit", nullable=false)
    private Produit produit;

    @Column(name="nom_transaction")
    private String nom_transaction;

    @Column(name="etat_transaction")
    private String etat_transaction;

    @Column(name="date_transaction")
    private Date date_transaction;

    @Column(name="sujet_transaction")
    private String sujet_transaction;

    @Column(name="quantite")
    private int quantite;

    @Column(name="prix")
    private float prix;

    public Transaction(){

    }

    public Transaction(Client client, Produit produit, String nom_transaction, String etat_transaction, Date date_transaction, String sujet_transaction, int quantite){
        this.client = client;
        this.produit = produit;
        this.nom_transaction = nom_transaction;
        this.etat_transaction = etat_transaction;
        this.date_transaction = date_transaction;
        this.sujet_transaction = sujet_transaction;
        this.quantite = quantite;
    }

    public int getIdentifiant_transaction() {
        return identifiant_transaction;
    }

    public void setIdentifiant_transaction(int identifiant_transaction) {
        this.identifiant_transaction = identifiant_transaction;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getNom_transaction() {
        return nom_transaction;
    }

    public void setNom_transaction(String nom_transaction) {
        this.nom_transaction = nom_transaction;
    }

    public String getEtat_transaction() {
        return etat_transaction;
    }

    public void setEtat_transaction(String etat_transaction) {
        this.etat_transaction = etat_transaction;
    }

    public Date getDate_transaction() {
        return date_transaction;
    }

    public void setDate_transaction(Date date_transaction) {
        this.date_transaction = date_transaction;
    }

    public String getSujet_transaction() {
        return sujet_transaction;
    }

    public void setSujet_transaction(String sujet_transaction) {
        this.sujet_transaction = sujet_transaction;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifiant_transactiont=" + identifiant_transaction +
                ", client=" + client +
                ", produit=" + produit +
                ", nom_transaction='" + nom_transaction + '\'' +
                ", etat_transaction='" + etat_transaction + '\'' +
                ", date_transaction=" + date_transaction +
                ", sujet_transaction='" + sujet_transaction + '\'' +
                ", quantite=" + quantite +
                ", prix=" + prix +
                '}';
    }
}
