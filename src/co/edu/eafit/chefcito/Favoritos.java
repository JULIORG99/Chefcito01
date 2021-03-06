package co.edu.eafit.chefcito;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Favoritos extends Activity implements
		AdapterView.OnItemClickListener {
	
	private DataBaseManager manager;
	private Cursor cursor;	
	private ListView lista;	
	private SimpleCursorAdapter adapter;
	
	
	private DrawerLayout mDrawer;
	private ListView mDrawerOptions;
	private String[] mTitles;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritos);
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
		
		manager = new DataBaseManager(this);
		lista = (ListView) findViewById(R.id.listViewFavoritos);
		final String[] from = new String[] { manager.CN_NAME, manager.CN_ID };
		final int[] to = new int[] { android.R.id.text1, android.R.id.text2};
		cursor = manager.cargarCursorFavoritos();
		adapter = new SimpleCursorAdapter(this,
				android.R.layout.two_line_list_item, cursor, from, to, 0);
		lista.setAdapter(adapter);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String _id = String.valueOf(id);
				Intent mainIntent = new Intent().setClass(Favoritos.this,
						VistaReceta.class);
				mainIntent.putExtra("idReceta", _id);
				startActivity(mainIntent);
				finish();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.favoritos, menu);
		return super.onCreateOptionsMenu(menu);
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
			Intent mainIntent0 = new Intent().setClass(Favoritos.this,
					MainActivity.class);
			startActivity(mainIntent0);
			break;
		case 1:
			Intent mainIntent1 = new Intent().setClass(Favoritos.this,
					AllRecetas.class);
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
