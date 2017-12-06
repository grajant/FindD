package com.find_d.findd.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.find_d.findd.Discotecas;
import com.find_d.findd.DiscotecasListAdapter;
import com.find_d.findd.FetchImage;
import com.find_d.findd.R;

import java.util.ArrayList;

/**
 * Created by Paula on 26/11/2017.
 */

public class DiscotecasListFragment extends ListFragment {
    FirebaseDatabase database;
    DatabaseReference DBReference;
    FetchImage fetchImage;
    ArrayList<Discotecas> list = new ArrayList<>();
    int listCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        DBReference = database.getReference("Discotecas");

        Query query = DBReference.orderByChild(String.valueOf(0));
        query.addListenerForSingleValueEvent(queryListener);
        //var = getIntent().getIntExtra("filter",-1);
    }

    private ValueEventListener queryListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            list.clear();
            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                        postSnapshot.child("telefono").getValue().toString(),postSnapshot.child("musica").getValue().toString(),postSnapshot.child("presupuesto").getValue().toString()));

            }

            fetchImage = new FetchImage(getContext(), fetchListener);
            fetchImage.execute(list.get(listCounter).getImageURL());
            listCounter++;
            //DiscotecasListAdapter adapter = new DiscotecasListAdapter(getContext(), list);
            //setListAdapter(adapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private FetchImage.AsyncResponse fetchListener = new FetchImage.AsyncResponse() {
        @Override
        public void processFinish(Bitmap bitmap) {
            list.get(listCounter).setImage(bitmap);
            listCounter++;
            if (listCounter < list.size()){
                fetchImage = new FetchImage(getContext(), fetchListener);
                fetchImage.execute(list.get(listCounter).getImageURL());

            }else {         // Set adapter
                DiscotecasListAdapter adapter = new DiscotecasListAdapter(getContext(), list);
                setListAdapter(adapter);
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        fetchImage.cancel(true);
    }
}
