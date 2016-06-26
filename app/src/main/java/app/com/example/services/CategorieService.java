package app.com.example.services;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.com.example.billelguerfa.projone.modele.Categorie;

/**
 * Created by Billel Guerfa on 25/06/2016.
 */
public class CategorieService {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public CategorieService() {
    }

    public void getCatgories(ValueEventListener valueEventListener){
        DatabaseReference myRef = database.getReference("categories");
        myRef.addListenerForSingleValueEvent(valueEventListener);
    }
    public Categorie recupCategories(DataSnapshot dataSnapshot,String path,Categorie parent){

        Categorie categorie = dataSnapshot.child(path).getValue(Categorie.class);
        categorie.setSousCategories(new ArrayList<Categorie>());
        categorie.setParent(parent);
        if(dataSnapshot.child(path+"/souscat").exists()){
            for(DataSnapshot data : dataSnapshot.child(path+"/souscat").getChildren()){
                //Categorie sousCat = dataSnapshot.child("categories/" + data.getKey()).getValue(Categorie.class);
                categorie.getSousCategories().add(recupCategories(dataSnapshot,data.getKey(),categorie));
                //recupCategories(sousCat,dataSnapshot,data.getKey());
            }
        }
        return categorie;
    }
}
