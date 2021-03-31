package application.service;

import application.entity.Produit;
import application.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public Produit findProduitByIdentifiantProduit(int id){
        return produitRepository.findProduitByIdentifiant_produit(id);
    }

    public void addOrUpdateProduct(Produit p){
        this.produitRepository.save(p);
    }

    public List<Produit> getAllProducts(){
        return this.produitRepository.findAll();
    }
}
