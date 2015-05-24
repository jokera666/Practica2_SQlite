package com.nestor.practica2_sqlite;


import java.util.Calendar;
import java.util.List;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InsertarPelicula extends Activity {
	
	String[] listaGenero = new String[]{"Drama","Comedia","Historico","Accion","Aventura","Ciencia Ficcion","Romantico","Musical","Suspense","Porno"};
	String[] listaIdioma = new String[]{"Български","Español","English","Français","Català","Euskera","Galego","Deutschland","Italiano","漢語"};
	String[] listaFormato = new String[]{"VHS","DVD","Blu-Ray"};
	Spinner spinGenero;
	Spinner spinIdioma;
	Spinner spinFormato;
	
	//Propiedades que se utilizaran para el INSERT
	 String inGenero;
	 String inTitulo;
	 String inDirector;
	 String inIdioma;
	 String inFormato;
	 String inFechaIni;
	 String inFechaFin;
	 String inPrestado_a;
	 String inNotas;
	 float inValoracion;
	
	TextView fecha_ini;
	TextView fecha_fin;
	Button btnFecha_ini;
	Button btnFecha_fin;
	private int mYear, mMonth, mDay;
	
	EditText titulo;
	EditText director;
	EditText prestado_a;
	EditText notas;
	RatingBar valoracion;
	
	private DataHelper peli;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_pelicula);
		
		peli = new DataHelper(this); // this es igual a getApplicationContext()
		titulo = (EditText)findViewById(R.id.editTitulo);
		//titulo.setText("Hola");
		director = (EditText)findViewById(R.id.editDirector);
		fecha_ini = (TextView) findViewById(R.id.fechaIni);
		fecha_fin = (TextView) findViewById(R.id.fechaFin);
		prestado_a = (EditText)findViewById(R.id.editPrestado);
		notas = (EditText)findViewById(R.id.editNotas);
		valoracion = (RatingBar)findViewById(R.id.barraRango);
		
		spinGenero = (Spinner)findViewById(R.id.spinGenero);
		ArrayAdapter<String> adaptadorGenero = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaGenero);
		adaptadorGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinGenero.setAdapter(adaptadorGenero);
		
		spinIdioma = (Spinner)findViewById(R.id.spinIdioma);
		ArrayAdapter<String> adaptadorIdioma = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaIdioma);
		adaptadorIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinIdioma.setAdapter(adaptadorIdioma);

		spinFormato = (Spinner)findViewById(R.id.spinFormato);
		ArrayAdapter<String> adaptadorFormato = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaFormato);
		adaptadorIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinFormato.setAdapter(adaptadorFormato);
		
		
		
		
		
		
		Button btnFecha_ini = (Button) findViewById(R.id.btnFechaIni);
        btnFecha_ini.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
					        	 
		         // Obtener la fecha a partider de un DatePicker e mostrarlo en un textView
			  final Calendar c = Calendar.getInstance();
			  mYear = c.get(Calendar.YEAR);
			  mMonth = c.get(Calendar.MONTH);
			  mDay = c.get(Calendar.DAY_OF_MONTH);
		 
			  // Lanzar Date Picker Dialog
			  DatePickerDialog dpd = new DatePickerDialog(InsertarPelicula.this, new DatePickerDialog.OnDateSetListener() {
		 
			        @Override
			        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		                     
				       fecha_ini.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
				    }
			        }, mYear, mMonth, mDay);
				    dpd.show();
			}
		});
        
        
        
		
        Button btnFecha_fin = (Button) findViewById(R.id.btnFechaFin);
        btnFecha_fin.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
					        	 
		         // Obtener la fecha a partider de un DatePicker e mostrarlo en un textView
			  final Calendar c = Calendar.getInstance();
			  mYear = c.get(Calendar.YEAR);
			  mMonth = c.get(Calendar.MONTH);
			  mDay = c.get(Calendar.DAY_OF_MONTH);
		 
			  // Lanzar Date Picker Dialog
			  DatePickerDialog dpd = new DatePickerDialog(InsertarPelicula.this, new DatePickerDialog.OnDateSetListener() {
		 
			        @Override
			        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		                     
				       fecha_fin.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
				    }
			        }, mYear, mMonth, mDay);
				    dpd.show();
			}
		});
        
        
        
        
        //Insertar pelicula pelicula
        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				inGenero = spinGenero.getSelectedItem().toString();
				inTitulo = titulo.getText().toString();
				inDirector = director.getText().toString();
				inIdioma = spinIdioma.getSelectedItem().toString();
				inFormato = spinFormato.getSelectedItem().toString();
				inFechaIni = fecha_ini.getText().toString();
				inFechaFin = fecha_fin.getText().toString();
				inPrestado_a = prestado_a.getText().toString();
				inNotas = notas.getText().toString();
				inValoracion = valoracion.getRating();
				long longValoracion = (long) Math.ceil(inValoracion);
				
				
				peli.insertar(inGenero,inTitulo,inDirector,inIdioma,inFormato,inFechaIni,inFechaFin,inPrestado_a,inNotas,longValoracion);
				Intent intent = new Intent(InsertarPelicula.this,ListarPeliculas.class);
				startActivity(intent);	       	 
			}
		});
        
        //Volver al menu principal
        Button btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(InsertarPelicula.this,AlmacenExtern.class);
				startActivity(intent);	       	 
			}
		});
        
         
		
	}
	
	 @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; añade los elemnetos del menu
          getMenuInflater().inflate(R.menu.main, menu);
          return true;
      }
     
     
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
      	// es un metodo boleano que cuando pulsamos a cualquier opcion
      	//del menu ejecuta lo que hay dentro del if
      	// item: es la opcion pulsada
          int id = item.getItemId();
     


          if (id == R.id.insertarPelicula) {
  			
  			//Finalizamos la actividad
        	  InsertarPelicula.this.finish();	
  			//Lanzamos la actividad con el tema nuevo
  			startActivity(new Intent(InsertarPelicula.this, InsertarPelicula.this.getClass()));
  			
          	Context contexto = getApplicationContext();
          	String mensaje = "Añade una nueva pelicula";
          	int duracion = Toast.LENGTH_SHORT;
          	Toast toast = Toast.makeText(contexto, mensaje,duracion);
          	// para los toast se puede utilizar tambien:
          	//Toast toast = Toast.makeText(this, "esto es mi mensaje",Toast.LENGTH_SHORT);
          	toast.show();
              return true;
          }
          
          if (id == R.id.acercaDe) {
          	item.setChecked(true);
          	Context contexto = getApplicationContext();
          	String mensaje = "Esta aplicacion fue creado por Nestor Dobrinov Edrev. Version 1.0";
          	int duracion = Toast.LENGTH_SHORT;
          	Toast toast = Toast.makeText(contexto, mensaje,duracion);
          	toast.show();
              return true;
          }
          return super.onOptionsItemSelected(item);
     }
}
