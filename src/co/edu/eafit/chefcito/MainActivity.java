package co.edu.eafit.chefcito;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		AdapterView.OnItemClickListener, OnClickListener {

	private DataBaseManager manager;
	private Cursor cursor;
	private Cursor cursor2;
	private ListView lista;
	private ListView milista;
	private SimpleCursorAdapter adapter;
	private SimpleCursorAdapter adapter2;
	private TextView tv;
	private ImageButton bt;

	private DrawerLayout mDrawer;
	private ListView mDrawerOptions;
	private String[] mTitles;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerOptions = (ListView) findViewById(R.id.left_drawer);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
		lista = (ListView) findViewById(R.id.listView_ingredientes);
		milista = (ListView) findViewById(R.id.listView2);
		tv = (TextView) findViewById(R.id.editText1);
		bt = (ImageButton) findViewById(R.id.imageButton1);
		bt.setOnClickListener(this);

		Resources res = getResources();

		TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
		tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Lista de Ingredientes",
				res.getDrawable(android.R.drawable.ic_btn_speak_now));
		tabs.addTab(spec);

		spec = tabs.newTabSpec("mitab2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Mis Ingredientes",
				res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);

		tabs.setCurrentTab(0);
		// Tabs

		final String[] from = new String[] { manager.CN_NAME };
		final int[] to = new int[] { android.R.id.text1 };
		cursor = manager.cargarCursor();
		adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, from, to, 0);
		lista.setAdapter(adapter);
		// genera Listview de ingredientes
		cursor2 = manager.cargarCursorMisIngredientes();
		adapter2 = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor2, from, to, 0);
		milista.setAdapter(adapter2);
		// genera Listview de misingredientes

		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String _id = String.valueOf(id);
				Toast.makeText(getApplicationContext(),
						"Agrego: " + ((TextView) view).getText(),
						Toast.LENGTH_SHORT).show();
				manager.instertarMisIngredients(_id, ((TextView) view)
						.getText().toString());
				Cursor c = manager.cargarCursorMisIngredientes();
				adapter2.changeCursor(c);
			}
		});

		milista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Toast.makeText(getApplicationContext(),
						"Elimino: " + ((TextView) view).getText(),
						Toast.LENGTH_SHORT).show();
				manager.eliminarMisIngredientes(((TextView) view).getText()
						.toString());
				Cursor c = manager.cargarCursorMisIngredientes();
				adapter2.changeCursor(c);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)
				|| item.getItemId() == android.R.id.home) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.menu_buscar:
			Cursor c = manager.cargarCursorMisIngredientes();
			if (c.moveToFirst() != false) {
				Intent mainIntent = new Intent().setClass(MainActivity.this,
						Recetas.class);
				startActivity(mainIntent);
			} else {
				Toast.makeText(getApplicationContext(),
						"No a agregado ningún ingrediente a su lista",
						Toast.LENGTH_SHORT).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		switch (i) {
		case 1:
			Intent mainIntent1 = new Intent().setClass(MainActivity.this,
					AllRecetas.class);
			startActivity(mainIntent1);
			break;
		case 2:
			Intent mainIntent2 = new Intent().setClass(MainActivity.this,
					Favoritos.class);
			// fav.fa.finish();
			startActivity(mainIntent2);
			break;
		}
		mDrawer.closeDrawers();
	}

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

	public void onClick(View view) {
		if (view.getId() == R.id.imageButton1) {
			Cursor c = manager.buscarIngrediente(tv.getText().toString());
			adapter.changeCursor(c);
		}
	}
}
