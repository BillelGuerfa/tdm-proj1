package app.com.example.projone.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Produit;
import app.com.example.services.PanierService;

public class CustomAdapterForCart extends BaseAdapter {

    Context context;
    List<Produit> produitList;
    TextView textViewpanier4 = null;
    NumberPicker np;
    TextView tv1, tv2,texttotal;

    //TextView textViewpanierqtte = null;

    NumberPicker numberPicker = null;

    public CustomAdapterForCart() {
    }

    public CustomAdapterForCart(Context context, List<Produit> produitList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        texttotal = (TextView) ((Activity) context).findViewById(R.id.total);
        convertView = parent.inflate(context, R.layout.activity_custom_adapter_for_cart,null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewpanier);
        TextView textViewpanier2 = (TextView) convertView.findViewById(R.id.textViewpanierMarque);
        textViewpanier4 = (TextView) convertView.findViewById(R.id.textViewpanierPrix);


        tv1 = (TextView) convertView.findViewById(R.id.textViewQteee);








        //textViewpanierqtte = (TextView) convertView.findViewById(R.id.textView6);


        /*numberPicker = (NumberPicker) convertView.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);*/


        imageView.setImageResource(produitList.get(position).getPhoto());
        textViewpanier2.setText("Marque: " + produitList.get(position).getMarque());
        textViewpanier4.setText("Prix U: " + (produitList.get(position).getPrix()));
        tv1.setText("Quantite : " + (produitList.get(position).getQuantite()));
        //textViewpanierqtte.setText("Quantite= "  );


        ImageButton button = (ImageButton) convertView.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Produit retiré du panier.", Toast.LENGTH_SHORT).show();
                produitList.remove(position);
                String tot = "Total : " + PanierService.panier.getTotalPrice();
                texttotal.setText(tot);
                notifyDataSetChanged();

            }
        });

        Button augQte=(Button) convertView.findViewById(R.id.button);
        Button subQte=(Button) convertView.findViewById(R.id.button2);
        tv2 = (TextView) convertView.findViewById(R.id.total);
        augQte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                produitList.get(position).setQuantite((produitList.get(position).getQuantite()) + 1);
                String tot = "Total : " + PanierService.panier.getTotalPrice();
                texttotal.setText(tot);
                notifyDataSetChanged();
            }
        });

        subQte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( produitList.get(position).getQuantite() > 1) {
                    produitList.get(position).setQuantite((produitList.get(position).getQuantite()) - 1);
                    String tot = "Total : " + PanierService.panier.getTotalPrice();
                    texttotal.setText(tot);
                    notifyDataSetChanged();
                }
            }
        });
        /*
        Button button1 = (Button) convertView.findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nbPickerDialog();
            }
        });
        */






        return convertView;
    }

    private void nbPickerDialog()
    {
        NumberPicker mynbrpckr = new NumberPicker(context);
        mynbrpckr.setMinValue(1);
        mynbrpckr.setMaxValue(10);
        NumberPicker.OnValueChangeListener myValueChangeListener = new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                //textViewpanierqtte.setText("Quantite= " +newVal);
                notifyDataSetChanged();

            }
        };

        mynbrpckr.setOnValueChangedListener(myValueChangeListener);

        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(mynbrpckr);
        builder.setTitle("Choisisez la quantite");
        builder.setPositiveButton("confirmer", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                notifyDataSetChanged();

            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                notifyDataSetChanged();

            }

        });
        builder.show();
    }



}
