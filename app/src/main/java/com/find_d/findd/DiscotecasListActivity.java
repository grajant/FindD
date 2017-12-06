package com.find_d.findd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiscotecasListActivity extends AppCompatActivity {

    String[] music = {
            "crossover",
            "bachata"
    };




    ArrayList<Discotecas> list = new ArrayList<>();
    ArrayList<String> musicCategory = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String ndb,nfilter;
    int var;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco_list);

        // Título de la Actividad
        getSupportActionBar().setTitle("Resultado de la búsqueda");

        Bundle extras = getIntent().getExtras();
        final String musica = extras.getString("musica");
        final String ubicacion = extras.getString("ubicacion");
        final String presupuesto = extras.getString("presupuesto");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Discotecas");
        //var = getIntent().getIntExtra("filter",-1);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    if (postSnapshot.child("ubicacion").exists()) {
                        if (musica.compareToIgnoreCase(postSnapshot.child("musica").getValue().toString()) == 0 &&
                                ubicacion.compareToIgnoreCase(postSnapshot.child("ubicacion").getValue().toString()) == 0 &&
                                presupuesto.compareToIgnoreCase(postSnapshot.child("presupuesto").getValue().toString()) == 0)
                            list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(), postSnapshot.getKey(), postSnapshot.child("direccion").getValue().toString(),
                                    postSnapshot.child("telefono").getValue().toString(), postSnapshot.child("musica").getValue().toString(), postSnapshot.child("presupuesto").getValue().toString()));
                    }


                }
                recyclerView = (RecyclerView)findViewById(R.id.recycler);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(DiscotecasListActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                TextView terr = (TextView)findViewById(R.id.tErr);
                if(list.isEmpty()){
                    terr.setVisibility(View.VISIBLE);
                }else{
                    terr.setVisibility(View.GONE);
                }
                adapter = new DiscotecasAdapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
