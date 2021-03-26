package application.repository;

import application.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT c FROM Client c WHERE c.identifiant_client = :identifiant_client")
    Client findClientByIdentifiant_client(@Param("identifiant_client") int identifiant_client);
}
