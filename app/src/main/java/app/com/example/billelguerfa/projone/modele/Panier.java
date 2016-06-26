package app.com.example.billelguerfa.projone.modele;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Panier implements Serializable{
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


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("listPanier", listPanier);

        return result;
    }
}
