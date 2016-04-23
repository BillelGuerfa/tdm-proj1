package app.com.example.projone.activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Produit;

/**
 * Created by Dell on 04/04/2016.
 */
public class CustomAdapterForListeProduits extends BaseAdapter {

    Context context;
    List<Produit> produitList;

    public CustomAdapterForListeProduits(Context context, List<Produit> produitList) {
        this.context = context;
        this.produitList = produitList;
    }




    @Override
    public int getCount() {
        return produitList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = parent.inflate(context, R.layout.activity_liste_produit,null);
        //relative layout 1
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
        TextView textView4 = (TextView) convertView.findViewById(R.id.textView4);

        //relative layout 2
        // ImageView imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
        //TextView textView3 = (TextView) convertView.findViewById(R.id.textView3);
        //TextView textView4 = (TextView) convertView.findViewById(R.id.textView4);



        //imageView2.setImageResource(produitList.get(position).getPhoto());
        //textView3.setText(produitList.get(position).getNom());
        //textView4.setText("" + (int) produitList.get(position).getPrix());

        /*Resources resources = context.getResources();
        TextView textView3 = (TextView) convertView.findViewById(R.id.textView3);
        TextView textView4 = (TextView) convertView.findViewById(R.id.textView4);
        textView2.setText(resources.getString(R.string.Marque  ));
        textView4.setText(resources.getString(R.string.Prix));*/

        //remplir
        imageView.setImageResource(produitList.get(position).getPhoto());
        textView2.setText("Marque: " + produitList.get(position).getMarque());
        textView4.setText("Prix: "+ (int) produitList.get(position).getPrix());







        return convertView;
    }
}
