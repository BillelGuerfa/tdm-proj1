package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.ArrayList;
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
    private float prix;
    private List<String> couleurs= new ArrayList<String>();
    private List<String> tailles = new ArrayList<String>();
    private String tailleChoisie;
    private String couleurChoisie;
    private Categorie categorie;

    public Produit(String nom, String marque, String reference, List<String> taille,List<String> couleurs) {
        this.nom = nom;
        this.marque = marque;
        this.reference = reference;
        this.couleurs=couleurs;
        this.tailles=taille;

    }
    public List<String> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(List<String> couleurs) {
        this.couleurs = couleurs;
    }

    public List<String> getTailles() {
        return tailles;
    }

    public String getTailleChoisie() {
        return tailleChoisie;
    }

    public void setTailleChoisie(String tailleChoisie) {
        this.tailleChoisie = tailleChoisie;
    }

    public String getCouleurChoisie() {
        return couleurChoisie;
    }

    public void setCouleurChoisie(String couleurChoisie) {
        this.couleurChoisie = couleurChoisie;
    }

    public void setTailles(List<String> tailles) {
        this.tailles = tailles;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Produit(int photo, String nom, float prix) {
        this.photo = photo;
        this.nom = nom;
        this.prix = prix;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


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
