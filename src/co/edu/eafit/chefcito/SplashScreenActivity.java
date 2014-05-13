package co.edu.eafit.chefcito;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

public class SplashScreenActivity extends Activity {	
  private long splashDelay = 5000; //5 segundos
  private DataBaseManager manager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);  
    
    manager = new DataBaseManager(this);    
    Cursor c = manager.cargarCursor();
    
	if(c.moveToFirst()==false){    
	    manager = new DataBaseManager(this);   
	    manager.instertar("1","Arroz");
	    manager.instertar("2","Crema de leche");
	    manager.instertar("3","Azucar");
	    manager.instertar("4","Astilla de canela");
	    manager.instertar("5","Agua");
	    manager.instertar("6","Coco");
	    manager.instertar("7","Mantequilla");
	    manager.instertar("8","Uvas pasas");
	    manager.instertar("9","Leche");
	    manager.instertar("10","Banano");
	    manager.instertar("11","Zumo de limon");
	    manager.instertar("12","Sirope de jengibre");
	    manager.instertar("13","Hielo");
	    manager.instertar("14","Jengibre");
	    manager.instertar("15","Ginger ale");
	    manager.instertar("16","Harina");
	    manager.instertar("17","Esencia de vainilla");
	    manager.instertar("18","Polvo para hornear");
	    manager.instertar("19","Huevos");
	    manager.instertar("20","Naranja");
	    manager.instertar("21","Leche en polvo");
	    manager.instertar("22","glucosa");
	    manager.instertar("23","Glucosa en polvo");
	    manager.instertar("24","Pasta de tomate");
	    manager.instertar("25","Almendras");
	    manager.instertar("26","Cacao puro");
	    manager.instertar("27","Agave");
	    manager.instertar("28","Canela en polvo");
	    manager.instertar("29","vainilla");
	}
	manager.eliminarTablaMisIngredientes();
    
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();//Destruimos esta activity para prevenit que el usuario retorne aqui presionando el boton Atras.
      }
    };

    Timer timer = new Timer();
    timer.schedule(task, splashDelay);//Pasado los 6 segundos dispara la tarea
  }
 

}
