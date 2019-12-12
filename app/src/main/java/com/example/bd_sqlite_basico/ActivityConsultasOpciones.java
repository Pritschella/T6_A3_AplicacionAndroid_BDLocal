package com.example.bd_sqlite_basico;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityConsultasOpciones extends AppCompatActivity {
Spinner parametro;
EditText campo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_opciones);
        parametro=findViewById(R.id.spinner_parametro);
        campo=findViewById(R.id.caja_busqueda);
    }

    public void abrirBusquedaC(View v){

        if(campo.getText().toString().trim().equals("")){
            Toast toast = Toast.makeText(ActivityConsultasOpciones.this, "No deje espacios vacios", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Intent i = new Intent(this, ActivityConsultas.class);
            if(parametro.getSelectedItemId()==0){
                i.putExtra("filtro","Num_Control");
            }else if(parametro.getSelectedItemId()==1){
                i.putExtra("filtro","Nombre");
            }else if(parametro.getSelectedItemId()==2){
                i.putExtra("filtro","Primer_Ap");
            }
            i.putExtra("dato",campo.getText().toString());
            startActivity(i);
        }
    }
}
