package com.find_d.findd;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MainActivity extends DrawerActivity {

    private ArrayList<Discotecas> list = new ArrayList<>();
    private TextView[] tNombre = new TextView[4];
    private RatingBar[] discoRatingBar = new RatingBar[4];
    private ImageView iprimera, isegunda, itercera,icuarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pone el título de la actividad
        getSupportActionBar().setTitle("Inicio");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Marca la opción del Drawer Activity
        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);

        /**
         * Inicializa los elementos del layout
         */
        discoRatingBar[0] = findViewById(R.id.rating_bar_0);
        discoRatingBar[1] = findViewById(R.id.rating_bar_1);
        discoRatingBar[2] = findViewById(R.id.rating_bar_2);
        discoRatingBar[3] = findViewById(R.id.rating_bar_3);
        tNombre[0] = findViewById(R.id.tSemana1);
        tNombre[1] = findViewById(R.id.tSemana2);
        tNombre[2] = findViewById(R.id.tSemana3);
        tNombre[3] = findViewById(R.id.tSemana4);
        iprimera = (ImageView) findViewById(R.id.iPrimera);
        isegunda = (ImageView) findViewById(R.id.iSegunda);
        itercera = (ImageView) findViewById(R.id.iTercera);
        icuarta = (ImageView) findViewById(R.id.iCuarta);

        /**
         * Lee la base de datos y toma 4 {@link Discotecas}
         */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Discotecas");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    String temp = postSnapshot.getKey().toString();
                    if ("Babylon".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                (float)5)
                        );
                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    iprimera.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());
                    }
                    if ("oye bonita".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                (float)3.5));
                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    isegunda.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());


                    }
                    if ("Sabana bar".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                (float)4));

                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    itercera.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());


                    }
                    if ("La ruana de juana".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                (float)3));
                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    icuarta.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());


                    }
                }
                // Ordena la lista de discotecas de mayor a menor calificación
                Collections.sort(list, new Comparator<Discotecas>() {
                    @Override
                    public int compare(Discotecas o1, Discotecas o2) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return o1.getRating() > o2.getRating() ? -1 : (o1.getRating() < o2.getRating()) ? 1 : 0;
                    }
                });
                // Despliega las discotecas en los cardView
                for (int i = 0; i < 4; i++){
                    if (list.get(i) != null){
                        tNombre[i].setText(list.get(i).getName());
                        discoRatingBar[i].setRating(list.get(i).getRating());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        //.makeText(this, "On Start", Toast.LENGTH_SHORT).show();

    }

    public void onDisco1Clicked(View view) {
        Intent intent = new Intent(MainActivity.this, DiscoProfileActivity.class);
        intent.putExtra(Tags.TAG_DISCO, list.get(0));
        startActivity(intent);
    }

    public void onDisco2Clicked(View view) {
        Intent intent = new Intent(MainActivity.this, DiscoProfileActivity.class);
        intent.putExtra(Tags.TAG_DISCO, list.get(1));
        startActivity(intent);
    }

    public void onDisco3Clicked(View view) {
        Intent intent = new Intent(MainActivity.this, DiscoProfileActivity.class);
        intent.putExtra(Tags.TAG_DISCO, list.get(2));
        startActivity(intent);
    }

    public void onDisco4Clicked(View view) {
        Intent intent = new Intent(MainActivity.this, DiscoProfileActivity.class);
        intent.putExtra(Tags.TAG_DISCO, list.get(3));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "On Resume", Toast.LENGTH_SHORT).show();
    }
}
