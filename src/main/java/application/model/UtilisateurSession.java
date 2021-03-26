package application.model;

import application.entity.Utilisateur;

public class UtilisateurSession {

    private static Utilisateur utilisateur;

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
    }

    public void cleanUserSession() {
        utilisateur = null;
    }

    @Override
    public String toString() {
        return utilisateur.toString();
    }


}
