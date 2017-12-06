package com.find_d.findd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {


    private String correo, contrasena, contrasenaR;
    EditText eCorreo, eContrasena, eRepcontrasena;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        eCorreo = (EditText) findViewById(R.id.eCorreo);
        eContrasena = (EditText) findViewById(R.id.eContrasena);
        eRepcontrasena = (EditText) findViewById(R.id.eRepcontrasena);



    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void registrar(View view) {
        correo = eCorreo.getText().toString();
        contrasena = eContrasena.getText().toString();
        contrasenaR = eRepcontrasena.getText().toString();


        if (correo.equals("") || contrasena.equals("")){
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }

        else if( !contrasena.equals(contrasenaR)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

        else if (!validarEmail(correo)) {

            Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show();

        }else{

            prefs.edit().putString("correo", correo).apply();
            prefs.edit().putString("contrasena", contrasena).apply();
            setResult(RESULT_OK);
            finish();
        }


    }


}



