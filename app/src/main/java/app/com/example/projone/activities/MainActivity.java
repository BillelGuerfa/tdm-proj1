package app.com.example.projone.activities;

import android.content.Intent;
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
    private List<Categorie> categories;
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

        //TODO: Fix this later.
        this.categories = new ArrayList<Categorie>();
        Categorie homme = new Categorie(true,"Homme");
        Categorie femme = new Categorie(true,"Femme");
        Categorie enfant = new Categorie(true,"Enfant");
        this.categories.add(homme);
        this.categories.add(femme);
        this.categories.add(enfant);

        Categorie haut = new Categorie(false,"Haut");
        haut.setIcon(R.drawable.ic_agilecovrer);

        Categorie bas = new Categorie(false,"Bas");
        bas.setIcon(R.drawable.ic_agilecovrer);

        Categorie accessoires = new Categorie(false,"Accessoires");
        accessoires.setIcon(R.drawable.ic_agilecovrer);

        Categorie chaussures = new Categorie(false,"Chaussures");
        chaussures.setIcon(R.drawable.ic_androidfd);

        Categorie costumes = new Categorie(false,"Costumes");
        costumes.setIcon(R.drawable.ic_androidfd);

        Categorie hautFemmes = new Categorie(false,"Haut");
        hautFemmes.setIcon(R.drawable.ic_agilecovrer);

        Categorie basFemmes = new Categorie(false,"Bas");
        basFemmes.setIcon(R.drawable.ic_agilecovrer);

        Categorie accessoiresFemme = new Categorie(false,"Accessoires");
        accessoiresFemme.setIcon(R.drawable.ic_agilecovrer);

        homme.getSousCategories().add(haut);
        homme.getSousCategories().add(bas);
        homme.getSousCategories().add(accessoires);

        haut.getSousCategories().add(new Categorie(false,"Chemises"));
        haut.getSousCategories().add(new Categorie(false,"T-Shirt"));
        haut.getSousCategories().add(new Categorie(false,"Vestes"));
        bas.getSousCategories().add(new Categorie(false,"Pantalons"));

        femme.getSousCategories().add(hautFemmes);
        femme.getSousCategories().add(basFemmes);
        femme.getSousCategories().add(accessoiresFemme);



        basFemmes.getSousCategories().add(new Categorie(false, "Jupes"));





        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),(ArrayList<Categorie>)this.categories);

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
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        ArrayList<Categorie> categories;
        CategoriesAdapter adapter;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(ArrayList<Categorie> categories) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putSerializable(ARG_CATEGORIE, categories);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            categories = (ArrayList) getArguments().getSerializable(ARG_CATEGORIE);
            listeCategories = (GridView) rootView.findViewById(R.id.liste_categories);
            adapter = new CategoriesAdapter(getActivity(),categories);
            listeCategories.setAdapter(adapter);
            listeCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (categories.get(position).getSousCategories().isEmpty()) {
                        Intent intent ; //TODO: Create intent here with the product list activity
                        //TODO: Send to products activity
                        ArrayList<Produit> produits = new ArrayList<Produit>();
                        //TODO:Copy product list here.


                    } else {

                        Toast.makeText(getContext(),categories.get(position).getNom(),Toast.LENGTH_SHORT).show();
                        ArrayList<Categorie> sousCategories = (ArrayList)categories.get(position).getSousCategories();
                        categories.clear();
                        categories.addAll(sousCategories);
                        adapter.notifyDataSetChanged();

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
        private ArrayList<Categorie> categories;
        public SectionsPagerAdapter(FragmentManager fm,ArrayList<Categorie> categories) {
            super(fm);
            this.categories = categories;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance((ArrayList<Categorie>)this.categories.get(position).getSousCategories());
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return this.categories.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.categories.get(position).getNom();
        }
    }
}
