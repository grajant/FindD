package com.find_d.findd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.find_d.findd.Fragments.TabFragment;

public class FilterActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Pone el título de la actividad
        getSupportActionBar().setTitle("Encuentra tu discoteca");
        setOriginalColorToolbar();

        // Marca la opción del Drawer Activity
        MenuItem item = navigationView.getMenu().getItem(2);
        item.setChecked(true);

        // Pone el fragment de los Tabs!
        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            TabFragment tabFragment = new TabFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, tabFragment).commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
