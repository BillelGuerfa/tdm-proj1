package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Categorie implements Serializable{

   private String nom;
    private int icon;
    private List<Produit> produits;
    Categorie parent;

    public Categorie(Categorie parent,  String nom, int icon) {
        this.parent = parent;
        this.icon = icon;
        this.nom = nom;
    }

    public Categorie getParent() {
        return parent;
    }

    public void setParent(Categorie parent) {
        this.parent = parent;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {

        this.produits = produits;
        for(int i=0;i<produits.size();i++){
            this.produits.get(i).setCategorie(this);
        }
    }

    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public Categorie(Categorie parent, String nom) {
        this.parent = parent;
        this.nom = nom;
        this.sousCategories = new ArrayList<Categorie>();
    }
    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie(Categorie parent, List<Categorie> sousCategories) {
        this.parent = parent;
        this.sousCategories = sousCategories;
    }

    public List<Categorie> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<Categorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    private List<Categorie> sousCategories;
}
