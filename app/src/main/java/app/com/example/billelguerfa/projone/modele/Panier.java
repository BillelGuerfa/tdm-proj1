package app.com.example.billelguerfa.projone.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Panier {
    private List<Produit> listPanier;

    public Panier() {

        this.listPanier = new ArrayList<>();

    }

    public List<Produit> getListPanier() {
        return listPanier;
    }

    public void setListPanier(List<Produit> listPanier) {
        this.listPanier = listPanier;
    }

    public void ajouterProduit(Produit produit)
    {
        listPanier.add(produit);
    }
}
