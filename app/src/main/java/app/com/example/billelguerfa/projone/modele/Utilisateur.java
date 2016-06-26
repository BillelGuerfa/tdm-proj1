package app.com.example.billelguerfa.projone.modele;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Billel Guerfa on 04/04/2016.
 */
public class Utilisateur implements Serializable {

    private int photo_id;
    private String nom;
    private String prenom;
    private String age;
    private String user_name;
    private String password;
    private String email;

    private Panier panier;
    private Commande commande;

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Utilisateur() {
    }

    public Utilisateur(int photo_id, String nom, String prenom, String age, String user_name, String password, String email) {
        this.photo_id = photo_id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public int getPhoto_id() {
        return photo_id;
    }



    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAge() {
        return age;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nom", nom);
        result.put("prenom",prenom);
        result.put("photo",photo_id);
        result.put("email",email);
        result.put("uid",user_name);
        result.put("password",password);
        result.put("age",age);
        result.put("panier",panier);

        return result;
    }


}
