package app.com.example.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Billel Guerfa on 25/06/2016.
 */
public class CommandeService {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public CommandeService() {
    }
    public void getCommandes(String uid, ValueEventListener valueEventListener){
           DatabaseReference myRef = database.getReference("users/"+uid+"/commandes");
           myRef.addValueEventListener(valueEventListener);
    }
}
