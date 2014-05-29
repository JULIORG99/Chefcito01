package co.edu.eafit.chefcito;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class VistaReceta extends Activity implements
		AdapterView.OnItemClickListener {

	private DataBaseManager manager;
	private String id_receta;
	private String nombre;
	private String dificultad;
	private String tiempo;
	private String preparacion;
	public String URLImagen;
	private ImageView imgImagen;
	private DrawerLayout mDrawer;
	private ListView mDrawerOptions;
	private String[] mTitles;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_receta);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerOptions = (ListView) findViewById(R.id.left_drawer4);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout4);

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
		manager = new DataBaseManager(this);

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

		Resources res = getResources();

		TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
		tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
		spec.setContent(R.id.tabPreparacion);
		spec.setIndicator("Preparacion",
				res.getDrawable(android.R.drawable.ic_btn_speak_now));
		tabs.addTab(spec);

		spec = tabs.newTabSpec("mitab2");
		spec.setContent(R.id.tabIngredientes);
		spec.setIndicator("Ingredientes",
				res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);

		tabs.setCurrentTab(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.vista_receta, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void cargaListado(final ArrayList<Item> datos) {

		TextView nombre_receta = (TextView) findViewById(R.id.nombreReceta);
		nombre_receta.setText(nombre);
		TextView preparacion_receta = (TextView) findViewById(R.id.textPreparacion);
		preparacion_receta.setText(preparacion);
		TextView Dificultad_receta = (TextView) findViewById(R.id.dificultad);
		Dificultad_receta.setText(dificultad);
		TextView Tiempo_receta = (TextView) findViewById(R.id.tiempo);
		Tiempo_receta.setText(tiempo + " min");

		imgImagen = (ImageView) findViewById(R.id.imagenReceta);

		final ListView listado = (ListView) findViewById(R.id.listIngredientes);
		AdapterItemIng adapter = new AdapterItemIng(this, datos);
		listado.setAdapter(adapter);

		CargaImagenes nuevaTarea = new CargaImagenes();
		nuevaTarea.execute(URLImagen);

	}

	public String leer() {

		Bundle datos = this.getIntent().getExtras();
		String idReceta = datos.getString("idReceta");

		// misingredientes.add(name.replace(" ", "%20"));
		String complemento = "?con=2&id_receta=" + idReceta;
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
		String nombreIng;
		String cantidad;
		try {
			JSONArray json = new JSONArray(response);

			for (int i = 0; i < json.length(); i++) {
				id_receta = json.getJSONObject(i).getString("id_receta");
				nombre = json.getJSONObject(i).getString("nombre");
				preparacion = json.getJSONObject(i).getString("preparacion");
				dificultad = json.getJSONObject(i).getString(
						"nombre_dificultad");
				tiempo = json.getJSONObject(i).getString("tiempo");
				nombreIng = json.getJSONObject(i).getString("nombreIng");
				cantidad = json.getJSONObject(i).getString("cantidad");
				URLImagen = getString(R.string.sURLimg)
						+ json.getJSONObject(i).getString("imagen");
				item = new Item(null, null, null, null, null, null, nombreIng,
						cantidad, null);
				listado.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listado;
	}

	private Bitmap descargarImagen(String imageHttpAddress) {
		URL imageUrl = null;
		Bitmap imagen = null;
		try {
			imageUrl = new URL(imageHttpAddress);
			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.connect();
			imagen = BitmapFactory.decodeStream(conn.getInputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return imagen;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (mDrawerToggle.onOptionsItemSelected(item)
				|| item.getItemId() == android.R.id.home) {
			return true;
		}

		if (mDrawerToggle.onOptionsItemSelected(item)
				|| item.getItemId() == android.R.id.home) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.menu_favorito:
			Cursor c = manager.buscarFavorito(id_receta);
			if (c.moveToFirst() != false) {				
				manager.eliminarFavorito(id_receta);
				Toast.makeText(getApplicationContext(), "Elimino da favoritos",
						Toast.LENGTH_SHORT).show();
			} else {
				manager.instertarFavoritos(id_receta, nombre);
				Toast.makeText(getApplicationContext(), "Agrego a favoritos ",
						Toast.LENGTH_SHORT).show();				
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}		
	}

	private class CargaImagenes extends AsyncTask<String, Void, Bitmap> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			pDialog = new ProgressDialog(VistaReceta.this);
			pDialog.setMessage("Cargando Imagen");
			pDialog.setCancelable(true);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.show();

		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Log.i("doInBackground", "Entra en doInBackground");
			String url = params[0];
			Bitmap imagen = descargarImagen(url);
			return imagen;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			imgImagen.setImageBitmap(result);
			pDialog.dismiss();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		switch (i) {
		case 0:
			Intent mainIntent0 = new Intent().setClass(VistaReceta.this,
					MainActivity.class);
			startActivity(mainIntent0);
			break;
		case 1:
			Intent mainIntent1 = new Intent().setClass(VistaReceta.this,
					AllRecetas.class);
			startActivity(mainIntent1);
			break;
		case 2:
			Intent mainIntent2 = new Intent().setClass(VistaReceta.this,
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
