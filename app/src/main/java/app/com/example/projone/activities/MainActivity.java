package app.com.example.projone.activities;

import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
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
    List<String> Lcouleur=new ArrayList<>();
    List<String> Lenfant=new ArrayList<>();
    List<String> Lhomme=new ArrayList<>();
    List<String> l2=new ArrayList<>();
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
        }else if(id == R.id.nav_login){
             this.showLoginDialog();
         }
         else if(id == R.id.nav_suivi){

             Intent intent = new Intent(this,CommandesActivity.class);
             startActivity(intent);
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


                        Intent intent = new Intent(getActivity() ,ListeProduitsActivity.class);
                        intent.putExtra("listp", (ArrayList<Produit>)adapter.getCategorie().getSousCategories().get(position).getProduits());

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
        chaussures.setProduits(this.creerChaussureHomme());

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
        homme.getSousCategories().add(costumes);
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
        pantalons.setProduits(this.creerJeanHomme());
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
        chaussuresFemme.setProduits(this.creerChaussureFemme());

        enfant.getSousCategories().add(hautEnfants);
        basEnfants.setProduits(this.creerJeanEnfant());
        enfant.getSousCategories().add(basEnfants);
        enfant.getSousCategories().add(chaussuresEnfants);
        chaussuresEnfants.setProduits(this.creerChaussureEnfant());

    }

    private void showLoginDialog()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(R.layout.login) ;
        builder.setTitle("Se connecter");
        builder.setPositiveButton("connecter", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.setNegativeButton("Creer un compte", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void createTailles(){
        Lenfant.add("28");
        Lenfant.add("28.5");
        Lenfant.add("29");
        Lenfant.add("30");
        Lenfant.add("30.5");
        Lenfant.add("31");




        Lhomme.add("40");
        Lhomme.add("41");
        Lhomme.add("42.5");
        Lhomme.add("43");
        Lhomme.add("43.5");
        Lhomme.add("44");
        Lhomme.add("45");


        l2.add("W32-H34");
        l2.add("W34-H34");
        l2.add("W36-H38");
        l2.add("W38-H36");
        l2.add("W40-H40");
        l2.add("W42-H42");


        Lcouleur.add("orange");
        Lcouleur.add("bleu");
        Lcouleur.add("vert");
        Lcouleur.add("noir");
        Lcouleur.add("blanc");
    }

    public List<Produit> creerJeanHomme()
    {
        List<Produit> produits= new ArrayList<Produit>();
        ////Jean Homme////
        produits.add(new Produit("Levis - Jean - Homme - 511 Slim Fit - Green Splash - Brut","Levis",l2,R.drawable.ic_levis_jean_homme_511_slim,2500));
        produits.add(new Produit("Diesel Industry Krooley","Diesel",l2,R.drawable.ic_diesel_jean,2500));
        produits.add(new Produit("Levis Fit - Green Splash - Brut","Levis",l2,R.drawable.ic_levis_jean_homme_511_slim1,2700));
        produits.add(new Produit("Jack and jones Fit - Slim - Brut","Jack And jones",l2,R.drawable.ic_jeans_slim,3500));
        produits.add(new Produit("Jack and jones  -Fit - Brut","Levis",l2,R.drawable.ic_jean_jack_and_jones,2700));
        produits.add(new Produit("Slim jean -Fit - daim  -Marron  ","Diesel",l2,R.drawable.ic_jean_slim,3800));

        return produits;
    }

    public List<Produit> creerJeanEnfant()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Jean enfant
        produits.add(new Produit("Levis Fit - Green Splash - Brut","Levis",l2,R.drawable.ic_jean_enfant,2700));
        produits.add(new Produit("Jack and jones Fit - Slim - Brut","Jack And jones",l2,R.drawable.ic_jean_enfant2,3500));
        produits.add(new Produit("Jack and jones  -Fit - Brut","Levis",l2,R.drawable.ic_jean_enfant3,2700));
        produits.add(new Produit("Slim jean -Fit - daim    ","Diesel",l2,R.drawable.ic_jean_enfant4,4000));
        produits.add(new Produit("Slim jean -Fit - daim  -Marron  ","Levis",l2,R.drawable.ic_jean_enfant5,3800));
        produits.add(new Produit("Slim jean -Fit - daim  -Marron  ","A",l2,R.drawable.ic_jean_enfant6,4700));

        return produits;
    }

    public List<Produit> creerChaussureHomme()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Chassure Homme
        produits.add(new Produit("Chaussure adidas  orignials style sport ","adidas",Lhomme,R.drawable.ic_adidas_homme,7200));
        produits.add(new Produit("Basket adidas  orignials style sport ","adidas",Lhomme,R.drawable.ic_adidas_homme1,7500));
        produits.add(new Produit("Chaussure adidas  orignials style sport ","adidas",Lhomme,R.drawable.ic_adidas_homme4,9000));
        produits.add(new Produit("Chaussure adidas  orignials style sport ","adidas",Lhomme,R.drawable.ic_adidas_baskets,8000));
        produits.add(new Produit("Basket montante adidas  orignials style sport ","adidas",Lhomme,R.drawable.ic_adidas_originals,5500));

        return produits;
    }
    public List<Produit> creerChaussureFemme()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Chassure Femme
        produits.add(new Produit("Chaussure Ballerines pour tous les jours  ","adidas",Lhomme,R.drawable.ic_ballerine_dune_6097207_2,7200));
        produits.add(new Produit("Chaussure talons bas  ","Gucci",Lhomme,R.drawable.ic_zalando_2,7500));
        produits.add(new Produit("Chaussure talon haut ","Gucci",Lhomme,R.drawable.ic_zalando_chaussure_4,9000));
        produits.add(new Produit("Chaussure ballerine pour tous les jours ","adidas",Lhomme,R.drawable.ic_love_moschino_ballerines_noir,8000));
        produits.add(new Produit("Chaussure pour tous les jours ","adidas",Lhomme,R.drawable.ic_soldes_zalando,5500));

        return produits;
    }
    public List<Produit> creerChaussureEnfant()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Chassure Enfant
        produits.add(new Produit("Basket  style sport ", "adidas",Lhomme,R.drawable.ic_chaussure_enfant1, 7200));
        produits.add(new Produit("Chaussure style classique ", "adidas",Lhomme,R.drawable.ic_chassure_enfant3, 7500));
        produits.add(new Produit("Chaussure style sport ", "adidas", Lhomme, R.drawable.ic_chaussure_enfant2, 9000));
        produits.add(new Produit("Basket adidas  orignials style sport ", "adidas", Lhomme, R.drawable.ic_chaussures_enfant,8000));
        produits.add(new Produit("Basket adidas  orignials style sport ","adidas",Lhomme,R.drawable.ic_chaussure_enfant4,5500));

        return produits;
    }
}
