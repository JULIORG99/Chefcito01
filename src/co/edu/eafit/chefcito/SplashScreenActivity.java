package co.edu.eafit.chefcito;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

public class SplashScreenActivity extends Activity {
	private long splashDelay = 5000; // 5 segundos
	private DataBaseManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		manager = new DataBaseManager(this);
		Cursor c = manager.cargarCursor();

		if (c.moveToFirst() == false) {
			manager = new DataBaseManager(this);
			manager.instertar("1", "Arroz");
			manager.instertar("2", "Crema de leche");
			manager.instertar("3", "Azúcar");
			manager.instertar("4", "Agua");
			manager.instertar("5", "Coco");
			manager.instertar("6", "Leche");
			manager.instertar("7", "Mantequilla");
			manager.instertar("8", "Uvas pasas");
			manager.instertar("9", "Astilla de canela");
			manager.instertar("10", "Galletas Milo");
			manager.instertar("11", "Queso crema");
			manager.instertar("12", "Gelatina");
			manager.instertar("13", "Huevos");						
		}
		manager.eliminarTablaMisIngredientes();

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent mainIntent = new Intent().setClass(
						SplashScreenActivity.this, MainActivity.class);
				startActivity(mainIntent);
				finish();// Destruimos esta activity para prevenit que el
							// usuario retorne aqui presionando el boton Atras.
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, splashDelay);// Pasado los 6 segundos dispara la
											// tarea
	}

}
