package app.com.example.projone.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Panier;
import app.com.example.billelguerfa.projone.modele.Produit;


public class DetailFragment extends android.app.Fragment {
    Produit produit;
    Panier panier;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        Bundle bundle = getArguments();
        produit= (Produit) bundle.getSerializable("produit");
        panier=(Panier) bundle.getSerializable("panier");


        if (bundle!=null) {
            ImageView coverImage = (ImageView) view.findViewById(R.id.coverImage);
            TextView textNom= (TextView) view.findViewById(R.id.textNom);
            TextView textPrix=(TextView) view.findViewById(R.id.textPrix);
            TextView textTaille=(TextView) view.findViewById(R.id.textTaille);
            TextView textQuantite=(TextView) view.findViewById(R.id.textQuantite);
            TextView textDescription=(TextView) view.findViewById(R.id.textDescription);
            TextView textContenu=(TextView) view.findViewById(R.id.textContenu);
            Spinner spinTaille=(Spinner) view.findViewById(R.id.spinTaille);
            EditText editQuantite=(EditText) view.findViewById(R.id.editQuantite);
            Spinner spinQuantite=(Spinner) view.findViewById(R.id.spinQuantite);
            Spinner spinCouleur=(Spinner) view.findViewById(R.id.spinCouleur);
            Button ajouterPanier=(Button) view.findViewById(R.id.bouttonAjouter);
            LinearLayout layoutTaille = (LinearLayout) view.findViewById(R.id.layoutTaille);
            LinearLayout layoutCouleur=(LinearLayout) view.findViewById(R.id.layoutCouleur);

            textNom.setText(produit.getNom());

            //-----------changer le texte taille par text pointure si c des chaussures-----------------//
            if (produit.getCategorie().getNom().equals("Chaussures"))
            {
                textTaille.setText(getResources().getString(R.string.textPointure));
            }

            ///-------------------------------------------------------------------------------------/////////

            ////----------------- cacher la layout de taille si le produit est un accessoire------------//
            if (produit.getTaille().isEmpty())
            {
                layoutTaille.setVisibility(View.GONE);
            }


            //-----------------Cachier layout couleur si l'accessoire n'a aucuen couleur ou dite standard//
            if(produit.getCouleurs().isEmpty())
            {
                layoutCouleur.setVisibility(View.GONE);
            }
            //--------------------Adapter spinner Taille--------------------------------------------//////
            ArrayAdapter<String> dataAdapterTaille= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, produit.getTaille());
            spinTaille.setAdapter(dataAdapterTaille);

            spinTaille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String valeur;
                    valeur = parent.getItemAtPosition(position).toString();
                    produit.setTailleChoisie(valeur);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });


            //----------------Adapter spinner Couleur ----------------------------------------------/////
            ArrayAdapter<String> dataAdapterCouleur= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, produit.getCouleurs());
            spinCouleur.setAdapter(dataAdapterCouleur);

            spinCouleur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String valeur;
                    valeur = parent.getItemAtPosition(position).toString();
                    produit.setCouleurChoisie(valeur);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });

            //------------------------------ClickLIster du bouton ajouter au panier ------------------------//

            ajouterPanier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    panier.ajouterProduit(produit);
                }
            });


        }

        return view;
    }

}