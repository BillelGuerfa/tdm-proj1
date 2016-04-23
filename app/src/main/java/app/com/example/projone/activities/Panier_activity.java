package app.com.example.projone.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Produit;

public class Panier_activity extends AppCompatActivity {

    private List<Produit> produitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_activity);

        Intent intent = getIntent();
        produitList = (List<Produit>) intent.getSerializableExtra("listp");

        panierfraguement pfp = new panierfraguement();

        Bundle bundle = new Bundle();
        bundle.putSerializable("prodlist",(ArrayList<Produit>) intent.getSerializableExtra("listp"));
        pfp.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutPanier, pfp);
        ft.commit();

    }
    /*public void supprimerDuPanier(View v)
    {

        Toast.makeText(v.getContext(), "panierSuppButton", Toast.LENGTH_SHORT).show();
    }*/

}
