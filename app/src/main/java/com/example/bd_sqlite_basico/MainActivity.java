package com.example.bd_sqlite_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import controlador.UsuarioDAO;
import modelo.Usuario;

public class MainActivity extends AppCompatActivity {
EditText usuario,contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario=findViewById(R.id.caja_usuario);
        contraseña=findViewById(R.id.caja_contraseña);
    }

    public void abrirRegistro(View v) {

        Intent i = new Intent(this, ActivityRegistro.class);
        startActivity(i);
    }
    public void iniciar(View v) {
        if(usuario.getText().toString().trim().equals("")||contraseña.getText().toString().trim().equals("")){
            Toast toast = Toast.makeText(MainActivity.this, " No deje espacios en blanco", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            final UsuarioDAO aa = new UsuarioDAO(this);
            Usuario usuario2 = aa.obtenerUsuario(usuario.getText().toString(),contraseña.getText().toString());
            if (usuario2 == null) {
                Toast toast = Toast.makeText(MainActivity.this, " Usuario o contraseña incorrecto", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Intent i = new Intent(this,ActivityMenu.class);
                startActivity(i);
            }
        }





    }



}
