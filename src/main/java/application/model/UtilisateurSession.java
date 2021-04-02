package application.model;

import application.entity.Utilisateur;
import javafx.scene.image.Image;

public class UtilisateurSession {

    private static Utilisateur utilisateur;
    private static Image genderImage;

    private UtilisateurSession() {
    }

    private static class UtilisateurSessionHolder
    {
        private final static UtilisateurSession instance = new UtilisateurSession();
    }

    public static UtilisateurSession getInstance()
    {
        return UtilisateurSession.UtilisateurSessionHolder.instance;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur target){
        utilisateur = target;
        if(utilisateur.getSexe_utilisateur().equals("h")){
            genderImage = new Image("/images/man.png");
        }
        else{
            genderImage = new Image("/images/woman.png");
        }
    }

    public Image getGenderImage() {
        return genderImage;
    }

    public void cleanUserSession() {
        utilisateur = null;
        genderImage = null;
    }

    @Override
    public String toString() {
        return utilisateur.toString();
    }


}
