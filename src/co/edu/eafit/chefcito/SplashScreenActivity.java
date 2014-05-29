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
			manager.instertar("14", "Harina");
			manager.instertar("15", "Sal");
			manager.instertar("16", "Vino dulce");
			manager.instertar("17", "Agua de coco");
			manager.instertar("18", "Ciruelas pasas ");
			manager.instertar("19", "Maicena");
			manager.instertar("20", "Naraja");
			manager.instertar("21", "Azúcar para caramelizar");
			manager.instertar("22", "Frijoles");
			manager.instertar("23", "Chile rojo");
			manager.instertar("24", "Zanahoria");
			manager.instertar("25", "Cebolla");
			manager.instertar("26", "Pimiento rojo");
			manager.instertar("27", "Tofu orgánico");
			manager.instertar("28", "Tomate frito");
			manager.instertar("29", "Tomates maduros");
			manager.instertar("30", "Vinagre de sidra");
			manager.instertar("31", "Hoja de laurel");
			manager.instertar("32", "Mostaza");
			manager.instertar("33", "Aceite de oliva");
			manager.instertar("34", "Champiñones");
			manager.instertar("35", "Coles de brucelas");
			manager.instertar("36", "Jamón de pavo");
			manager.instertar("37", "Vino blanco");
			manager.instertar("38", "Aceite extra virgen");
			manager.instertar("39", "Granola");
			manager.instertar("40", "Yogurt");
			manager.instertar("41", "Fruta fresca");
			manager.instertar("42", "Miel");
			manager.instertar("43", "Chocolate negro sin azúcar ");
			manager.instertar("44", "Leche desnatada");
			manager.instertar("45", "Gelatina sin sabor");
			manager.instertar("46", "Edulcorante líquido");
			manager.instertar("47", "Tortillas de trigo integral");
			manager.instertar("48", "Queso crema sin grasa");
			manager.instertar("49", "Lechuga romana ");
			manager.instertar("50", "Pepino picado");
			manager.instertar("51", "Chiles verdes en lata");
			manager.instertar("52", "Aceitunas maduras");
			manager.instertar("53", "Ajo picado");
			manager.instertar("54", "Calabacín");
			manager.instertar("55", "Calabaza ");
			manager.instertar("56", "Patatas");
			manager.instertar("57", "Tomillo seco");
			manager.instertar("58", "Pimienta");
			manager.instertar("59", "Queso muzzarella");
			manager.instertar("60", "Tomate");
			manager.instertar("61", "Harina de maíz");
			manager.instertar("62", "Harina de trigo");
			manager.instertar("63", "Levadura");
			manager.instertar("64", "Salchichas");
			manager.instertar("65", "Brochetas de madera");
			manager.instertar("66", "Pollo cocido y desmenuzado");
			manager.instertar("67", "Queso semi duro rallado");
			manager.instertar("68", "Pimienta jalapeño sin semillas");
			manager.instertar("69", "Comino");
			manager.instertar("70", "Masa para empanadas");
			manager.instertar("71", "Vino blanco seco");
			manager.instertar("72", "Agua mineral");
			manager.instertar("73", "Lima");
			manager.instertar("74", "Melón piel de sapo");
			manager.instertar("75", "Melón cantaloup");
			manager.instertar("76", "Pasta brisa");
			manager.instertar("77", "Chocolate negro");
			manager.instertar("78", "Nata");
			manager.instertar("79", "Azúcar glas");
			manager.instertar("80", "Pierna de cordero");
			manager.instertar("81", "Polenta");
			manager.instertar("82", "Perejil");
			manager.instertar("83", "Pimienta negra");
			manager.instertar("84", "Ramitas de menta");
			manager.instertar("85", "Cebolla roja");
			manager.instertar("86", "Batata");
			manager.instertar("87", "Jengibre");
			manager.instertar("88", "Ajo ");
			manager.instertar("89", "Caldo de verduras");
			manager.instertar("90", "Aceite de coco");
			manager.instertar("91", "Cúrcuma");
			manager.instertar("92", "Chile en polvo");
			manager.instertar("93", "Dulce de leche");
			manager.instertar("94", "Crema de leche");
			manager.instertar("95", "Jugo de limón");
			manager.instertar("96", "Ron");
			manager.instertar("97", "Almíbar");
			manager.instertar("98", "Azúcar impalpable");
			manager.instertar("99", "Pechuga de pollo");
			manager.instertar("100", "Leche de coco");
			manager.instertar("101", "Caldo de pollo");
			manager.instertar("102", "Curry en polvo");
			manager.instertar("103", "Crema líquida");
			manager.instertar("104", "Almendras sin piel");
			manager.instertar("105", "Vainilla");
			manager.instertar("106", "Pimienta");
			manager.instertar("107", "Alitas de pollo");
			manager.instertar("108", "sal marina");
			manager.instertar("109", "Beicon");
			manager.instertar("110", "Queso rallado");
			manager.instertar("111", "Nuez moscada");
			manager.instertar("112", "Romero");
			manager.instertar("113", "Pescado");
			manager.instertar("114", "Camarones");
			manager.instertar("115", "Puerro");
			manager.instertar("116", "Paprika");
			manager.instertar("117", "Yogurt de fresa");
			manager.instertar("118", "Yogurt natural");
			manager.instertar("119", "Petit suisse de fresa");
			manager.instertar("120", "Manzanas verdes");
			manager.instertar("121", "Cerveza");
			manager.instertar("122", "Polvo de hornear");
			manager.instertar("123", "Bicarbonato de sodio");
			manager.instertar("124", "Canela");
			manager.instertar("125", "Queso rallado");

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
