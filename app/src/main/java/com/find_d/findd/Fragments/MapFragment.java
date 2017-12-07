package com.find_d.findd.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.find_d.findd.Discotecas;
import com.find_d.findd.Tags;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.find_d.findd.InfoActivity;
import com.find_d.findd.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private ArrayList<Discotecas> discotecasList = new ArrayList<>();

    public MapFragment() {
        // Required empty public constructor
    }

    MapView mapView;
    private GoogleMap mMap;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Discotecas");


        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle args = getArguments();
        // Add a marker in Sydney and move the camera
        LatLng myPosition = new LatLng(args.getDouble(Tags.TAG_LAT), args.getDouble(Tags.TAG_LON));
        mMap.addMarker(new MarkerOptions().position(myPosition).title("Posición actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,14));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    if(postSnapshot.child("Lat").exists() && postSnapshot.child("Lng").exists() && postSnapshot.child("direccion").exists()
                            && postSnapshot.child("telefono").exists() && postSnapshot.child("presupuesto").exists() && postSnapshot.child("musica").exists()) {


                        // Discotecas(String imageURL, String name, String dir, String tel, String music, String price)
                        discotecasList.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),
                                postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString()));

                        LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("Lat").getValue().toString()), Double.parseDouble(postSnapshot.child("Lng").getValue().toString()));

                        MarkerOptions mark = new MarkerOptions().position(marker).title(postSnapshot.getKey()).
                                snippet(postSnapshot.getKey()+
                                        "\n" + postSnapshot.child("direccion").getValue().toString() +
                                        "\n" + postSnapshot.child("telefono").getValue().toString() +
                                        "\n" + postSnapshot.child("musica").getValue().toString() +
                                        "\n" + postSnapshot.child("presupuesto").getValue().toString() +
                                        "\n" + postSnapshot.child("URL").getValue().toString());
                        mMap.addMarker(mark);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                for(Discotecas discotecas:discotecasList.toArray(new Discotecas[discotecasList.size()])){
                    if (discotecas.getName().equals(marker.getTitle())){
                        intent.putExtra("marker_data", discotecas);
                        getActivity().startActivity(intent);
                    }
                }

            }
        });

    }

    private void drawCircle(LatLng dstLatLng, int size){
        CircleOptions circleOptions = new CircleOptions()
                .center(dstLatLng)
                .strokeColor(Color.TRANSPARENT)
                .strokeWidth(1)
                .fillColor(Color.argb(100, 164, 171, 167))
                .radius(size*10);
        mMap.addCircle(circleOptions);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
