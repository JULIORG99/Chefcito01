package co.edu.eafit.chefcito;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashScreenActivity extends Activity {	
  private long splashDelay = 1000; //6 segundos
  private DataBaseManager manager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);  
    
    
    
    manager = new DataBaseManager(this);   
	manager.instertar("1","Azucar");
	manager.instertar("2","Arroz");
	manager.instertar("3","Sal");
	manager.instertar("4","Zanaoria");
	manager.instertar("5","Pan integral");
	manager.instertar("6","Harina");
	manager.instertar("7","Huevos");
	manager.instertar("8","Solomo");
	manager.instertar("9","Tomates");
	manager.instertar("10","Miel");
	
	
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
