package com.nestor.practica2_sqlite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EscribirFichero extends Activity {

	public String nombreF;
	public String cotenido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escribir_fichero);

		
        Button btnGardar = (Button) findViewById(R.id.guardarFichero);
        btnGardar.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				
				EditText nombreFichero = (EditText) findViewById(R.id.nombreFichero);
				EditText contenidoFichero = (EditText) findViewById(R.id.contenidoFichero);
				nombreF = nombreFichero.getText().toString()+".txt";
				cotenido = contenidoFichero.getText().toString();
				
				if(nombreFichero.length() == 0){ // comprobacion de error EditText Campo vacio
					Toast.makeText(getApplicationContext(), "Introduce nombre del fichero.", Toast.LENGTH_LONG).show();
		        	return; // Detiene la actividad
				}
				
				escribirTexto(nombreF, cotenido);
				Toast.makeText(getApplicationContext(), "El fichero "+nombreF+" fue guardado.", Toast.LENGTH_LONG).show();
			}
		});
        
        Button btnVolver = (Button) findViewById(R.id.volver);
        btnVolver.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(EscribirFichero.this,AlmacenInter.class);
				startActivity(intent);	
			}
		});
	}
	
	public void escribirTexto(String nombre , String cotenido)
	{
		try 
		{
			File raiz = new File (Environment.getExternalStorageDirectory()+"/misFicheros/");
				if (raiz.canWrite())
				{
					File file = new File(raiz, nombre);
					BufferedWriter out = new BufferedWriter(new FileWriter(file));
					out.write(cotenido);
					out.close();
				}
		} 
		
		catch (IOException e) 
		{
			Log.e("FILE I/O", "Error en la escritura de fichero: " +e.getMessage());
		}	
	}	
}
