package app.com.example.projone.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Commande;
import app.com.example.projone.adapters.CommandesAdapter;
import app.com.example.services.CommandeService;

public class CommandesActivity extends AppCompatActivity {
    ArrayList<Commande> commandes = new ArrayList<Commande>();
    Object that = this;
    CommandeService commandeService = new CommandeService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);
        //createCommandes();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot com: dataSnapshot.getChildren()){
                    commandes.add(com.getValue(Commande.class));
                }
                GridView listeCommandes = (GridView) findViewById(R.id.liste_commandes);
                listeCommandes.setAdapter(new CommandesAdapter( (Context) that,commandes));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        commandeService.getCommandes(valueEventListener);


    }

   /* private void createCommandes(){
        this.commandes = new ArrayList<Commande>();
        this.commandes.add(new Commande("24/04/2016","En cours"));
        this.commandes.add(new Commande("24/04/2016","En cours"));
        this.commandes.add(new Commande("24/04/2016","En cours"));
    }*/
}
