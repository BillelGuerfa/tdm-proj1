package app.com.example.projone.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Categorie;

/**
 * Created by Billel Guerfa on 15/04/2016.
 */
public class CategoriesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Categorie> categories;

    public CategoriesAdapter(Context context, ArrayList<Categorie> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return this.categories.size();
    }

    @Override
    public Object getItem(int position) {
        return this.categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.categorie_item, null);
        }
        ImageView icon = (ImageView) convertView.findViewById(R.id.sous_cat_image);
        TextView nom = (TextView) convertView.findViewById(R.id.sous_cat_name);
        icon.setImageResource(this.categories.get(position).getIcon());
        nom.setText(this.categories.get(position).getNom());

        return convertView;
    }
}
