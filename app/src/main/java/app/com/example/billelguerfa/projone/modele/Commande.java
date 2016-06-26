package app.com.example.billelguerfa.projone.modele;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Commande implements Serializable{
    private List<Produit> produits;
    private String date;
    private String etat;

    public Commande()
    {

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Commande(String date, String etat) {
        this.date = date;
        this.etat = etat;
    }



    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    /*public Commande() {
        this.produits = new ArrayList<Produit>();
    }*/

    public Commande(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Produit> getProduits() {

        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Exclude
    public Map<String, Object> toMap(Utilisateur utilisateur) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("uid",utilisateur.getUser_name());
        result.put("produits", produits);
        result.put("etat", etat);



        return result;
    }
}
