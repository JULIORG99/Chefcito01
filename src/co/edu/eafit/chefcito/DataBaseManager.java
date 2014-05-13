package co.edu.eafit.chefcito;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
	public static final String TABLE_NAME = "ingredientes";
	public static final String TABLE_NAME2 = "mis_ingredientes";
	
	public static final String CN_ID = "_id";
	public static final String CN_NAME = "nombre";
	
	public static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ " ("
			+ CN_ID + " INTEGER PRIMARY KEY NOT NULL,"
			+ CN_NAME + " TEXT NOT NULL);";
	
	public static final String CREATE_TABLE2 = "CREATE TABLE " +TABLE_NAME2+ " ("
			+ CN_ID + " INTEGER PRIMARY KEY NOT NULL,"
			+ CN_NAME + " TEXT NOT NULL);";
	
	

	private DbHelper helper;
	private SQLiteDatabase db;	
	
	public DataBaseManager(Context context) {
		 helper = new DbHelper(context);
		 db = helper.getWritableDatabase();
	}
	

	public ContentValues generarContentValues(String _id,String nombre){
		ContentValues valores = new ContentValues();
		valores.put(CN_ID, _id);
		valores.put(CN_NAME, nombre);		
		return valores;		
	}
	//Tabla Mis ingredientes
	public void instertarMisIngredients(String _id,String nombre){
		db.insert(TABLE_NAME2, null, generarContentValues(_id,nombre));
	}
	public Cursor cargarCursorMisIngredientes(){
		String[] columnas = new String[]{CN_ID,CN_NAME};
		return db.query(TABLE_NAME2, columnas, null, null, null, null, null);
	}	
	
	public void eliminarMisIngredientes(String nombre) {
        //bd.delete (Tabla, Claúsula Where, Argumentos Where)
        db.delete(TABLE_NAME2, CN_NAME + "=?", new String[]{nombre});
    }
	
	public void eliminarTablaMisIngredientes(){
		db.delete("mis_ingredientes", null, null);
	}	
	
	//Tabla ingredientes
	public void instertar(String _id,String nombre){
		db.insert(TABLE_NAME, null, generarContentValues(_id,nombre));
	}
	
	public Cursor cargarCursor(){
		String[] columnas = new String[]{CN_ID,CN_NAME};
		return db.query(TABLE_NAME, columnas, null, null, null, null, null);
	}	
	public Cursor buscarIngrediente(String nombre){
		String[] columnas = new String[]{CN_ID,CN_NAME};	
		
		return db.query(TABLE_NAME, columnas, CN_NAME + " like ?", new String[]{nombre+"%"}, null, null, null);
	}
	
}
