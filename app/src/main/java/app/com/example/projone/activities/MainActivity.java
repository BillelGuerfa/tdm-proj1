package app.com.example.projone.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Categorie;
import app.com.example.billelguerfa.projone.modele.Produit;
import app.com.example.projone.adapters.CategoriesAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Categorie categorie;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.createCategories(); //instancie les catégories

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this.categorie);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_cart) {
            //TODO: Intent vers Panier ici.
            List<Produit> produitList = new ArrayList<>();
            produitList.add(new Produit(R.drawable.ic_italie_paul_et_shark_homme_polos_meilleur_prix_1016,"polo",100));

            produitList.add(new Produit(R.drawable.ic_2013_fashion_mens_stripe_paul_shark_short_polo_shirt_red_grey_black_lrg,"Ralph",200));

            produitList.add(new Produit(R.drawable.ic__32,"Tommy",300));

            produitList.add(new Produit(R.drawable.ic_corec1p11104sfi010_1x,"triicoo",400));

            produitList.add(new Produit(R.drawable.ic_fashion_paul_and_shark_long_sleeved_shirts_best_price_453, "geek", 500));

            produitList.add(new Produit(R.drawable.ic_img_thing, "paul&shark", 500));

            produitList.add(new Produit(R.drawable.ic_nyp0902224_sale_stripes_paul_shark_homme_short_polo_shirt_yellow_boutique_vente, "mongo", 500));

            produitList.add(new Produit(R.drawable.ic__32,"celio",300));

            Intent intent = new Intent(this ,Panier_activity.class);
            intent.putExtra("listp", (ArrayList<Produit>) produitList);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            GridView gridView = (GridView) findViewById(R.id.liste_categories);
            CategoriesAdapter adapter = (CategoriesAdapter)gridView.getAdapter();
            if(adapter.getCategorie() != null && adapter.getCategorie().getParent() != null){
                adapter.setCategorie(adapter.getCategorie().getParent());
                adapter.notifyDataSetChanged();
            }else {
                super.onBackPressed();
            }

        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if(id == R.id.notifications) {
            if(item.getTitle().equals(getResources().getString(R.string.notifications_active))){
                item.setTitle(getResources().getString(R.string.notifications_off));
                item.setIcon(R.drawable.ic_notifications_off_black_24dp);
            }
            else {
                item.setTitle(getResources().getString(R.string.notifications_active));
                item.setIcon(R.drawable.ic_notifications_active_black_24dp);
            }
        }


        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_CATEGORIE = "categorie";
        private GridView listeCategories;
        Categorie categorie;
        CategoriesAdapter adapter;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(Categorie categorie) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putSerializable(ARG_CATEGORIE, categorie);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            categorie = (Categorie) getArguments().getSerializable(ARG_CATEGORIE);
            listeCategories = (GridView) rootView.findViewById(R.id.liste_categories);
            adapter = new CategoriesAdapter(getActivity(),categorie);
            listeCategories.setAdapter(adapter);
            listeCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (adapter.getCategorie().getSousCategories().get(position).getSousCategories().isEmpty()) {
                         //TODO: Create intent here with the product list activity
                        //TODO: Send to products activity
                        //TODO:Copy product list here.
                        List<Produit> produitList = new ArrayList<>();
                        produitList.add(new Produit(R.drawable.ic_italie_paul_et_shark_homme_polos_meilleur_prix_1016,"polo",100));

                        produitList.add(new Produit(R.drawable.ic_2013_fashion_mens_stripe_paul_shark_short_polo_shirt_red_grey_black_lrg,"Ralph",200));

                        produitList.add(new Produit(R.drawable.ic__32,"Tommy",300));

                        produitList.add(new Produit(R.drawable.ic_corec1p11104sfi010_1x,"triicoo",400));

                        produitList.add(new Produit(R.drawable.ic_fashion_paul_and_shark_long_sleeved_shirts_best_price_453, "geek", 500));

                        produitList.add(new Produit(R.drawable.ic_img_thing, "paul&shark", 500));

                        produitList.add(new Produit(R.drawable.ic_nyp0902224_sale_stripes_paul_shark_homme_short_polo_shirt_yellow_boutique_vente, "mongo", 500));

                        produitList.add(new Produit(R.drawable.ic__32,"celio",300));

                        Intent intent = new Intent(getActivity() ,ListeProduitsActivity.class);
                        intent.putExtra("listp", (ArrayList<Produit>) produitList);

                        startActivity(intent);
                    } else {
                        adapter.setCategorie(adapter.getCategorie().getSousCategories().get(position));
                    }
                }
            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private Categorie categorie;
        public SectionsPagerAdapter(FragmentManager fm,Categorie categorie) {
            super(fm);
            this.categorie = categorie;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(this.categorie.getSousCategories().get(position));
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return this.categorie.getSousCategories().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.categorie.getSousCategories().get(position).getNom();
        }
    }
    private void createCategories(){
        this.categorie = new Categorie(null,"root");
        Categorie homme = new Categorie(this.categorie,"Homme");
        Categorie femme = new Categorie(this.categorie,"Femme");
        Categorie enfant = new Categorie(this.categorie,"Enfant");
        this.categorie.getSousCategories().add(homme);
        this.categorie.getSousCategories().add(femme);
        this.categorie.getSousCategories().add(enfant);

        Categorie haut = new Categorie(homme,"Haut");
        haut.setIcon(R.drawable.ic_haut);

        Categorie bas = new Categorie(homme,"Bas");
        bas.setIcon(R.drawable.ic_bas);

        Categorie accessoires = new Categorie(homme,"Accessoires");
        accessoires.setIcon(R.drawable.ic_accessoires);

        Categorie chaussures = new Categorie(homme,"Chaussures");
        chaussures.setIcon(R.drawable.ic_chaussureshomme);

        Categorie costumes = new Categorie(homme,"Costumes");
        costumes.setIcon(R.drawable.ic_costumes);

        Categorie hautFemmes = new Categorie(femme,"Haut");
        hautFemmes.setIcon(R.drawable.ic_hautfemmes);

        Categorie basFemmes = new Categorie(femme,"Bas");
        basFemmes.setIcon(R.drawable.ic_basfemmes);

        Categorie chaussuresFemme = new Categorie(femme,"Chaussures");
        chaussuresFemme.setIcon(R.drawable.ic_chaussuresfemme);

        Categorie accessoiresFemme = new Categorie(femme,"Accessoires");
        accessoiresFemme.setIcon(R.drawable.ic_accessoiresfemme);

        homme.getSousCategories().add(haut);
        homme.getSousCategories().add(bas);
        homme.getSousCategories().add(chaussures);
        homme.getSousCategories().add(accessoires);

        Categorie chemises = new Categorie(haut, "Chemises");
        chemises.setIcon(R.drawable.ic_chemise);
        haut.getSousCategories().add(chemises);
        Categorie tshirt = new Categorie(haut,"T-Shirt");
        tshirt.setIcon(R.drawable.ic_haut);
        haut.getSousCategories().add(tshirt);
        Categorie vestes = new Categorie(haut,"Vestes");
        vestes.setIcon(R.drawable.ic_vestes);
        haut.getSousCategories().add(vestes);
        Categorie pantalons = new Categorie(bas,"Pantalons");
        pantalons.setIcon(R.drawable.ic_bas);
        bas.getSousCategories().add(pantalons);

        femme.getSousCategories().add(hautFemmes);
        femme.getSousCategories().add(basFemmes);
        femme.getSousCategories().add(chaussuresFemme);
        femme.getSousCategories().add(accessoiresFemme);

        Categorie hautEnfants = new Categorie(enfant,"Haut");
        hautEnfants.setIcon(R.drawable.ic_hautenfants);

        Categorie basEnfants = new Categorie(enfant,"Bas");
        basEnfants.setIcon(R.drawable.ic_basenfants);

        Categorie chaussuresEnfants = new Categorie(enfant,"Chaussures");
        chaussuresEnfants.setIcon(R.drawable.ic_chaussuresenfant);

        enfant.getSousCategories().add(hautEnfants);
        enfant.getSousCategories().add(basEnfants);
        enfant.getSousCategories().add(chaussuresEnfants);

    }
}
