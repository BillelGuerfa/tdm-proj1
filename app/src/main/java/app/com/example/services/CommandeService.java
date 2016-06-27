package app.com.example.services;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.com.example.billelguerfa.projone.modele.Commande;

/**
 * Created by Billel Guerfa on 25/06/2016.
 */
public class CommandeService {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public CommandeService() {
    }
    public void getCommandes( ValueEventListener valueEventListener){
           DatabaseReference myRef = database.getReference("commandes/"+UserService.firebaseUser.getUid());
           myRef.addListenerForSingleValueEvent(valueEventListener);
    }
    public void sendCommande(Commande commande){
          DatabaseReference myRef = database.getReference("commandes/"+UserService.firebaseUser.getUid());
          String key = myRef.push().getKey();
          myRef.child(key).setValue(commande.toMap());
    }
}
