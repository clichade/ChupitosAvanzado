package com.example.trjano.chupitos;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity principal que funcionará mediante tabs accedienco a los fragments Menú e inicio
 */
public class Main_Activity extends AppCompatActivity {


    /**
     * La variable que almacena el tab
     */
    private TabLayout tabLayout;

    /**
     * La variable que contiene el administrador de vistas(los fragments)
     */
    private ViewPager viewPager;

    /**
     * On create es el momento del ciclo de vida de una activity en la que se declara su vista y donde se deben inicializar
     * las estructuras
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//declaro que layout usará para la vista

        //inicializamos el viewPager de la layout activity_main
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //inicializamos el tabLayout de la layout acvitity_main
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);//unimos el tab con el viewpager
    }


    /**
     * tenemos que crear el método para setear el viewpager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        //necesitamos un adaptador para conectar la view con el fragment
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //añadimos al adaptador los fragments que vamos a utilizar con el nombre delos taba
        adapter.addFragment(new Chupito_list_fragment(), "MENÚ");
        adapter.addFragment(new Inicio_fragment(),"INICIO");
        viewPager.setAdapter(adapter);
    }

    //creamos una clase para adaptar la vista al fragment que hereda de fragmentPAgerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        //usamos arraylist para administrar los fragments y las etiquetas de los tabs
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        //contructor con fragment manager se suele inicializar con getSupportFragmentManager() perteneciente a una activity
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

