package application.service;

import application.entity.Produit;
import application.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public Produit findProduitByIdentifiantProduit(int id){
        return produitRepository.findProduitByIdentifiant_produit(id);
    }
}
