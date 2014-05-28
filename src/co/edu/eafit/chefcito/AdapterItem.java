package co.edu.eafit.chefcito;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterItem extends BaseAdapter {

	protected Activity activity;
	protected ArrayList<Item> items;

	public AdapterItem(Activity activity, ArrayList<Item> items) {
		this.activity = activity;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Generamos una convertView por motivos de eficiencia
		View v = convertView;

		// Asociamos el layout de la lista que hemos creado
		if (convertView == null) {
			LayoutInflater inf = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.row, null);
		}

		// Creamos un objeto directivo
		Item dir = items.get(position);
		// Rellenamos el nombre
		TextView nombre = (TextView) v.findViewById(R.id.nombre_receta);
		nombre.setText(dir.getNombre());
		// Rellenamos el cargo
		TextView descripcion = (TextView) v
				.findViewById(R.id.dercripcion_receta);
		descripcion.setText(dir.getDescripcion());

		dir.getIdreceta();

		// Retornamos la vista
		return v;
	}

}
