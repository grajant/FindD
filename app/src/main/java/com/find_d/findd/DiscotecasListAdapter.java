package com.find_d.findd;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by Paula on 26/11/2017.
 */

public class DiscotecasListAdapter extends ArrayAdapter<Discotecas> {

    public DiscotecasListAdapter(@NonNull Context context, ArrayList<Discotecas> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        // Get the data item for this position
        Discotecas itemDiscotecas = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.disco_details, parent, false);
        }

        // Lookup view for data population

        final CircularImageView image = v.findViewById(R.id.imbar);
        TextView name = (TextView) v.findViewById(R.id.dname);
        TextView dir = (TextView) v.findViewById(R.id.tdir);
        TextView tel = (TextView) v.findViewById(R.id.ttel);
        TextView music = (TextView) v.findViewById(R.id.tmusic);
        TextView price = (TextView) v.findViewById(R.id.tprice);

        // Populate the data into the template view using the data object
        name.setText(itemDiscotecas.getName());
        dir.setText(itemDiscotecas.getDir());
        tel.setText(itemDiscotecas.getTel());
        music.setText(itemDiscotecas.getMusic());
        price.setText(itemDiscotecas.getPrice());
        if (itemDiscotecas.getImage() != null) {
            image.setImageBitmap(itemDiscotecas.getImage());
        }
        return v;
    }
}
