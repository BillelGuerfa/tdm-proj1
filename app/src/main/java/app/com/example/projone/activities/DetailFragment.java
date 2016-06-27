package app.com.example.projone.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Base64;
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
import app.com.example.services.PanierService;
import app.com.example.services.ViderPanierService;


public class DetailFragment extends android.app.Fragment {
    Produit produit;
    Panier panier =new Panier();
    EditText quantiteText;
    int i = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        Bundle bundle = getArguments();
        produit= (Produit) bundle.getSerializable("produit");
        //panier=(Panier) bundle.getSerializable("panier");

        List<String> l=new ArrayList<>();
        l.add("32");
        l.add("34");
        l.add("36");
        List<String> L1=new ArrayList<>();
        L1.add("orange");
        L1.add("bleu");
        L1.add("vert");

        produit= (Produit) bundle.getSerializable("produit");
        if (bundle!=null) {
             quantiteText = (EditText) view.findViewById(R.id.editQuantite);
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
            textPrix.setText(""+produit.getPrix()+getResources().getString(R.string.textDeviseAlgerien));
            textContenu.setText(produit.getDescription());
            String base64Image = produit.getImage();
            if(base64Image != null){
                byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);
                coverImage.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                );
            }

            //-----------changer le texte taille par text pointure si c des chaussures-----------------//
           /* if (produit.getCategorie().getNom().equals("Chaussures"))
            {
                textTaille.setText(getResources().getString(R.string.textPointure));
            }*/

            ///-------------------------------------------------------------------------------------/////////

            ////----------------- cacher la layout de taille si le produit est un accessoire------------//
            if (produit.getTailles().isEmpty())
            {
                layoutTaille.setVisibility(View.GONE);
            }


            //-----------------Cachier layout couleur si l'accessoire n'a aucuen couleur ou dite standard//
            if(produit.getCouleurs().isEmpty())
            {
                layoutCouleur.setVisibility(View.GONE);
            }
            //--------------------Adapter spinner Taille--------------------------------------------//////
            ArrayAdapter<String> dataAdapterTaille= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, produit.getTailles());
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
                public void onClick(View v){
                    //panier.ajouterProduit(produit);

                    if (!PanierService.panier.isProductExistant(produit.getNom())) {
                        if (PanierService.panier.getListPanier().isEmpty())
                        {
                            AlarmManager alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                            Intent intent = new Intent(getActivity(),ViderPanierService.class);
                            PendingIntent pendingIntent= PendingIntent.getService(getActivity(),0,intent,0);
                            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() +1000 *30, pendingIntent);
                            Toast.makeText(v.getContext(), "tache planifié", Toast.LENGTH_SHORT).show();

                        }
                        produit.setQuantite(Integer.parseInt(quantiteText.getText().toString()));
                        PanierService.panier.ajouterProduit(produit);
                        Toast.makeText(v.getContext(), "produit ajouté au panier", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(v.getContext(), "produit existant", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

        return view;
    }

}