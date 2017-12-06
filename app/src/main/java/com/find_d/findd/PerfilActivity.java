package com.find_d.findd;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;

public class PerfilActivity extends DrawerActivity {

    private TextView tNombre, tContrasena;
    private CircularImageView imgProfile;
    String sname=null,semail=null,sid=null;
    Uri photoUrl=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tNombre = findViewById(R.id.tNombre);
        tContrasena = findViewById(R.id.tContrasena);
        imgProfile = findViewById(R.id.profileImg);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            sname = user.getDisplayName();
            semail = user.getEmail();
            sid = user.getUid();
            photoUrl = user.getPhotoUrl();
        }else {
            logOut();
        }
        tNombre.setText(sname);   // User name
        tContrasena.setText(semail);  // User email

        FetchImage fetchImage = new FetchImage(this, new FetchImage.AsyncResponse() {
            @Override
            public void processFinish(Bitmap bitmap) {
                if (bitmap != null) {
                    imgProfile.setImageBitmap(bitmap);
                }
            }
        });
        fetchImage.execute(photoUrl.toString());

        // Nombre y color Toolbar
        getSupportActionBar().setTitle("Perfil");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
    }

    private void logOut() {
        Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
