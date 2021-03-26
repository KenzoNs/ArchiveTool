package application.repository;

import application.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    @Query("SELECT u FROM Utilisateur u WHERE u.identifiant_utilisateur = :identifiant_utilisateur")
    Utilisateur findUserByUserLogin(@Param("identifiant_utilisateur") String login);

    @Query("SELECT u FROM Utilisateur u WHERE u.identifiant_utilisateur = :identifiant_utilisateur AND u.mot_de_passe_utilisateur = :mot_de_passe_utilisateur")
    Utilisateur findUserByUserLoginAndPassword(@Param("identifiant_utilisateur") String login, @Param("mot_de_passe_utilisateur") String password);

}