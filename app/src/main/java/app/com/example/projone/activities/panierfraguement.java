package app.com.example.projone.activities;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Panier;
import app.com.example.billelguerfa.projone.modele.Produit;
import app.com.example.services.PanierService;
import app.com.example.services.ViderPanierService;

public class panierfraguement extends Fragment {

    TextView tv;
    View view;
    PanierService panierService = new PanierService();
    public panierfraguement() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // create vue
          view = inflater.inflate(R.layout.activity_panierfraguement,null);
        //appeler la listeView
        GridView gridView = (GridView) view.findViewById(R.id.gridViewPanier);
        //final TextView tv = (TextView) view.findViewById(R.id.total);
        //crée et remplir la liste
        /////listView.setAdapter(new CustomAdapterForListeProduits(getActivity(), list ));
        ///////////listView.setOnItemClickListener();

        final Bundle bundle = getArguments();

        if(bundle != null)
        {
            List<Produit> listp =  (List<Produit>)bundle.get("prodlist");



            gridView.setAdapter(new CustomAdapterForCart(getActivity(), PanierService.getPanier().getListPanier()));

            tv = (TextView) view.findViewById(R.id.total);
            tv.setText("Total : " + PanierService.panier.getTotalPrice());

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //placer le intent ici pour aller vers le detail
                    Toast.makeText(view.getContext(), "fonctionne panier", Toast.LENGTH_SHORT).show();

                }
            });



        }


        //View v = inflater.inflate(R.layout.activity_panier_detail,null);

        Button validerPanier = (Button) view.findViewById(R.id.validerPanier);
        validerPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //panier.ajouterProduit(produit);


                AlarmManager alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getActivity(),ViderPanierService.class);
                PendingIntent pendingIntent= PendingIntent.getService(getActivity(),0,intent,0);
                alarmManager.cancel(pendingIntent);
                Toast.makeText(v.getContext(), "tache planifié annulé", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getActivity(), Panier_activity.class);
                List<Produit> produits = new ArrayList<>();
                panierService.majStock( (ArrayList<Produit>) PanierService.panier.getListPanier());
                PanierService.panier.setListPanier(produits);
                startActivity(intent1);


            }
        });




        // Inflate the layout for this fragment
        return view;
    }

}
