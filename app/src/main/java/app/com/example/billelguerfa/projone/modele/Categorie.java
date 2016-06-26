package app.com.example.billelguerfa.projone.modele;

import com.google.firebase.database.Exclude;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Categorie implements Serializable{

    private String nom;
    private int icon;
    private String id;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    private List<Produit> produits;
    Categorie parent;
    private List<Categorie> sousCategories;
    HashMap<String, Boolean > mapping = new HashMap<>();

    public Categorie()
    {

    }

    /*public Categorie(Categorie parent,  String nom, int icon) {
        this.parent = parent;
        this.icon = icon;
        this.nom = nom;
    }*/

    public Categorie(Categorie parent,  String nom, int icon,String id) {
        this.parent = parent;
        this.icon = icon;
        this.nom = nom;
        this.id=id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public Categorie(Categorie parent, String nom,String id) {
        this.parent = parent;
        this.nom = nom;
        this.id=id;
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



    @Exclude
    public Map<String, Object> toMap(String encodage, HashMap<String, Boolean > mapping) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nom", nom);
        result.put("id",id);
        result.put("souscat",mapping);
        if(parent != null){
            result.put("parentId",parent.getId());
        }
        //result.put("produits", produits);
        //result.put("sousCategories", new Gson().toJsonTree(sousCategories) );
          result.put("photo", encodage);
        //result.put("parent", parent);


        return result;
    }
    @Exclude
    public Map<String, Object> toMap(HashMap<String, Boolean > mapping) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("nom", nom);
        result.put("id",id);
        if(parent != null){
            result.put("parentId",parent.getId());
        }
        result.put("souscat",mapping);
        //result.put("produits", produits);
        //result.put("sousCategories", new Gson().toJsonTree(sousCategories) );
        //result.put("photo", encodage);
        //result.put("parent", parent);


        return result;
    }
}
