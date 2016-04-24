/*package app.com.example.billelguerfa.projone.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billel Guerfa on 03/04/2016.
 */
/*
public class Produit implements Serializable{
    private int photo;
    private String nom;
    private String couleur;
    private String marque;
    private String reference;
    public List<String> tailles = new ArrayList<String>();

    public List<Categorie> getCategories() {
        return categories;
    }

    public Produit(String nom, String couleur, String marque, String reference, List<String> taille, int longeur, List<Categorie> categories) {
        this.nom = nom;
        this.couleur = couleur;
        this.marque = marque;
        this.reference = reference;
        this.tailles = taille;
        this.longeur = longeur;
        this.categories = categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
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

    public List<String> getTaille() {
        return tailles;
    }

    /*public void setTaille(String taille) {
        this.taille = taille;
    }*/
/*
    public int getLongeur() {
        return longeur;
    }

    public void setLongeur(int longeur) {
        this.longeur = longeur;
    }

    private int longeur;

}
*/

package app.com.example.billelguerfa.projone.modele;
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Billel Guerfa on 03/04/2016.
 */
public class Produit implements Serializable {
    private int photo;
    private String nom;
    private List<String> couleurs= new ArrayList<String>();
    private String marque;
    private String reference;
    private String tailleChoisie;
    private List<String> tailles = new ArrayList<String>();
    private String couleurChoisie;
    private Categorie categorie;
    private int note;
    private int prix;
    private String description;





    public Produit(String nom, String marque, String reference, List<String> taille,List<String> couleurs,Categorie categorie,int photo,int prix,String description) {
        this.nom = nom;
        this.marque = marque;
        this.reference = reference;
        this.categorie = categorie;
        this.couleurs=couleurs;
        this.tailles=taille;
        this.photo=photo;
        this.prix=prix;
        this.description = description;

    }

    public Produit(String nom, String marque, String reference, List<String> taille,List<String> couleurs,int photo,int prix,String description) {
        this.nom = nom;
        this.marque = marque;
        this.reference = reference;
        this.couleurs=couleurs;
        this.tailles=taille;
        this.photo=photo;
        this.prix=prix;
        this.description = description;

    }


    public Produit(String nom, String marque, String reference, List<String> taille,Categorie categorie,int photo,int prix,String description) {
        this.nom = nom;
        this.marque = marque;
        this.reference = reference;
        this.categorie = categorie;
        this.tailles=taille;
        this.photo=photo;
        this.prix=prix;
        this.description = description;

    }

    public Produit(String nom, String marque, String reference, List<String> taille,int photo,int prix,String description) {
        this.nom = nom;
        this.marque = marque;
        this.reference = reference;
        this.categorie = categorie;
        this.tailles=taille;
        this.photo=photo;
        this.prix=prix;
        this.description = description;

    }

    public Produit(String nom, String marque, String reference,Categorie categorie,int photo,int prix,String description) {
        this.nom = nom;
        this.marque = marque;
        this.reference = reference;
        this.categorie = categorie;
        this.couleurs=couleurs;
        this.photo=photo;
        this.prix=prix;
        this.description = description;

    }

    public Produit(String nom, String marque, String reference, List<String> taille,List<String> couleurs) {
        this.nom = nom;
        this.marque = marque;
        this.reference = reference;
        this.categorie = categorie;
        this.couleurs=couleurs;
        this.tailles=taille;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleurChoisie() {
        return couleurChoisie;
    }

    public void setCouleurChoisie(String couleurChoisie) {
        this.couleurChoisie = couleurChoisie;
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

    public List<String> getTaille() {
        return tailles;
    }

    /*public void setTaille(String taille) {
        this.taille = taille;
    }*/


    public List<String> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(List<String> couleurs) {
        this.couleurs = couleurs;
    }
    public String getTailleChoisie() {
        return tailleChoisie;
    }

    public void setTailleChoisie(String tailleChoisie) {
        this.tailleChoisie = tailleChoisie;
    }
    public Categorie getCategorie() {
        return categorie;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    // private List<Categorie> categories;
}
