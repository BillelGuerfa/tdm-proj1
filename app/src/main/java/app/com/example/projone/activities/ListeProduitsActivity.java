package app.com.example.projone.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Produit;

public class ListeProduitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produits);

        Intent intent = getIntent();
        List<Produit> produitList = (List<Produit>) intent.getSerializableExtra("listp");

        FragementListeProduits flvp = new FragementListeProduits();

        Bundle bundle = new Bundle();
        bundle.putSerializable("prodlist",(ArrayList<Produit>) intent.getSerializableExtra("listp"));
        flvp.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, flvp);
        ft.commit();



    }
}
