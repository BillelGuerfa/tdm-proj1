package app.com.example.projone.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
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

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Categorie;
import app.com.example.projone.adapters.CategoriesAdapter;

public class MainActivity extends AppCompatActivity {
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
        Categorie hautFemmes = new Categorie(false,"Haut");
        hautFemmes.setIcon(R.drawable.ic_agilecovrer);
        Categorie basFemmes = new Categorie(false,"Bas");
        basFemmes.setIcon(R.drawable.ic_agilecovrer);
        homme.getSousCategories().add(bas);
        homme.getSousCategories().add(accessoires);
        femme.getSousCategories().add(hautFemmes);
        Categorie accessoiresFemme = new Categorie(false,"Accessoires");
        accessoires.setIcon(R.drawable.ic_agilecovrer);
        homme.getSousCategories().add(haut);
        femme.getSousCategories().add(basFemmes);
        femme.getSousCategories().add(accessoiresFemme);
        haut.getSousCategories().add(new Categorie(false,"T-Shirt"));
        haut.getSousCategories().add(new Categorie(false,"Chemises"));
        bas.getSousCategories().add(new Categorie(false,"Pantalons"));
        basFemmes.getSousCategories().add(new Categorie(false,"Jupes"));

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

        return super.onOptionsItemSelected(item);
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
            ArrayList<Categorie> categories = (ArrayList) getArguments().getSerializable(ARG_CATEGORIE);

            ListView listeCategories = (ListView) rootView.findViewById(R.id.liste_categories);
            listeCategories.setAdapter(new CategoriesAdapter(getActivity(),categories));
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
