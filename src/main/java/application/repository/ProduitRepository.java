package application.repository;

import application.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

    @Query("SELECT p FROM Produit p WHERE p.identifiant_produit = :identifiant_produit")
    Produit findProduitByIdentifiant_produit(@Param("identifiant_produit") int identifiant_produit);
}
