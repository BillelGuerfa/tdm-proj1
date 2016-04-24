package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Commande implements Serializable{
    private List<Produit> produits;
    private String date;
    private String etat;

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

    public Commande() {
        this.produits = new ArrayList<Produit>();
    }

    public Commande(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Produit> getProduits() {

        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
