package app.com.example.projone.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Categorie;
import app.com.example.billelguerfa.projone.modele.Produit;
import app.com.example.services.ProduitService;

public class ListeProduitsActivity extends AppCompatActivity {
    ProduitService produitService = new ProduitService();
    List<Produit> produitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produits);
        Intent intent = getIntent();
        String catId =  intent.getStringExtra("cat");
        produitService.getProduitsByCat(catId, new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                produitList = produitService.recupProduitsByCat(dataSnapshot);
                FragementListeProduits flvp = new FragementListeProduits();
                Bundle bundle = new Bundle();
                bundle.putSerializable("prodlist", (ArrayList<Produit>) produitList);
                flvp.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, flvp);
                ft.commit();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

}
