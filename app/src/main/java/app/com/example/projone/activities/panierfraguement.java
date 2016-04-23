package app.com.example.projone.activities;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Produit;

public class panierfraguement extends Fragment {


    public panierfraguement() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // create vue
        View view = inflater.inflate(R.layout.activity_panierfraguement,null);
        //appeler la listeView
        ListView listView = (ListView) view.findViewById(R.id.listViewPanier);
        //crée et remplir la liste
        /////listView.setAdapter(new CustomAdapterForListeProduits(getActivity(), list ));
        ///////////listView.setOnItemClickListener();

        final Bundle bundle = getArguments();

        if(bundle != null)
        {
            List<Produit> listp =  (List<Produit>)bundle.get("prodlist");



            listView.setAdapter(new CustomAdapterForCart(getActivity(), listp));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //placer le intent ici pour aller vers le detail
                    Toast.makeText(view.getContext(), "fonctionne panier", Toast.LENGTH_SHORT).show();
                }
            });
        }


        //View v = inflater.inflate(R.layout.activity_panier_detail,null);






        // Inflate the layout for this fragment
        return view;
    }

}
