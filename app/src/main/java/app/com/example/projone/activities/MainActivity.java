package app.com.example.projone.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
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
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Categorie;
import app.com.example.billelguerfa.projone.modele.Produit;
import app.com.example.projone.adapters.CategoriesAdapter;
import app.com.example.services.CategorieService;
import app.com.example.services.PanierService;
import app.com.example.services.ProduitService;
import app.com.example.services.UserService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Categorie categorie;
    private CategorieService _categorieService = new CategorieService();
    private ProduitService _produitService = new ProduitService();
    List<String> Lcouleur=new ArrayList<>();
    List<String> Lenfant=new ArrayList<>();
    List<String> Lhomme=new ArrayList<>();
    List<String> l2=new ArrayList<>();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Context that = this;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        mDatabase = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    UserService.firebaseUser = user;
                } else {
                    // User is signed out
                    UserService.firebaseUser = null;
                }
                // ...
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
        /*mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(that,"Auth failed",Toast.LENGTH_LONG).show();
                        }

                    }
                });*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //this.createCategories(); //instancie les catégories
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Create the adapter that will return a fragment for each of the three
                // primary sections of the activity.
                categorie =  _categorieService.recupCategories(dataSnapshot,"root",null);
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),categorie);

                // Set up the ViewPager with the sections adapter.
                mViewPager = (ViewPager) findViewById(R.id.container);
                mViewPager.setAdapter(mSectionsPagerAdapter);

                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(mViewPager);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        this._categorieService.getCatgories(valueEventListener);

       /* mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),categorie);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);*/

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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


            Intent intent = new Intent(this ,Panier_activity.class);
            intent.putExtra("listp", (Serializable) PanierService.getPanier().getListPanier());

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
        ProduitService produitService = new ProduitService();
        Intent intent ;
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
            intent  = new Intent(getActivity() ,ListeProduitsActivity.class);
            listeCategories.setAdapter(adapter);
            listeCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (adapter.getCategorie().getSousCategories().get(position).getSousCategories().size() == 0) {
                         //TODO: Create intent here with the product list activity
                        Categorie cat = adapter.getCategorie().getSousCategories().get(position);
                        intent.putExtra("cat",cat.getId());
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
        this.createTailles();
        this.categorie = new Categorie(null,"root","root");
        Categorie homme = new Categorie(this.categorie,"Homme","homme");
        Categorie femme = new Categorie(this.categorie,"Femme","femme");
        Categorie enfant = new Categorie(this.categorie,"Enfant","enfant");
        this.categorie.getSousCategories().add(homme);
        this.categorie.getSousCategories().add(femme);
        this.categorie.getSousCategories().add(enfant);

        Categorie haut = new Categorie(homme,"Haut","hautHomme");
        haut.setIcon(R.drawable.ic_haut);

        Categorie bas = new Categorie(homme,"Bas","basHomme");
        bas.setIcon(R.drawable.ic_bas);

        Categorie accessoires = new Categorie(homme,"Accessoires","accessoiresHomme");
        accessoires.setIcon(R.drawable.ic_accessoires);

        Categorie chaussures = new Categorie(homme,"Chaussures","chaussuresHomme");
        chaussures.setIcon(R.drawable.ic_chaussureshomme);
        chaussures.setProduits(this.creerChaussureHomme());

        Categorie costumes = new Categorie(homme,"Costumes","costumesHomme");
        costumes.setIcon(R.drawable.ic_costumes);

        Categorie hautFemmes = new Categorie(femme,"Haut","hautFemmes");
        hautFemmes.setIcon(R.drawable.ic_hautfemmes);

        Categorie basFemmes = new Categorie(femme,"Bas","basFemmes");
        basFemmes.setIcon(R.drawable.ic_basfemmes);

        Categorie chaussuresFemme = new Categorie(femme,"Chaussures","chaussuresFemme");
        chaussuresFemme.setIcon(R.drawable.ic_chaussuresfemme);

        Categorie accessoiresFemme = new Categorie(femme,"Accessoires","accessoiresFemme");
        accessoiresFemme.setIcon(R.drawable.ic_accessoiresfemme);

        homme.getSousCategories().add(haut);
        homme.getSousCategories().add(bas);
        homme.getSousCategories().add(chaussures);
        homme.getSousCategories().add(costumes);
        homme.getSousCategories().add(accessoires);

        Categorie chemises = new Categorie(haut, "Chemises","chemisesHomme");
        chemises.setIcon(R.drawable.ic_chemise);
        haut.getSousCategories().add(chemises);
        Categorie tshirt = new Categorie(haut,"T-Shirt","tshirtHomme");
        tshirt.setIcon(R.drawable.ic_haut);
        haut.getSousCategories().add(tshirt);
        Categorie vestes = new Categorie(haut,"Vestes","vestesHomme");
        vestes.setIcon(R.drawable.ic_vestes);
        haut.getSousCategories().add(vestes);
        Categorie pantalons = new Categorie(bas,"Pantalons","pantalonsHomme");
        pantalons.setIcon(R.drawable.ic_bas);
        pantalons.setProduits(this.creerJeanHomme());
        bas.getSousCategories().add(pantalons);

        Categorie chapeaux = new Categorie(accessoires,"Casquettes","chapeauxHomme");
        chapeaux.setIcon(R.drawable.ic_casquettehommeicone2);
        chapeaux.setProduits(this.creerCasquetteHomme());

        Categorie lunettesHomme = new Categorie(accessoires,"Lunettes","lunettesHomme");
        lunettesHomme.setIcon(R.drawable.ic_lunettehomme1icone);
        lunettesHomme.setProduits(this.creerLunetteHomme());

        Categorie centureHommes = new Categorie(accessoires,"Ceintures","centureHommes");
        centureHommes.setIcon(R.drawable.ic_centurehomme1icone);
        centureHommes.setProduits(this.creerCeintureHomme());

        accessoires.getSousCategories().add(chapeaux);
        accessoires.getSousCategories().add(lunettesHomme);
        accessoires.getSousCategories().add(centureHommes);


        Categorie jupesFemme = new Categorie(basFemmes,"Jupes","jupesFemme");
        jupesFemme.setIcon(R.drawable.ic_jupefemmeicone);
        jupesFemme.setProduits(this.creerJupeFemme());

        Categorie pantalonsFemme = new Categorie(basFemmes,"Pantalons","pantalonsFemme");
        pantalonsFemme.setIcon(R.drawable.ic_pantalonfemmeicone);
        pantalonsFemme.setProduits(this.creerPantalonFemme());

        basFemmes.getSousCategories().add(jupesFemme);
        basFemmes.getSousCategories().add(pantalonsFemme);

        femme.getSousCategories().add(hautFemmes);
        femme.getSousCategories().add(basFemmes);
        femme.getSousCategories().add(chaussuresFemme);
        femme.getSousCategories().add(accessoiresFemme);

        Categorie chapeauxFemme = new Categorie(accessoiresFemme, "Chapeaux","chapeauxFemme");
        chapeauxFemme.setIcon(R.drawable.ic_chapeau_fedora_femme);
        chapeauxFemme.setProduits(this.creerChapeauFemme());

        Categorie sacFemme = new Categorie(accessoiresFemme,"Sacs","sacFemme");
        sacFemme.setIcon(R.drawable.ic_sacfemme4icone);
        sacFemme.setProduits(this.creerSacfemme());

        Categorie lunettesFemme = new Categorie(accessoiresFemme,"Lunettes","lunettesFemme");
        lunettesFemme.setIcon(R.drawable.ic_lunettefemme1icone);
        lunettesFemme.setProduits(this.creerLunetteFemme());

        Categorie centureFemme = new Categorie(accessoiresFemme,"Ceintures","centureFemme");
        centureFemme.setIcon(R.drawable.ic_ceinturefemme2icone);
        centureFemme.setProduits(this.creerCeinturefemme());

        Categorie baguesFemme = new Categorie(accessoiresFemme,"Bagues","baguesFemme");
        baguesFemme.setIcon(R.drawable.ic_bague2icone);
        baguesFemme.setProduits(this.creerBagueFemme());


        accessoiresFemme.getSousCategories().add(chapeauxFemme);
        accessoiresFemme.getSousCategories().add(sacFemme);
        accessoiresFemme.getSousCategories().add(lunettesFemme);
        accessoiresFemme.getSousCategories().add(centureFemme);
        accessoiresFemme.getSousCategories().add(baguesFemme);


        Categorie hautEnfants = new Categorie(enfant,"Haut","hautEnfants");
        hautEnfants.setIcon(R.drawable.ic_hautenfants);

        Categorie basEnfants = new Categorie(enfant,"Bas","basEnfants");
        basEnfants.setIcon(R.drawable.ic_basenfants);

        Categorie chaussuresEnfants = new Categorie(enfant,"Chaussures","chaussuresEnfants");
        chaussuresEnfants.setIcon(R.drawable.ic_chaussuresenfant);
        chaussuresFemme.setProduits(this.creerChaussureFemme());

        enfant.getSousCategories().add(hautEnfants);
        basEnfants.setProduits(this.creerJeanEnfant());
        enfant.getSousCategories().add(basEnfants);
        enfant.getSousCategories().add(chaussuresEnfants);
        chaussuresEnfants.setProduits(this.creerChaussureEnfant());

        //insererCategorie(this.categorie);
        constructMapCat(this.categorie);
        insertProduitsbyCat(this.categorie);
    }

    private void showLoginDialog()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(that);
        final View view = layoutInflater.inflate(R.layout.login,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(view) ;
        builder.setTitle("Se connecter");
        builder.setPositiveButton("connecter", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText email = (EditText) view.findViewById(R.id.email);
                EditText password = (EditText) view.findViewById(R.id.password);
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener((Activity) that, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Log.w("lol", "signInWithEmail", task.getException());
                                    Toast.makeText(that, "Authentication failed." ,
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(that, "Vous étes maintenant connecté", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        builder.setNegativeButton("Creer un compte", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText email = (EditText) view.findViewById(R.id.email);
                EditText password = (EditText) view.findViewById(R.id.password);
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener((Activity) that, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Log.w("lol", "signInWithEmail", task.getException());
                            Toast.makeText(that, "registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(that, "Vous étes maintenant connecté", Toast.LENGTH_LONG).show();
                        }
                    }

                });


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
        produits.add(new Produit("Chaussure adidas  orignials style sport ","adidas",Lhomme,Lcouleur, R.drawable.ic_adidas_homme,7200));
        produits.add(new Produit("Basket adidas  orignials style sport ","adidas",Lhomme,Lcouleur,R.drawable.ic_adidas_homme1,7500));
        produits.add(new Produit("Chaussure adidas  orignials style sport ","adidas",Lhomme,Lcouleur,R.drawable.ic_adidas_homme4,9000));
        produits.add(new Produit("Chaussure adidas  orignials style sport ","adidas",Lhomme,Lcouleur,R.drawable.ic_adidas_baskets,8000));
        produits.add(new Produit("Basket montante adidas  orignials style sport ","adidas",Lcouleur,Lhomme,R.drawable.ic_adidas_originals,5500));

        return produits;
    }
    public List<Produit> creerChaussureFemme()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Chassure Femme
        produits.add(new Produit("Chaussure Ballerines pour tous les jours  ","adidas",Lhomme,Lcouleur,R.drawable.ic_ballerine_dune_6097207_2,7200));
        produits.add(new Produit("Chaussure talons bas  ","Gucci",Lhomme,Lcouleur,R.drawable.ic_zalando_2,7500));
        produits.add(new Produit("Chaussure talon haut ","Gucci",Lhomme,Lcouleur,R.drawable.ic_zalando_chaussure_4,9000));
        produits.add(new Produit("Chaussure ballerine pour tous les jours ","adidas",Lhomme,Lcouleur,R.drawable.ic_love_moschino_ballerines_noir,8000));
        produits.add(new Produit("Chaussure pour tous les jours ","adidas",Lhomme,Lcouleur,R.drawable.ic_soldes_zalando,5500));

        return produits;
    }
    public List<Produit> creerChaussureEnfant()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Chassure Enfant
        produits.add(new Produit("Basket  style sport ", "adidas",Lhomme,Lcouleur,R.drawable.ic_chaussure_enfant1, 7200));
        produits.add(new Produit("Chaussure style classique ", "adidas",Lhomme,Lcouleur,R.drawable.ic_chassure_enfant3, 7500));
        produits.add(new Produit("Chaussure style sport ", "adidas", Lhomme,Lcouleur, R.drawable.ic_chaussure_enfant2, 9000));
        produits.add(new Produit("Basket adidas  orignials style sport ", "adidas", Lhomme, Lcouleur,R.drawable.ic_chaussures_enfant,8000));
        produits.add(new Produit("Basket adidas  orignials style sport ","adidas",Lhomme,Lcouleur,R.drawable.ic_chaussure_enfant4,5500));

        return produits;
    }
    public List<Produit> creerMontreEnfant()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Montre Enfant
        produits.add(new Produit("Montre enfant solide", "lego",R.drawable.ic_montreenfan1, 7200));
        produits.add(new Produit("Montre enfant solide", "dora",R.drawable.ic_montreenfan2, 3200));
        produits.add(new Produit("Montre enfant solide", "zuchi",R.drawable.ic_montreenfan3, 2200));
        produits.add(new Produit("Montre enfant solide", "gon",R.drawable.ic_montreenfan4, 1200));
        produits.add(new Produit("Montre enfant solide", "flavio",R.drawable.ic_montreenfan5, 700));
        produits.add(new Produit("Montre enfant solide", "kilua",R.drawable.ic_montreenfan6, 900));
        produits.add(new Produit("Montre enfant solide", "hisoka",R.drawable.ic_montreenfan7, 999));
        produits.add(new Produit("Montre enfant solide", "urushima",R.drawable.ic_montreenfan8, 7200));



        return produits;
    }

    public List<Produit> creerMontreHomme()
    {
        List<Produit> produits= new ArrayList<Produit>();
        //Montre Enfant
        produits.add(new Produit("Montre homme elegant", "lego",R.drawable.ic_montrehomme1, 7200));
        produits.add(new Produit("Montre homme elegant", "dora",Lenfant,R.drawable.ic_montrehomme2, 3200));
        produits.add(new Produit("Montre homme elegant", "zuchi",Lenfant,R.drawable.ic_montrehomme3, 2200));
        produits.add(new Produit("Montre homme elegant", "gon",Lenfant,R.drawable.ic_montrehomme4, 1200));
        produits.add(new Produit("Montre homme elegant", "flavio",Lenfant,R.drawable.ic_montrehomme5, 700));
        produits.add(new Produit("Montre homme elegant", "kilua",Lenfant,R.drawable.ic_montrehomme6, 900));
        produits.add(new Produit("Montre homme elegant", "hisoka",Lenfant,R.drawable.ic_montrehomme7, 999));
        produits.add(new Produit("Montre homme elegant", "urushima",Lenfant,R.drawable.ic_montrehomme8, 7200));



        return produits;
    }

    public List<Produit> creerMontreFemme() {
        List<Produit> produits = new ArrayList<Produit>();
        produits.add(new Produit("Montre femme elegante", "zara",R.drawable.ic_montrefemme1, 7200));
        produits.add(new Produit("Montre femme elegante", "mongo",R.drawable.ic_montrefemme2, 4300));
        produits.add(new Produit("Montre femme elegante", "timsah",R.drawable.ic_montrefemme3, 9400));
        produits.add(new Produit("Montre femme elegante", "triicoo",R.drawable.ic_montrefemme4, 10000));
        produits.add(new Produit("Montre femme elegante", "ricci",R.drawable.ic_montrefemme5, 7900));
        produits.add(new Produit("Montre femme elegante", "dior",R.drawable.ic_montrefemme6, 7300));
        produits.add(new Produit("Montre femme elegante", "zara",R.drawable.ic_montrefemme7, 8400));
        produits.add(new Produit("Montre femme elegante", "dior",R.drawable.ic_montrefemme8, 6600));



        return produits;

    }

    public List<Produit> creerJupeFemme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Jupe femme nouvelle collection", "zara",R.drawable.ic_jupefemme1, 7200));
        produits.add(new Produit("Jupe femme nouvelle collection", "mongo",R.drawable.ic_jupefemme2, 4300));
        produits.add(new Produit("Jupe femme nouvelle collection", "timsah",R.drawable.ic_jupefemme3, 9400));
        produits.add(new Produit("Jupe femme nouvelle collection", "triicoo",R.drawable.ic_jupefemme4, 10000));
        produits.add(new Produit("Jupe femme nouvelle collection", "ricci",R.drawable.ic_jupefemme5, 7900));
        produits.add(new Produit("Jupe femme nouvelle collection", "dior",R.drawable.ic_jupefemme6, 7300));


        return produits;

    }

    public List<Produit> creerPantalonFemme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Pantalon femme nouvelle collection", "zara",R.drawable.ic_pantalonfemme1, 7200));
        produits.add(new Produit("Pantalon femme nouvelle collection", "mongo",R.drawable.ic_pantalonfemme2, 4300));
        produits.add(new Produit("Pantalon femme nouvelle collection", "timsah",R.drawable.ic_pantalonfemme3, 9400));
        produits.add(new Produit("Pantalon femme nouvelle collection", "triicoo",R.drawable.ic_pantalonfemme4, 10000));
        produits.add(new Produit("Pantalon femme nouvelle collection", "ricci",R.drawable.ic_pantalonfemme5, 7900));
        produits.add(new Produit("Pantalon femme nouvelle collection", "dior",R.drawable.ic_pantalonfemme6, 7300));
        produits.add(new Produit("Pantalon femme nouvelle collection", "dior",R.drawable.ic_pantalonfemme7, 7300));



        return produits;

    }

    public List<Produit> creerBagueFemme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Bague femme nouvelle collection", "zara",R.drawable.ic_bague1, 7200));
        produits.add(new Produit("Bague femme nouvelle collection", "mongo",R.drawable.ic_bague2, 4300));
        produits.add(new Produit("Bague femme nouvelle collection", "timsah",R.drawable.ic_bague3, 9400));
        produits.add(new Produit("Bague femme nouvelle collection", "triicoo",R.drawable.ic_bague4, 10000));
        produits.add(new Produit("Bague femme nouvelle collection", "ricci",R.drawable.ic_bague5, 7900));




        return produits;

    }

    public List<Produit> creerLunetteFemme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Lunette femme nouvelle collection", "zara",R.drawable.ic_lunettefemme1, 7200));
        produits.add(new Produit("Lunette femme nouvelle collection", "mongo",R.drawable.ic_lunettefemme2, 4300));
        produits.add(new Produit("Lunette femme nouvelle collection", "timsah",R.drawable.ic_lunettefemme3, 9400));
        produits.add(new Produit("Lunette femme nouvelle collection", "triicoo",R.drawable.ic_lunettefemme4, 10000));
        produits.add(new Produit("Lunette femme nouvelle collection", "ricci",R.drawable.ic_lunettefemme5, 7900));




        return produits;

    }

    public List<Produit> creerLunetteHomme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Lunette homme nouvelle collection", "rayban",R.drawable.ic_lunettehomme1, 7200));
        produits.add(new Produit("Lunette homme nouvelle collection", "massimo",R.drawable.ic_lunettehomme2, 4300));
        produits.add(new Produit("Lunette homme nouvelle collection", "dior",R.drawable.ic_lunettehomme3, 9400));
        produits.add(new Produit("Lunette homme nouvelle collection", "carrera",R.drawable.ic_lunettehomme4, 10000));




        return produits;

    }


    public List<Produit> creerSacfemme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Sac femme nouvelle collection", "gucci",R.drawable.ic_sacfemme1, 9000));
        produits.add(new Produit("Sac femme nouvelle collection", "massimo",R.drawable.ic_sacfemme2, 4300));
        produits.add(new Produit("Sac femme nouvelle collection", "dior",R.drawable.ic_sacfemme3, 9400));
        produits.add(new Produit("Sac femme nouvelle collection", "ricci",R.drawable.ic_sacfemme4, 2000));
        produits.add(new Produit("Sac femme nouvelle collection", "guess",R.drawable.ic_sacfemme5, 3000));
        produits.add(new Produit("Sac femme nouvelle collection", "burburry",R.drawable.ic_sacfemme6, 3200));





        return produits;

    }

    public List<Produit> creerCeinturefemme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("ceinture femme nouvelle collection", "Gucci",R.drawable.ic_ceinturefemme1, 9000));
        produits.add(new Produit("ceinture femme nouvelle collection", "Guess",R.drawable.ic_ceinturefemme2, 4300));
        produits.add(new Produit("ceinture femme nouvelle collection", "Gucci",R.drawable.ic_ceinturefemme3, 9400));
        produits.add(new Produit("ceinture femme nouvelle collection", "RRicci",R.drawable.ic_ceinturefemme4, 2000));



        return produits;

    }

    public List<Produit> creerCeintureHomme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("ceinture homme nouvelle collection", "Gucci",R.drawable.ic_centurehomme1, 5000));
        produits.add(new Produit("ceinture homme nouvelle collection", "LuisVuitton",R.drawable.ic_centurehomme2, 4300));
        produits.add(new Produit("ceinture homme nouvelle collection", "Gucci",R.drawable.ic_centurehomme3, 7400));
        produits.add(new Produit("ceinture homme nouvelle collection", "YvesSaintLaurent",R.drawable.ic_centurehomme4, 2500));



        return produits;

    }

    public List<Produit> creerCasquetteHomme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Casquette homme nouvelle collection", "59Fifty",R.drawable.ic_casquettenyhomme1, 3000));
        produits.add(new Produit("Casquette homme nouvelle collection", "Tommy",R.drawable.ic_casquettetomyhomme2, 2300));
        produits.add(new Produit("Casquette homme nouvelle collection", "Berret",R.drawable.ic_casquettehomme3, 1400));



        return produits;

    }

    public List<Produit> creerChapeauFemme() {
        List<Produit> produits = new ArrayList<Produit>();

        produits.add(new Produit("Casquette homme nouvelle collection", "Brixton",R.drawable.ic_chapeau_brixto, 3000));
        produits.add(new Produit("Casquette homme nouvelle collection", "Fedora",R.drawable.ic_chapeau_fedora_feutre_classiq, 2300));
        produits.add(new Produit("Casquette homme nouvelle collection", "Triicoo",R.drawable.ic_chapeaufemme3, 1400));



        return produits;

    }

    public void insertProduitsbyCat(Categorie categorie){
        if(categorie.getProduits() == null){
            if(categorie.getSousCategories() != null){
                for(int i=0;i<categorie.getSousCategories().size();i++){
                    this.insertProduitsbyCat(categorie.getSousCategories().get(i));
                }
            }

        }
        else{
            List<Produit> produits = categorie.getProduits();

            for (int i=0;i<produits.size();i++)
            {
                produits.get(i).setQuantite(500);
                mDatabase.child("produits").push().setValue(produits.get(i).toMap(encoderImage(produits.get(i).getPhoto())));

            }


        }
    }

    public void  constructMapCat(Categorie categorie){
        HashMap<String,Boolean> sousCat;

        if(categorie.getSousCategories() != null) {
            sousCat = new HashMap<>();
            for (Categorie cat : categorie.getSousCategories()) {
                sousCat.put(cat.getId(), true);
            }
            if (categorie.getIcon() == 0) {
                mDatabase.child("categories").child(categorie.getId()).setValue(categorie.toMap(sousCat));
            }
            else {
                mDatabase.child("categories").child(categorie.getId()).setValue(categorie.toMap(encoderImage(categorie.getIcon()),sousCat));
            }
            for (Categorie cat : categorie.getSousCategories()) {
                this.constructMapCat(cat);
            }
        }
        else{
            if (categorie.getIcon() == 0) {
                mDatabase.child("categories").child(categorie.getId()).setValue(categorie.toMap(null));
            }
            else {
                mDatabase.child("categories").child(categorie.getId()).setValue(categorie.toMap(encoderImage(categorie.getIcon()),null));
            }
        }
    }


    public String encoderImage(int photo)
    {
        Bitmap bmp =  BitmapFactory.decodeResource(getResources(),photo);//your image
        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
        bmp.recycle();
        byte[] byteArray = bYtE.toByteArray();
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encodedImage;
    }
}
