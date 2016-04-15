package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Billel Guerfa on 03/04/2016.
 */
public class Produit implements Serializable{
    private int photo;
    private String nom;
    private String couleur;
    private String marque;
    private String reference;



    public Produit(String nom, String couleur, String marque, String reference, String taille, int longeur) {
        this.nom = nom;
        this.couleur = couleur;
        this.marque = marque;
        this.reference = reference;
        this.taille = taille;
        this.longeur = longeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getLongeur() {
        return longeur;
    }

    public void setLongeur(int longeur) {
        this.longeur = longeur;
    }

    private String taille;
    private int longeur;

}
