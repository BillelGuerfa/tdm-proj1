package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Categorie implements Serializable{
    boolean racine;

    public String getNom() {
        return nom;
    }

    String nom;

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
