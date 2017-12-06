package com.find_d.findd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import com.find_d.findd.CustomList;
import com.find_d.findd.DiscotecasListActivity;
import com.find_d.findd.R;

import java.util.ArrayList;


public class FilterFragment extends Fragment {
    protected int posit = 1;
    SearchView search;

    private Spinner Smusica, Spresupuesto, Subicacion, Scover;
    Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);



        Smusica = (Spinner) view.findViewById(R.id.Smusica);
        Spresupuesto = (Spinner) view.findViewById(R.id.Spresupuesto);
        Subicacion = (Spinner) view.findViewById(R.id.Subicacion);
        searchButton = (Button) view.findViewById(R.id.searchButton);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.musica, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Smusica.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),R.array.ubicacion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Subicacion.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(),R.array.presupuesto, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spresupuesto.setAdapter(adapter3);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiscotecasListActivity.class);
                intent.putExtra("musica",  Smusica.getSelectedItem().toString());
                intent.putExtra("ubicacion",  Subicacion.getSelectedItem().toString());
                intent.putExtra("presupuesto",  Spresupuesto.getSelectedItem().toString());
                startActivity(intent);
            }
        });



        return view;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<CustomList> list = new ArrayList<>();

    }




}
