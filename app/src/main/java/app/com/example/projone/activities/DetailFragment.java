package app.com.example.projone.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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

import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Panier;
import app.com.example.billelguerfa.projone.modele.Produit;


public class DetailFragment extends android.app.Fragment {
    Produit produit;
    Produit produit1;
    Produit produit2;
    Produit produit3;
    Produit produit4;
    Produit produit5;
    Panier panier;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        Bundle bundle = getArguments();
       // produit= (Produit) bundle.getSerializable("produit");
        //panier=(Panier) bundle.getSerializable("panier");

        List<String> l=new ArrayList<>();
        l.add("32");
        l.add("34");
        l.add("36");
        l.add("38");
        l.add("40");
        l.add("42");

        List<String> l2=new ArrayList<>();
        l2.add("W32-H34");
        l2.add("W34-H34");
        l2.add("W36-H38");
        l2.add("W38-H36");
        l2.add("W40-H40");
        l2.add("W42-H42");

        List<String> L1=new ArrayList<>();
        /*L1.add("orange");
        L1.add("bleu");
        L1.add("vert");*/

        produit= new Produit("Levis - Jean - Homme - 511 Slim Fit - Green Splash - Brut","Levis","2132156",l2,L1,R.drawable.ic_levis_jean_homme_511_slim,2500,"Jean pour hommesLevi's 511 Slim Fit Couleur Brut bleu vert délavé (Green Splash)Leger délavage patiné aux jambesCoupe droite ajustée (Slim fit), resserré en bas (Slighty tapered leg)Taille basse (Sits below waist)Braguette zippée éclair﻿5 poches dont une petite gousset apparente devant à droite Composition : 100% Coton");
        produit2= new Produit("Diesel Industry Krooley","Diesel","213656",l2,R.drawable.ic_diesel_jean,2500,"Jean pour hommesLevi's 511 Slim Fit Couleur Brut bleu vert délavé (Green Splash)Leger délavage patiné aux jambesCoupe droite ajustée (Slim fit), resserré en bas (Slighty tapered leg)Taille basse (Sits below waist)Braguette zippée éclair﻿5 poches dont une petite gousset apparente devant à droite Composition : 100% Coton");





        if (bundle!=null) {
            ImageView coverImage = (ImageView) view.findViewById(R.id.coverImage);
            TextView textNom= (TextView) view.findViewById(R.id.textNom);
            TextView textPrix=(TextView) view.findViewById(R.id.textPrix);
            TextView textTaille=(TextView) view.findViewById(R.id.textTaille);
            TextView textQuantite=(TextView) view.findViewById(R.id.textQuantite);
            TextView textDescription=(TextView) view.findViewById(R.id.textDescription);
            TextView textContenu=(TextView) view.findViewById(R.id.textContenu);
            Spinner spinTaille=(Spinner) view.findViewById(R.id.spinTaille);
            Spinner spinCouleur=(Spinner) view.findViewById(R.id.spinCouleur);
            Button ajouterPanier=(Button) view.findViewById(R.id.bouttonAjouter);
            LinearLayout layoutTaille = (LinearLayout) view.findViewById(R.id.layoutTaille);
            LinearLayout layoutCouleur=(LinearLayout) view.findViewById(R.id.layoutCouleur);
            TextView textSuggestion=(TextView) view.findViewById(R.id.textSuggestion) ;
            /*ImageView suggestion1=(ImageView) view.findViewById(R.id.suggestion1);
            ImageView suggestion2=(ImageView) view.findViewById(R.id.suggestion1);
            ImageView suggestion3=(ImageView) view.findViewById(R.id.suggestion1);*/

            textSuggestion.setText(getResources().getString(R.string.textSuggestion));
            textNom.setText(produit.getNom());
            textPrix.setText("2500"+getResources().getString(R.string.textDeviseAlgerien));
           textContenu.setText(produit.getDescription());
            coverImage.setImageResource(produit.getPhoto());

            //-----------changer le texte taille par text pointure si c des chaussures-----------------//
/*            if (produit.getCategorie().getNom().equals("Chaussures"))
            {
                textTaille.setText(getResources().getString(R.string.textPointure));
            }
*/
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