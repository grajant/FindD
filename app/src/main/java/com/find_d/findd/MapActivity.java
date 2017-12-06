package com.find_d.findd;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.find_d.findd.Fragments.ListaFragment;
import com.find_d.findd.Fragments.MapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends DrawerActivity {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted = false;
    //private Location mLastKnownLocation;
    private FusedLocationProviderClient mLastKnownLocation;

    private BottomNavigationView navigation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (findViewById(R.id.content) != null){
            if (savedInstanceState != null){
                ListaFragment listaFragment = new ListaFragment();

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content, listaFragment).commit();
            }
        }
        navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.bnavigation_nearby_discos);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Pone el título de la actividad
        getSupportActionBar().setTitle("Mapa");
        setOriginalColorToolbar();

        // Marca la opción del Drawer Activity
        MenuItem item = navigationView.getMenu().getItem(1);
        item.setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.bnavigation_map:
                    //mTextMessage.setText(R.string.title_home);
                    fragment = new MapFragment();
                    break;
                case R.id.bnavigation_nearby_discos:
                    /*
                     * Request location permission, so that we can get the location of the
                     * device. The result of the permission request is handled by a callback,
                     * onRequestPermissionsResult.
                     */
                    if (ContextCompat.checkSelfPermission(getApplicationContext(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        mLocationPermissionGranted = true;
                    } else {
                        ActivityCompat.requestPermissions(MapActivity.this,
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                    }

                    fragment = new ListaFragment();
                    break;
            }
            if(fragment!=null){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, fragment).commit();
                return true;
            }
            return false;
        }

    };

    @SuppressLint("MissingPermission")
    @Override
    protected void onStart() {
        super.onStart();
        if (mLocationPermissionGranted){
            mFusedLocationProviderClient.getLastLocation()
                    .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            double lat = task.getResult().getLatitude();
                            double lon = task.getResult().getLongitude();
                        }
                    });
        }

    }
}
