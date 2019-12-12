package com.example.bd_sqlite_basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import controlador.AlumnoDAO;
import modelo.Alumno;

public class ActivityAltas extends Activity {

    EditText txtnumControl, txtNombre, txtPrimerAp, txtSegundoAp, txtEdad, txtSemestre, txtCarrera;
    Button Agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);
        Agregar = findViewById(R.id.btn_agregar);

        txtnumControl=findViewById(R.id.cajaId_alta);
        txtNombre=findViewById(R.id.cajaNombre_alta);
        txtPrimerAp=findViewById(R.id.cajaPrimerAp_alta);
        txtSegundoAp=findViewById(R.id.cajaSegundoAp_alta);
        txtEdad=findViewById(R.id.cajaEdad_alta);
        txtSemestre=findViewById(R.id.cajaSemestre_alta);
        txtCarrera=findViewById(R.id.cajacarrera_alta);
    }

    public void Onclick(View v){

        if((txtnumControl.getText().toString().trim().equals("")  )||
                (txtNombre.getText().toString().trim().equals("")) ||
                (txtPrimerAp.getText().toString().trim().equals("")) ||
                (txtSegundoAp.getText().toString().trim().equals("")) ||
                (txtEdad.getText().toString().trim().equals("")) ||
                (txtSemestre.getText().toString().trim().equals("")) ||
                (txtCarrera.getText().toString().trim().equals(""))){
            Toast toast = Toast.makeText(ActivityAltas.this, "No deje espacios vacios", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            if((isNumeric(txtEdad.getText().toString())) && isNumeric(txtSemestre.getText().toString())){
                final AlumnoDAO aa = new AlumnoDAO(this);

                Alumno a = new Alumno();
                a.setNumControl(txtnumControl.getText().toString());
                a.setNombre(txtNombre.getText().toString());
                a.setPrimerAp(txtPrimerAp.getText().toString());
                a.setSegundoAp(txtSegundoAp.getText().toString());
                a.setEdad((Byte.valueOf(txtEdad.getText().toString())));
                a.setSemestre(Byte.valueOf(txtSemestre.getText().toString()));
                a.setCarrera(txtCarrera.getText().toString());
                if (aa.agregarAlumno(a)) {
                    Toast toast = Toast.makeText(ActivityAltas.this, " Se agrego Alumno", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(ActivityAltas.this, " No se pudo agregar Alumno", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }else{
                Toast toast = Toast.makeText(ActivityAltas.this, " La edad y el semestre tiene que ser numerico", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    public void limpiar(View v){
        txtnumControl.setText("");
        txtNombre.setText("");
        txtPrimerAp.setText("");
        txtSegundoAp.setText("");
        txtEdad.setText("");
        txtSemestre.setText("");
        txtCarrera.setText("");
    }

    }
