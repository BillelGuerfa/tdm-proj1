package app.com.example.projone.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
    private Categorie categorie;





    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
        notifyDataSetChanged();
    }

    public CategoriesAdapter(Context context,Categorie categorie) {
        this.context = context;
        this.categorie = categorie;
    }

    @Override
    public int getCount() {
        return this.categorie.getSousCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return this.categorie.getSousCategories().get(position);
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
        String base64Image = this.categorie.getSousCategories().get(position).getPhoto();
        if(base64Image != null){
            byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);
            icon.setImageBitmap(
                    BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
            );
        }

        //icon.setImageResource(this.categorie.getSousCategories().get(position).getIcon());
        nom.setText(this.categorie.getSousCategories().get(position).getNom());

        return convertView;
    }
}
