package application.service;

import application.entity.Utilisateur;
import application.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur authentificed(String login, String password){
        return utilisateurRepository.findUtilisateurByLoginAndPassword(login, password);
    }

}
