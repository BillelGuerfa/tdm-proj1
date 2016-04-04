package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Commande implements Serializable{
    private List<Produit> produits;

    public Commande() {
        this.produits = new ArrayList<Produit>();
    }

    public List<Produit> getProduits() {

        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
