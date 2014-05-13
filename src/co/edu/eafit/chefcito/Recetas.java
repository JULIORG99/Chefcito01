package co.edu.eafit.chefcito;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Recetas extends Activity {
	
	private DataBaseManager manager;
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recetas);	
    	
    	Thread tr = new Thread(){
			@Override
			public void run(){
				final String Resultado = leer();
				runOnUiThread(
						new Runnable() {
				
							@Override
							public void run() {
									cargaListado(obtDatosJSON(Resultado));
							}
						});
			}			
		};
		tr.start();
		
    	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.recetas, menu);
		return true;
	}
	
	public void cargaListado(ArrayList<String> datos){
		ArrayAdapter<String> adaptador =
			new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
		ListView listado = (ListView) findViewById(R.id.recetas);
		listado.setAdapter(adaptador);
		
		
		listado.setOnItemClickListener(new OnItemClickListener() {			
			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String _id = String.valueOf(id);
				//String item = ((TextView)view).getText().toString();                
                Toast.makeText(getBaseContext(), _id, Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent().setClass(Recetas.this, VistaReceta.class);
                startActivity(mainIntent);
                finish();
			}			
		});
		
		
	}
	
	public String leer(){
		
		manager = new DataBaseManager(this);

		Cursor c = manager.cargarCursorMisIngredientes();		   		
    		
    	int nombreColumn = c.getColumnIndex(manager.CN_NAME);    			        		 
    	//Recorremos el cursor    		
    	final ArrayList<String> misingredientes = new ArrayList<String>();
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
    		String name = c.getString(nombreColumn);	        		  
    		misingredientes.add(name.replace(" ", "%20"));       
    		
    	}
		//String URL = "http://10.0.2.2/chefcito/GetData.php";
		String URL = "http://chefcito.hol.es/GetData.php";
    	HttpClient cliente =new DefaultHttpClient();
		HttpContext contexto = new BasicHttpContext();
		
		HttpGet	httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0));
		switch (misingredientes.size()){
    	case 1:
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0));
    		break;
    	case 2:
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1));
    		break;
    	case 3:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2));
    		break;
    	case 4:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2)+
    				"&ing4="+misingredientes.get(3));
    		break;
    	case 5:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2)+
    				"&ing4="+misingredientes.get(3)+
    				"&ing5="+misingredientes.get(4));	
    		break;
    	case 6:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2)+
    				"&ing4="+misingredientes.get(3)+
    				"&ing5="+misingredientes.get(4)+
    				"&ing6="+misingredientes.get(5));
    		break;
    	case 7:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2)+
    				"&ing4="+misingredientes.get(3)+
    				"&ing5="+misingredientes.get(4)+
    				"&ing6="+misingredientes.get(5)+
    				"&ing7="+misingredientes.get(6));
    		break;
    	case 8:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2)+
    				"&ing4="+misingredientes.get(3)+
    				"&ing5="+misingredientes.get(4)+
    				"&ing6="+misingredientes.get(5)+
    				"&ing7="+misingredientes.get(6)+
    				"&ing8="+misingredientes.get(7));
    		break;
    	case 9:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2)+
    				"&ing4="+misingredientes.get(3)+
    				"&ing5="+misingredientes.get(4)+
    				"&ing6="+misingredientes.get(5)+
    				"&ing7="+misingredientes.get(6)+
    				"&ing8="+misingredientes.get(7)+
    				"&ing9="+misingredientes.get(8));
    		break;
    	case 10:	
    		httpget = new HttpGet(URL+"?ing1="+misingredientes.get(0)+
    				"&ing2="+misingredientes.get(1)+
    				"&ing3="+misingredientes.get(2)+
    				"&ing4="+misingredientes.get(3)+
    				"&ing5="+misingredientes.get(4)+
    				"&ing6="+misingredientes.get(5)+
    				"&ing7="+misingredientes.get(6)+
    				"&ing8="+misingredientes.get(7)+
    				"&ing9="+misingredientes.get(8)+
    				"&ing10="+misingredientes.get(9));
    		break;
    	}    	
		
		String resultado=null;
		try {			
			HttpResponse response = cliente.execute(httpget,contexto);
			HttpEntity entity = response.getEntity();
			resultado = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(),
					"Error", Toast.LENGTH_SHORT).show();
		}
		return resultado;
	}
	
	public ArrayList<String> obtDatosJSON(String response){
		ArrayList<String> listado= new ArrayList<String>();
		try {
			JSONArray json= new JSONArray(response);
			String texto="";
			for (int i=0; i<json.length();i++){
				texto = json.getJSONObject(i).getString("nombre") +" - "+
						json.getJSONObject(i).getString("descripcion");
				listado.add(texto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listado;
	}

	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
