package application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(RelationshipId.class)
@Table(name = "utilisateurs", schema = "public")
public class User implements Serializable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Id
    @Column(name="identifiant_utilisateur")
    private String userLogin;

    @Column(name="adresse_mail")
    private String emailAdress;

    @Column(name="nom")
    private String name;

    @Column(name="prenom")
    private String firstName;

    @Column(name="mot_de_passe")
    private String password;

    @Column(name="date_inscription")
    private java.util.Date registrationDate;

    @Column(name="privilege")
    private int right;

    public User(){

    }

    public User(int id, String userLogin, String emailAdress, String name, String firstName, String password, Date registrationDate, int right){
        this.id = id;
        this.userLogin = userLogin;
        this.emailAdress = emailAdress;
        this.name = name;
        this.firstName = firstName;
        this.password = password;
        this.registrationDate = registrationDate;
        this.right = right;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin() {
        return this.userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getEmailAdress() {
        return this.emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getPower() {
        return this.right;
    }

    public void setPower(int power) {
        this.right = power;
    }

    @Override
    public String toString() {
        return "User: id= " + id + ", userLogin=" + userLogin + ", emailAdress= " + emailAdress + ", name=" + name + ", firstName=" + firstName +
                ", password=" + password + ", registrationDate=" + registrationDate + ", power=" + right;
    }
}
