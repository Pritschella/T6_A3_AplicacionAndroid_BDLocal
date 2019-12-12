package controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Gravity;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import modelo.Alumno;

public class AlumnoDAO extends SQLiteOpenHelper {

    //Constantes que definirán la estructura de la BD

    private static final int VERSION_BD = 1;

    private static final String NOMBRE_BD = "Escuela";

    private static final String TABLA_ALUMNOS = "Alumnos";

    private static final String CAMPO_NUM_CONTROL = "Num_Control";
    private static final String CAMPO_NOMBRE = "Nombre";
    private static final String CAMPO_PRIMER_AP = "Primer_Ap";
    private static final String CAMPO_SEGUNDO_AP = "Segundo_Ap";
    private static final String CAMPO_EDAD = "Edad";
    private static final String CAMPO_SEMESTRE = "Semestre";
    private static final String CAMPO_CARRERA = "Carrera";

    //CREATE TABLE Alumnos(Num_Control TEXT

    public static final String CREACION_TABLA_ALUMNOS = "CREATE TABLE "+ TABLA_ALUMNOS+"("+CAMPO_NUM_CONTROL+" TEXT, "+CAMPO_NOMBRE+" TEXT, "+
            CAMPO_PRIMER_AP+" TEXT, "+CAMPO_SEGUNDO_AP+" TEXT, "+CAMPO_EDAD+" INTEGER, "+CAMPO_SEMESTRE+" INTEGER, "+CAMPO_CARRERA+" TEXT);";


    public AlumnoDAO(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //para actualizaar ESQUEMA de la BD
    }

    //---------------------------- METODOS para ABCC ------------------------------
    public boolean agregarAlumno (Alumno a){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NUM_CONTROL, a.getNumControl());
        cv.put(CAMPO_NOMBRE, a.getNombre());
        cv.put(CAMPO_PRIMER_AP, a.getPrimerAp());
        cv.put(CAMPO_SEGUNDO_AP, a.getSegundoAp());
        cv.put(CAMPO_EDAD, a.getEdad());
        cv.put(CAMPO_SEMESTRE, a.getSemestre());
        cv.put(CAMPO_CARRERA, a.getCarrera());

        long res = db.insert(TABLA_ALUMNOS, null, cv);

        return (res==-1) ? false : true;
    }

    public boolean eliminarAlumno (String nc){

        SQLiteDatabase db = this.getWritableDatabase();
        int res= db.delete(TABLA_ALUMNOS,CAMPO_NUM_CONTROL+"='"+nc+"'",null);
        return (res==-1) ? false : true;

    }

    public boolean modificarAlumno (String  nc,String nombre,String primerAp, String segundoAp, byte edad,byte semestre,String carrera){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, nombre);
        cv.put(CAMPO_PRIMER_AP, primerAp);
        cv.put(CAMPO_SEGUNDO_AP, segundoAp);
        cv.put(CAMPO_EDAD, edad);
        cv.put(CAMPO_SEMESTRE, semestre);
        cv.put(CAMPO_CARRERA, carrera);

        long res=db.update(TABLA_ALUMNOS, cv, CAMPO_NUM_CONTROL+"='"+nc+"'", null);

        return (res==-1) ? false : true;
    }

    public Alumno obtenerAlumno(String filtro){
        Alumno  alumno;
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="SELECT * FROM "+TABLA_ALUMNOS+ " WHERE "+CAMPO_NUM_CONTROL+"= '"+filtro+"'";

        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
                alumno= new Alumno(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        (byte)cursor.getInt(4),
                        (byte)cursor.getInt(5),
                        cursor.getString(6));
            return alumno;
        }else{
            return  null;
        }
    }


    public ArrayList<Alumno>obtenerTodosLosAlumnos(String filtro,String campo){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="SELECT * FROM "+TABLA_ALUMNOS+ " WHERE "+filtro+"= '"+campo+"'";

        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){

                listaAlumnos.add(new Alumno(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                (byte)cursor.getInt(4),
                                (byte)cursor.getInt(5),
                                cursor.getString(6)));


        }
        return listaAlumnos;
    }
}
