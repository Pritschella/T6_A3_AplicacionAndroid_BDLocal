package controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import modelo.Alumno;
import modelo.Usuario;

public class UsuarioDAO extends SQLiteOpenHelper {
    //Constantes que definirán la estructura de la BD

    private static final int VERSION_BD = 1;

    private static final String NOMBRE_BD = "Escuela";

    private static final String TABLA_USUARIO = "Usuario";

    private static final String CAMPO_USUARIO = "Usuario";
    private static final String CAMPO_CONTRASEÑA = "Contraseña";



    private static final String CREACION_TABLA_USUARIO = "CREATE TABLE "+ TABLA_USUARIO+"("+CAMPO_USUARIO+" TEXT PRIMARY KEY, "+CAMPO_CONTRASEÑA+" TEXT);";


    public UsuarioDAO(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREACION_TABLA_USUARIO);
        db.execSQL(AlumnoDAO.CREACION_TABLA_ALUMNOS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //para actualizaar ESQUEMA de la BD

    }

    //---------------------------- METODOS para ABCC ------------------------------
    public boolean agregarUsuario (Usuario a){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_USUARIO, a.getUsuario());
        cv.put(CAMPO_CONTRASEÑA, a.getContrasena());

        long res = db.insert(TABLA_USUARIO, null, cv);

        return (res==-1) ? false : true;
    }



    public Usuario obtenerUsuario(String user, String contraseña){
            Usuario usuario;
            SQLiteDatabase db= this.getWritableDatabase();
            String sql="SELECT * FROM "+TABLA_USUARIO+ " WHERE "+CAMPO_USUARIO+"= '"+user+"' AND "+CAMPO_CONTRASEÑA+"='"+contraseña+"';";

            Cursor cursor = db.rawQuery(sql,null);
            if(cursor.moveToFirst()){
                usuario= new Usuario(
                        cursor.getString(0),
                        cursor.getString(1));
                return usuario;
            }else{
                return  null;
            }
    }


    public Usuario obtenerUsuarioRepetido(String user){
        Usuario usuario;
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="SELECT * FROM "+TABLA_USUARIO+ " WHERE "+CAMPO_USUARIO+"= '"+user+"';";

        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            usuario= new Usuario(
                    cursor.getString(0),
                    cursor.getString(1));
            return usuario;
        }else{
            return  null;
        }
    }



}
