package co.edu.eafit.chefcito;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Recetas extends Activity {

	private ListView recetas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recetas);

		final String[] datos =
			    new String[]{"Lomitos de Atún con limón y Anís",
				"Tortas de Lentejas",
				"Arroz con Verduras",
				"Bacalao a la Italiana",
				"Filete de Pescado Gratinado"};
			 
			ArrayAdapter<String> adaptador =
			    new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, datos);
			 
			recetas = (ListView)findViewById(R.id.recetas);
			 
			recetas.setAdapter(adaptador);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recetas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
