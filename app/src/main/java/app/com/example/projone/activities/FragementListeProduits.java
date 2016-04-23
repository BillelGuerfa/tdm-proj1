package app.com.example.projone.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Produit;


public class FragementListeProduits extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // create vue
        View view = inflater.inflate(R.layout.fragment_liste_produits,null);
        //appeler la listeView
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        //crée et remplir la liste
        /////listView.setAdapter(new CustomAdapterForListeProduits(getActivity(), list ));
        ///////////listView.setOnItemClickListener();

        final Bundle bundle = getArguments();

        if(bundle != null)
        {
            List<Produit> listp =  (List<Produit>)bundle.get("prodlist");

            gridView.setAdapter(new CustomAdapterForListeProduits(getActivity(), listp));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //placer le intent ici pour aller vers le detail
                    //TODO:Go to detail activity here
                    Intent intent = new Intent(getActivity(),DetailProduitActivite.class);
                    startActivity(intent);
                    Toast.makeText(view.getContext(), "fonctionne", Toast.LENGTH_SHORT).show();
                }
            });
        }





        // Inflate the layout for this fragment
        return view;
    }
}
