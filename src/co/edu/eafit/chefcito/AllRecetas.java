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
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AllRecetas extends Activity implements
AdapterView.OnItemClickListener{
	private DrawerLayout mDrawer;
	private ListView mDrawerOptions;
	private String[] mTitles;
	private ActionBarDrawerToggle mDrawerToggle;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_recetas);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerOptions = (ListView) findViewById(R.id.left_drawer2);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout2);

		mDrawerOptions.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mTitles));
		mDrawerOptions.setOnItemClickListener(this);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawer.setDrawerListener(mDrawerToggle);

		Thread tr = new Thread() {
			@Override
			public void run() {
				final String Resultado = leer();
				runOnUiThread(new Runnable() {

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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.all_recetas, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void cargaListado(final ArrayList<Item> datos) {

		final ListView listado = (ListView) findViewById(R.id.allRecetas);
		AdapterItem adapter = new AdapterItem(this, datos);
		listado.setAdapter(adapter);

		listado.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// IdReceta = datos.get(position).getIdreceta();

				Log.i("ID", datos.get(position).getIdreceta());

				Intent mainIntent = new Intent().setClass(AllRecetas.this,
						VistaReceta.class);
				mainIntent.putExtra("idReceta", datos.get(position)
						.getIdreceta());
				startActivity(mainIntent);

			}
		});

	}

	public String leer() {

		String complemento = "?con=3";
		String URL = getString(R.string.sURL);
		// String URL = "http://chefcito.hol.es/GetData.php";

		HttpClient cliente = new DefaultHttpClient();
		HttpContext contexto = new BasicHttpContext();
		HttpGet httpget = new HttpGet(URL + complemento);
		Log.i("URL", URL + complemento);

		String resultado = null;
		try {
			HttpResponse response = cliente.execute(httpget, contexto);
			HttpEntity entity = response.getEntity();
			resultado = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		}
		return resultado;
	}

	public ArrayList<Item> obtDatosJSON(String response) {
		ArrayList<Item> listado = new ArrayList<Item>();
		Item item;
		try {
			JSONArray json = new JSONArray(response);
			String idReceta;
			String nombre;
			String descaripcion;
			for (int i = 0; i < json.length(); i++) {
				idReceta = json.getJSONObject(i).getString("id_receta");
				nombre = json.getJSONObject(i).getString("nombre");
				descaripcion = json.getJSONObject(i).getString("descripcion");
				item = new Item(idReceta, nombre, descaripcion, null, null,
						null, null, null, null);
				listado.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listado;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)
				|| item.getItemId() == android.R.id.home) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		switch (i) {
		case 0:
			Intent mainIntent0 = new Intent().setClass(AllRecetas.this,
					MainActivity.class);
			startActivity(mainIntent0);
			break;
		case 2:
			Intent mainIntent1 = new Intent().setClass(AllRecetas.this,
					Favoritos.class);
			startActivity(mainIntent1);
			break;		
		}		
		mDrawer.closeDrawers();
		finish();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
