package com.find_d.findd;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DiscoProfileActivity extends AppCompatActivity {

    TextView tInfo;
    RatingBar rEstrellas;
    ImageView iFoto;
    TextView tDiscoDescripcion;
    Discotecas discoteca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco_profile);

        tInfo = (TextView) findViewById(R.id.tInfod);
        rEstrellas= (RatingBar) findViewById(R.id.rating_bar_profile);
        iFoto = (ImageView) findViewById(R.id.iFotodisco);
        tDiscoDescripcion = (TextView) findViewById(R.id.tDescripcion);

        discoteca = (Discotecas)getIntent().getSerializableExtra("marker_data");

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
}
