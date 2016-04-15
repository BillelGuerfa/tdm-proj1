package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Categorie implements Serializable{
    boolean racine;
   private String nom;
    private int icon;
    private List<Produit> produits;

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public Categorie(boolean racine, String nom) {
        this.racine = racine;
        this.nom = nom;
        this.sousCategories = new ArrayList<Categorie>();
    }
    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie(boolean racine, List<Categorie> sousCategories) {
        this.racine = racine;
        this.sousCategories = sousCategories;
    }

    public boolean isRacine() {
        return racine;
    }

    public void setRacine(boolean racine) {
        this.racine = racine;
    }

    public List<Categorie> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<Categorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    private List<Categorie> sousCategories;
}
