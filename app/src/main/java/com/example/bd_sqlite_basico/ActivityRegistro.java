package com.example.bd_sqlite_basico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import controlador.AlumnoDAO;
import controlador.UsuarioDAO;
import modelo.Alumno;
import modelo.Usuario;

public class ActivityRegistro  extends AppCompatActivity {
EditText usuario, contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario=findViewById(R.id.agregar_usuario);
        contraseña=findViewById(R.id.agregar_Contraseña);
    }

    public void registrarUsuario(View v){
        if(usuario.getText().toString().trim().equals("")||contraseña.getText().toString().trim().equals("")){
            Toast toast = Toast.makeText(ActivityRegistro.this, " No deje espacios en blanco", Toast.LENGTH_SHORT);
            toast.show();
        }else {


            final UsuarioDAO aa = new UsuarioDAO(this);
            Usuario usuario2 = aa.obtenerUsuarioRepetido(usuario.getText().toString());
            if (usuario2 == null) {

                Usuario a = new Usuario();
                a.setUsuario(usuario.getText().toString());
                a.setContrasena(contraseña.getText().toString());

                if (aa.agregarUsuario(a)) {
                    Toast toast = Toast.makeText(ActivityRegistro.this, " Se agrego Usuario", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast toast = Toast.makeText(ActivityRegistro.this, " No se pudo agregar Usuario", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(ActivityRegistro.this, " Ese nombre de usuario no esta disponible", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }


}
