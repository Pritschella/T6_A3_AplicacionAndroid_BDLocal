package com.example.bd_sqlite_basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controlador.AlumnoDAO;
import modelo.Alumno;

public class ActivityCambios extends Activity {
    EditText txtnumControl, txtNombre, txtPrimerAp, txtSegundoAp, txtEdad, txtSemestre, txtCarrera;
    Button buscar,modificar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);
        buscar = findViewById(R.id.btnBuscarModificacion);
        modificar=findViewById(R.id.btn_modificar);

        txtnumControl=findViewById(R.id.cajaId_cambio);
        txtNombre=findViewById(R.id.cajaNombre_Cambio);
        txtPrimerAp=findViewById(R.id.cajaPrimerAp_cambio);
        txtSegundoAp=findViewById(R.id.cajaSegundoAp_cambio);
        txtEdad=findViewById(R.id.cajaEdad_cambio);
        txtSemestre=findViewById(R.id.cajaSemestre_cambio);
        txtCarrera=findViewById(R.id.cajaCarrera_cambio);
    }


    public void buscarM(View v){
        final AlumnoDAO aa = new AlumnoDAO(this);
        String filtro=txtnumControl.getText().toString();
        Alumno a =aa.obtenerAlumno(filtro);
        if(a==null){
            Toast toast = Toast.makeText(ActivityCambios.this, "Ese alumno no existe", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            txtNombre.setText(a.getNombre());
            txtPrimerAp.setText(a.getPrimerAp());
            txtSegundoAp.setText(a.getSegundoAp());
            txtEdad.setText(String.valueOf(a.getEdad()));
            txtSemestre.setText(String.valueOf(a.getSemestre()));
            txtCarrera.setText(a.getCarrera());
        }
    }

    public void modificar(View v) {
        if ((txtnumControl.getText().toString().trim().equals("")) ||
                (txtNombre.getText().toString().trim().equals("")) ||
                (txtPrimerAp.getText().toString().trim().equals("")) ||
                (txtSegundoAp.getText().toString().trim().equals("")) ||
                (txtEdad.getText().toString().trim().equals("")) ||
                (txtSemestre.getText().toString().trim().equals("")) ||
                (txtCarrera.getText().toString().trim().equals(""))) {
            Toast toast = Toast.makeText(ActivityCambios.this, "No deje espacios vacios", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if ((isNumeric(txtEdad.getText().toString())) && isNumeric(txtSemestre.getText().toString())) {

                final AlumnoDAO aa = new AlumnoDAO(this);
                boolean res = aa.modificarAlumno(txtnumControl.getText().toString(), txtNombre.getText().toString(),
                        txtPrimerAp.getText().toString(), txtSegundoAp.getText().toString(),
                        (byte) Integer.parseInt(txtEdad.getText().toString()),
                        (byte) Integer.parseInt(txtSemestre.getText().toString()),
                        txtCarrera.getText().toString());
                if (res) {
                    Toast toast = Toast.makeText(ActivityCambios.this, "Se actualizo Alumno", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(ActivityCambios.this, "No se pudo actualizar Alumno", Toast.LENGTH_SHORT);
                    toast.show();
                }
             }else{
            Toast toast = Toast.makeText(ActivityCambios.this, " La edad y el semestre tiene que ser numerico", Toast.LENGTH_SHORT);
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

    public void limpiarC(View v){
        txtnumControl.setText("");
        txtNombre.setText("");
        txtPrimerAp.setText("");
        txtSegundoAp.setText("");
        txtEdad.setText("");
        txtSemestre.setText("");
        txtCarrera.setText("");
    }
}
