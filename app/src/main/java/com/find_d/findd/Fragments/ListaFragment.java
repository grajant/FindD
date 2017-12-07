package com.find_d.findd.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.find_d.findd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {


    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentj
        return inflater.inflate(R.layout.fragment_nearby_disco, container, false);
    }

}
