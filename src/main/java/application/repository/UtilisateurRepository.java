package application.repository;

import application.entity.User;
import application.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM Utilisateur u WHERE u.identifiant_utilisateur = :identifiant_utilisateur")
    Utilisateur findByIdentifiant_utilisateur(@Param("identifiant_utilisateur") String identifiant_utilisateur);

    @Query("SELECT u FROM Utilisateur u WHERE u.identifiant_utilisateur = :identifiant_utilisateur AND u.mot_de_passe_utilisateur = :mot_de_passe_utilisateur")
    Utilisateur findUtilisateurByLoginAndPassword(@Param("identifiant_utilisateur") String identifiant_utilisateur, @Param("mot_de_passe_utilisateur") String mot_de_passe_utilisateur);

}