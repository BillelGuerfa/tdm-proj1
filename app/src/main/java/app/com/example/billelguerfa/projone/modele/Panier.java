package app.com.example.billelguerfa.projone.modele;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public void ajouterProduit(Produit produit) {

        listPanier.add(produit);
    }

    public boolean isProductExistant(String ref)
    {
        int i = 0;
        boolean trouve = false;
        if(this.listPanier.size() >0)
        {
            trouve =false;
            while (i < this.listPanier.size() && trouve == false) {
                if (this.listPanier.get(i).getNom().equals(ref)) {
                    trouve = true;
                }
                i++;
            }
        }
        else
        {
            trouve =false;
        }
        return trouve;
    }



    public float getTotalPrice()
    {

        float total = 0;
        int i = 0;
        while (i < this.listPanier.size() ) {
            total +=  (this.listPanier.get(i).getPrix() * this.listPanier.get(i).getQuantite()) ;
            i++;
        }
        return total;
    }
}
