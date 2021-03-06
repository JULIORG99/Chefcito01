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
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Recetas extends Activity implements
		AdapterView.OnItemClickListener {

	private DataBaseManager manager;
	private DrawerLayout mDrawer;
	private ListView mDrawerOptions;
	private String[] mTitles;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recetas);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerOptions = (ListView) findViewById(R.id.left_drawer3);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
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
		inflater.inflate(R.menu.recetas, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void cargaListado(final ArrayList<Item> datos) {

		final ListView listado = (ListView) findViewById(R.id.recetas);
		AdapterItem adapter = new AdapterItem(this, datos);
		listado.setAdapter(adapter);

		listado.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// IdReceta = datos.get(position).getIdreceta();

				Log.i("ID", datos.get(position).getIdreceta());

				Intent mainIntent = new Intent().setClass(Recetas.this,
						VistaReceta.class);
				mainIntent.putExtra("idReceta", datos.get(position)
						.getIdreceta());
				startActivity(mainIntent);

			}
		});

	}

	public String leer() {

		manager = new DataBaseManager(this);
		Cursor c = manager.cargarCursorMisIngredientes();
		int nombreColumn = c.getColumnIndex(manager.CN_ID);
		// Recorremos el cursor
		final ArrayList<String> misingredientes = new ArrayList<String>();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			String name = c.getString(nombreColumn);
			misingredientes.add(name.replace(" ", "%20"));
		}
		while (misingredientes.size() < 10) {
			misingredientes.add(misingredientes.get(0));
		}
		String complemento = "?con=1&ing1=" + misingredientes.get(0) + "&ing2="
				+ misingredientes.get(1) + "&ing3=" + misingredientes.get(2)
				+ "&ing4=" + misingredientes.get(3) + "&ing5="
				+ misingredientes.get(4) + "&ing6=" + misingredientes.get(5)
				+ "&ing7=" + misingredientes.get(6) + "&ing8="
				+ misingredientes.get(7) + "&ing9=" + misingredientes.get(8)
				+ "&ing10=" + misingredientes.get(9);
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

	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		switch (i) {
		case 0:
			Intent mainIntent0 = new Intent().setClass(Recetas.this,
					MainActivity.class);
			startActivity(mainIntent0);
			break;
		case 1:
			Intent mainIntent1 = new Intent().setClass(Recetas.this,
					AllRecetas.class);
			startActivity(mainIntent1);
			break;
		case 2:
			Intent mainIntent2 = new Intent().setClass(Recetas.this,
					Favoritos.class);
			startActivity(mainIntent2);
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
