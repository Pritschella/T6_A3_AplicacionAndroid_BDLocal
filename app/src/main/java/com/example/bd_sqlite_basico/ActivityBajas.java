package com.example.bd_sqlite_basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controlador.AlumnoDAO;

public class ActivityBajas extends Activity {
    EditText txtnumControl;
    Button btnBaja;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        btnBaja = findViewById(R.id.btn_Eliminar);
        txtnumControl=findViewById(R.id.cajaid_bajas);
    }

    public void eliminarAlumno(View v){
        final AlumnoDAO aa = new AlumnoDAO(this);
        if(aa.eliminarAlumno(txtnumControl.getText().toString())){
            Toast toast = Toast.makeText(ActivityBajas.this, " Se elimino Alumno", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Toast toast = Toast.makeText(ActivityBajas.this, " No se pudo eliminar ese usuario", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
