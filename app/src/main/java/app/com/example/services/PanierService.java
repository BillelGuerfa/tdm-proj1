package app.com.example.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import app.com.example.billelguerfa.projone.modele.Panier;
import app.com.example.billelguerfa.projone.modele.Produit;

/**
 * Created by Dell on 25/06/2016.
 */
public class PanierService {


    private DatabaseReference mDatabase;
    private ProduitService produitService = new ProduitService();
    public static Panier panier = new Panier();

    public PanierService() {

    }
    public static Panier getPanier(){
        return panier;
    }

    public void majStock(ArrayList<Produit> produits){
        for (Produit produit: produits){
            produitService.majProduitQuantite(produit.getReference(),produit.getQuantite());
        }
    }




}
