package co.edu.eafit.chefcito;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends Activity {
	
	private ListView lstOpciones;
	
	final String[] datos = new String[]{"Arandano","Arroz","Arroz Integral","Avena","Azucar"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

        final EditText txtNombre = (EditText)findViewById(R.id.TxtNombre);
     
        
        Resources res = getResources();
        
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Lista de Ingredientes", res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
        
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Mis Ingredientes", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        
        tabs.setCurrentTab(0);
        
        tabs.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				Log.i("AndroidTabsDemo", "Pulsada pestaÒa: " + tabId);
			}
		});
        
        	 
    	ArrayAdapter<String> adaptador =
    	    new ArrayAdapter<String>(this,
    	        android.R.layout.simple_list_item_1, datos);
    	 
    	lstOpciones = (ListView)findViewById(R.id.LstOpciones);
    	 
    	lstOpciones.setAdapter(adaptador);
    	/*lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            	
            	//Alternativa 1:
            	String opcionSeleccionada = 
            			(a.getAdapter().getItem(position)).toString();
            	
            	//Alternativa 2:
            	//String opcionSeleccionada = 
            	//		((TextView)v.findViewById(R.id.LblTitulo))
            	//			.getText().toString();
            	
            	lblEtiqueta.setText("OpciÛn seleccionada: " + opcionSeleccionada);
            }
        });*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
