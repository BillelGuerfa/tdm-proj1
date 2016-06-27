package app.com.example.services;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.example.billelguerfa.projone.modele.Categorie;
import app.com.example.billelguerfa.projone.modele.Produit;

/**
 * Created by Billel Guerfa on 25/06/2016.
 */
public class ProduitService {
    FirebaseDatabase database = FirebaseDatabase.getInstance(); ;

    public ProduitService() {

    }
    public void getProduitsByCat(String catId,ValueEventListener valueEventListener){
        Query query = database.getReference("produits").orderByChild("catId").equalTo(catId);
        query.addListenerForSingleValueEvent(valueEventListener);

    }
    public ArrayList<Produit> recupProduitsByCat(DataSnapshot dataSnapshot){
        ArrayList<Produit> produits = new ArrayList<Produit>();
        for(DataSnapshot produit: dataSnapshot.getChildren()){
            String ref = produit.getKey();
            Produit produit1 = produit.getValue(Produit.class);
            produit1.setReference(ref);
            //produit1.setCategorie(categorie);
            produits.add(produit1);
        }
        return produits;
    }
    public void majProduitQuantite(String idProduit, final int quantite){
        DatabaseReference myRef = database.getReference("produits").child(idProduit);
        myRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Produit p = mutableData.getValue(Produit.class);
                if(p == null){
                    return Transaction.success(mutableData);
                }
                p.setQuantite(p.getQuantite()- quantite);
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }
}
