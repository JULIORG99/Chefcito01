package co.edu.eafit.chefcito;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
	public static final String TABLE_NAME = "ingredientes";
	
	public static final String CN_ID = "_id";
	public static final String CN_NAME = "nombre";
	
	public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
			+ CN_ID + " integer primary key autoincrement,"
			+ CN_NAME + " text not null);";
	

	private DbHelper helper;
	private SQLiteDatabase db;	
	
	public DataBaseManager(Context context) {
		 helper = new DbHelper(context);
		 db = helper.getWritableDatabase();
	}
	

	public ContentValues generarContentValues(String nombre){
		ContentValues valores = new ContentValues();
		valores.put(CN_NAME, nombre);		
		return valores;		
	}
	
	public void instertar(String nombre){
		db.insert(TABLE_NAME, null, generarContentValues(nombre));
	}
	
	public Cursor cargarCursor(){
		String[] columnas = new String[]{CN_ID,CN_NAME};
		return db.query(TABLE_NAME, columnas, null, null, null, null, null);
	}
	public Cursor buscarIngrediente(String nombre){
		String[] columnas = new String[]{CN_ID,CN_NAME};
		return db.query(TABLE_NAME, columnas, CN_NAME + "=?", new String[]{nombre}, null, null, null);
	}
}
