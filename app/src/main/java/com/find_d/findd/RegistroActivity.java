package com.find_d.findd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {


    private String correo, contrasena, contrasenaR, nombre, apellido, usuario;
    EditText eCorreo, eNombre,  esureName, eUsuario, eContrasena, eRepcontrasena;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    User user_class;

    private FirebaseAuth mAuth;

    DatabaseReference myRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        eNombre = (EditText) findViewById(R.id.eNombre);
        esureName = (EditText) findViewById(R.id.eApellido);
        eUsuario = (EditText) findViewById(R.id.eUsuario);
        eCorreo = (EditText) findViewById(R.id.eCorreo);
        eContrasena = (EditText) findViewById(R.id.eContrasena);
        eRepcontrasena = (EditText) findViewById(R.id.eRepcontrasena);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void registrar(View view) {

        correo = eCorreo.getText().toString();
        contrasena = eContrasena.getText().toString();
        contrasenaR = eRepcontrasena.getText().toString();
        nombre = eNombre.getText().toString();
        apellido = esureName.getText().toString();
        usuario = eUsuario.getText().toString();


        if (correo.equals("") || contrasena.equals("") || usuario.equals("")
                ||nombre.equals("") || apellido.equals("")){
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }

        else if( !contrasena.equals(contrasenaR)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
        else if (contrasena.length() < 6){
            Toast.makeText(this, "La contraseña debe contener mínimo 6 caracteres", Toast.LENGTH_SHORT).show();
        }
        else if (!validarEmail(correo)) {

            Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show();

        }else {

            prefs.edit().putString("correo", correo).apply();
            prefs.edit().putString("contrasena", contrasena).apply();
            setResult(RESULT_OK);
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();


            final Intent intent = new Intent();
            intent.putExtra("correo", correo);
            intent.putExtra("contrasena", contrasena);

            mAuth.createUserWithEmailAndPassword(correo, contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user, intent);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(RegistroActivity.this, "Ya existe el usuario",Toast.LENGTH_SHORT).show();
                                updateUI(null, intent);
                            }

                            // ...
                        }
                    });



        }





    }

    private void updateUI(FirebaseUser user, final Intent intent) {
        if (user != null){
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nombre+apellido)
                    .build();
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }
        else {
            Toast.makeText(RegistroActivity.this, "Falló el registro.", Toast.LENGTH_SHORT).show();
        }
    }

    private void crear_tabla(FirebaseUser fuser){
        String uid = fuser.getUid();

        editor.putString("uid", uid);
        editor.apply();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(uid);
        user_class = new User(nombre, apellido, usuario, correo);
        myRef.setValue(user_class);

    }


}



