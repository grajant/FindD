package com.find_d.findd;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class DiscoProfileActivity extends AppCompatActivity {

    TextView tInfo;
    RatingBar rEstrellas;
    ImageView iFoto;
    TextView tDiscoDescripcion;
    Discotecas discoteca;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco_profile);

        /** Establece la toolbar con flecha hacia atrás **/
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        tInfo = (TextView) findViewById(R.id.tInfod);
        rEstrellas= (RatingBar) findViewById(R.id.rating_bar_profile);
        iFoto = (ImageView) findViewById(R.id.iFotodisco);
        tDiscoDescripcion = (TextView) findViewById(R.id.tDescripcion);

        discoteca = (Discotecas)getIntent().getSerializableExtra(Tags.TAG_DISCO);

        tInfo.setText(discoteca.getName()+"\n"+discoteca.getPrice());


        //final String[] textFields = new String[5];


        //Bundle extras = getIntent().getExtras();
        //final String data = extras.getString("marker_data");
        //String []fields = data.split("\n");


        tInfo.setText(discoteca.getName()+"\n"+discoteca.getPrice());
        rEstrellas.setRating((float) 4);
        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
            @Override
            public void processFinish(Bitmap bitmap) {
                if (bitmap != null) {
                    Resources res = getApplicationContext().getResources();
                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                            .create(res, bitmap);
                    //roundBitmap.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
                    iFoto.setImageDrawable(roundBitmap);
                }
            }
        });
        fetchImage.execute(discoteca.getImageURL());

        tDiscoDescripcion.setText("Discoteca ubicada en la "+ discoteca.getDir() +". \nTipo de música: " + discoteca.getMusic() +".\nContacto: "+ discoteca.getTel()+".");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(DiscoProfileActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
