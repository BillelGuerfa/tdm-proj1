package app.com.example.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Billel Guerfa on 25/06/2016.
 */
public class ProduitService {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public ProduitService() {
    }
    public void getProduitsByCat(String catId,ValueEventListener valueEventListener){
        Query query = database.getReference("produits").orderByChild("catId").equalTo(catId);
        query.addValueEventListener(valueEventListener);

    }
}
