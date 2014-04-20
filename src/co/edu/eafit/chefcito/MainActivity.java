package co.edu.eafit.chefcito;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {	
	
	private DataBaseManager manager;
	private Cursor cursor;
	private Cursor cursor2;
	private TextView eti;
	private ListView lista;
	private ListView milista;
	private SimpleCursorAdapter adapter;
	private SimpleCursorAdapter adapter2;
	private TextView tv;
	private ImageButton bt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);				
		
		manager = new DataBaseManager(this);
		lista = (ListView)findViewById(R.id.listView1);
		milista = (ListView)findViewById(R.id.listView2);
		tv = (TextView) findViewById(R.id.editText1);		
		bt = (ImageButton) findViewById(R.id.imageButton1);
		bt.setOnClickListener(this);			
		
		Resources res = getResources();
		 
		TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
		tabs.setup();
		 
		TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Lista de Ingredientes",
				res.getDrawable(android.R.drawable.ic_btn_speak_now));
		tabs.addTab(spec);
		 
		spec=tabs.newTabSpec("mitab2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Mis Ingredientes",
				res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);
		 
		tabs.setCurrentTab(0);			
		//Tabs
		
		final String[] from = new String[]{manager.CN_NAME};
		final int[] to = new int[] {android.R.id.text1};		
		cursor = manager.cargarCursor();
		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, 
				cursor, from, to,0);		
		lista.setAdapter(adapter);		
		//genera Listview de ingredientes		
		cursor2 = manager.cargarCursorIngredientes();
		adapter2 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, 
				cursor2, from, to,0);		
		milista.setAdapter(adapter2);		
		//genera Listview de misingredientes		
		
		lista.setOnItemClickListener(new OnItemClickListener() {			
			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String _id = String.valueOf(id);
				Toast.makeText(getApplicationContext(),
						"Agrego: "+((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				manager.instertarIngredients(_id,((TextView) view).getText().toString());
				Cursor c = manager.cargarCursorIngredientes();
				adapter2.changeCursor(c);
			}			
		});
		
		milista.setOnItemClickListener(new OnItemClickListener() {			
			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {		
				
				Toast.makeText(getApplicationContext(), 
						"Elimino: "+((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				manager.eliminaring(((TextView) view).getText().toString());	
				Cursor c = manager.cargarCursorIngredientes();
				adapter2.changeCursor(c);
				
			}			
		});
		
	}	
		public void onClick(View view){
			if (view.getId() == R.id.imageButton1){
				
				Cursor c = manager.buscarIngrediente(tv.getText().toString());
				adapter.changeCursor(c);				
			}
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	
}
