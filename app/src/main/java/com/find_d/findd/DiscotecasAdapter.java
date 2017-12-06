package com.find_d.findd;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DiscotecasAdapter extends RecyclerView.Adapter<DiscotecasAdapter.SideItemsViewHolder> {
    private List<Discotecas> items;

    static class SideItemsViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        ImageView image;
        TextView name;
        TextView dir;
        TextView tel;
        TextView music;
        TextView price;


        SideItemsViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.imbar);
            name = (TextView) v.findViewById(R.id.dname);
            dir = (TextView) v.findViewById(R.id.tdir);
            tel = (TextView) v.findViewById(R.id.ttel);
            music = (TextView) v.findViewById(R.id.tmusic);
            price = (TextView) v.findViewById(R.id.tprice);
        }
    }

    DiscotecasAdapter(List<Discotecas> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SideItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.disco_details, viewGroup, false);
        return new SideItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SideItemsViewHolder viewHolder, int i) {
        viewHolder.name.setText(items.get(i).getName());
        viewHolder.dir.setText(items.get(i).getDir());
        viewHolder.tel.setText(items.get(i).getTel());
        viewHolder.music.setText(items.get(i).getMusic());
        viewHolder.price.setText(items.get(i).getPrice());
        FetchImage fetchImage = new FetchImage(viewHolder.image.getContext(), new FetchImage.AsyncResponse() {
            @Override
            public void processFinish(Bitmap bitmap) {
                if (bitmap != null) {
                    Resources res = viewHolder.image.getContext().getResources();
                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                            .create(res, bitmap);
                    roundBitmap.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
                    viewHolder.image.setImageDrawable(roundBitmap);
                }
            }
        });
        fetchImage.execute(items.get(i).getImageURL());
    }

}
