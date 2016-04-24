package app.com.example.projone.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Commande;

/**
 * Created by Billel Guerfa on 24/04/2016.
 */
public class CommandesAdapter extends BaseAdapter{

    ArrayList<Commande> commandes;
    Context context;
    public CommandesAdapter(Context context,ArrayList<Commande> commandes) {
        this.commandes = commandes;
        this.context = context;
    }

    @Override
    public int getCount() {

        return this.commandes.size();
    }

    @Override
    public Object getItem(int position) {
        return this.commandes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.commande_item, null);
        }
        TextView dateCommande = (TextView) convertView.findViewById(R.id.commande_date);
        TextView etatCommande = (TextView) convertView.findViewById(R.id.commande_etat);
        dateCommande.setText(this.commandes.get(position).getDate());
        etatCommande.setText(this.commandes.get(position).getEtat());

        return convertView;
    }
}
