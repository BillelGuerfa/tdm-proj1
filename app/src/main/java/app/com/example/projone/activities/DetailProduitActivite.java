package app.com.example.projone.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import app.com.example.billelguerfa.projone.R;

public class DetailProduitActivite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produit_activite);
        DetailFragment detailFragment = new DetailFragment();
        Toast.makeText(DetailProduitActivite.this, "sqkldqskld", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        //bundle.putSerializable("produit",intent.getSerializableExtra("produit"));
        detailFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, detailFragment);
        ft.commit();
    }
}
