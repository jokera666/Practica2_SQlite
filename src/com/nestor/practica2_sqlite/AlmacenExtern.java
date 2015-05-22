package com.nestor.practica2_sqlite;


import java.util.Calendar;
import java.util.List;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AlmacenExtern extends Activity {
	
	String[] listaGenero = new String[]{"Drama","Comedia","Historico","Accion","Aventura","Ciencia Ficcion","Romantico","Musical","Suspense","Porno"};
	String[] listaIdioma = new String[]{"Български","Español","English","Français","Catalán","Vasco","Gallego","Deutschland","Italiano","漢語"};
	String[] listaFormato = new String[]{"VHS","DVD","Blu-Ray"};
	Spinner spinGenero;
	Spinner spinIdioma;
	Spinner spinFormato;
	
	TextView fecha_ini;
	TextView fecha_fin;
	Button btnFecha_ini;
	Button btnFecha_fin;
	private int mYear, mMonth, mDay;
	
	EditText titulo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_almacen_extern);
		
		//titulo = (EditText)findViewById(R.id.editTitulo);
		//titulo.setText("Hola");
		
		spinGenero = (Spinner)findViewById(R.id.spinGenero);
		// Crear ArrayAdapter usando un string de array y el spinner layout por defecto
		ArrayAdapter<String> adaptadorGenero = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaGenero);
		//La apariencia del adaptador con las opciones
		adaptadorGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Aplicar el addapter al spinner
		spinGenero.setAdapter(adaptadorGenero);
		
		spinIdioma = (Spinner)findViewById(R.id.spinIdioma);
		// Crear ArrayAdapter usando un string de array y el spinner layout por defecto
		ArrayAdapter<String> adaptadorIdioma = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaIdioma);
		//La apariencia del adaptador con las opciones
		adaptadorIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Aplicar el addapter al spinner
		spinIdioma.setAdapter(adaptadorIdioma);

		spinFormato = (Spinner)findViewById(R.id.spinFormato);
		// Crear ArrayAdapter usando un string de array y el spinner layout por defecto
		ArrayAdapter<String> adaptadorFormato = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaFormato);
		//La apariencia del adaptador con las opciones
		adaptadorIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Aplicar el addapter al spinner
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
			  DatePickerDialog dpd = new DatePickerDialog(AlmacenExtern.this, new DatePickerDialog.OnDateSetListener() {
		 
			        @Override
			        public void onDateSet(DatePicker view, int year,
			           int monthOfYear, int dayOfMonth) {
		                     
			        	TextView fecha_ini = (TextView) findViewById(R.id.fechaIni);
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
			  DatePickerDialog dpd = new DatePickerDialog(AlmacenExtern.this, new DatePickerDialog.OnDateSetListener() {
		 
			        @Override
			        public void onDateSet(DatePicker view, int year,
			           int monthOfYear, int dayOfMonth) {
		                     
			        	TextView fecha_fin = (TextView) findViewById(R.id.fechaFin);
				       fecha_fin.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
				    }
			        }, mYear, mMonth, mDay);
				    dpd.show();
			}
		});
        
        
		
		DataHelper dh = new DataHelper(this); // this es igual a getApplicationContext()
		dh.insertar("hola1","hola2","hola3","hola4","hola5","hola6","hola7",5.555,"hola9","hola10");
		List<String> selectTodo = dh.mostrarTodo();
		
	    //Si no hay registros mostramos un aviso
	    if (selectTodo.isEmpty())
	    {
	    	Toast.makeText(getBaseContext(), "No se han encontrado registros", Toast.LENGTH_LONG).show();
	    }
		
		//TextView selectView = (TextView) findViewById(R.id.select);
				
		StringBuilder builder = new StringBuilder();
		for (String details : selectTodo) {
		   builder.append(details + "\n");
		}

		//selectView.setText(builder.toString());	
		
		dh.borrarTodo();
		
	}
}
