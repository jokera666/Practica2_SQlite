package com.nestor.practica2_sqlite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LeerFichero extends Activity {
	ArrayList<String> nombreFichero;
	File[] files;
	File directorioFicheros;
	ListView listaFicheros;
	TextView texto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leer_fichero);
		
	   
		nombreFichero = new ArrayList<String>();// Array TEXTO donde guardaremos los nombres de los ficheros
 
        directorioFicheros = new File(Environment.getExternalStorageDirectory()+"/misFicheros/");//Defino la ruta donde busco los ficheros
        files = directorioFicheros.listFiles(); //obtiene todos los ficheros del directorio y los guarda en una array de Files[]
 
        //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
        for (int i = 0; i < files.length; i++)
 
        {

        	//Sacamos del array files un fichero
            File file = files[i];
 
            //Si es directorio...
            if (file.isDirectory())
 
            	nombreFichero.add(file.getName() + "/");
            
            //Si es fichero...
            else
 
            	nombreFichero.add(file.getName()); // obtiene el nombre del fichero
        }
		
        listaFicheros = (ListView) findViewById(R.id.miListViewFicheros); // declaramos el listview donde va listar los ficheros 
        /*Creo un objeto de ElementoListaAdaptador donde le paso el contexto y los nombre 
        de los ficheros donde los va a listar en el ListView*/
        ElementoMenusAdaptador elemento = new ElementoMenusAdaptador(this,nombreFichero); 
        listaFicheros.setAdapter(elemento);
        
 
        // Accion para realizar al pulsar sobre un item de la lista        
        listaFicheros.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				//Toast.makeText(getApplicationContext(), "Posicion de fichero: "+nombreFichero.get(position), Toast.LENGTH_LONG).show();//Cuando pulses sobre el nombre de fichero el toast mostrar su posicion 
				String fichero = nombreFichero.get(position); //obtengo el nombre del fichero a partir de su posicion y se lo paso a la funcion de Leer
	            LeerTexto(fichero);
			}
		});
        
        Button btnVolver = (Button) findViewById(R.id.volver);
        btnVolver.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
			Intent intent = new Intent(LeerFichero.this,AlmacenInter.class);
			startActivity(intent);	
			}
		});
        
        Button btnBorrarTodo = (Button) findViewById(R.id.borrarTodo);
        btnBorrarTodo.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
					
				File[] files = directorioFicheros.listFiles();
				try {
				        for (int i = 0; i < files.length; i++) 
				        {
				        	files[i].delete();
				        	Toast.makeText(getApplicationContext(), "Todos los ficheros fueron borrados.", Toast.LENGTH_LONG).show();
				        	listaFicheros.setAdapter(null);
				        	texto.setText(null);
				        	listaFicheros.setVisibility(View.INVISIBLE);
				        	texto.setVisibility(View.INVISIBLE);
				        }

					} 
				
				catch (Exception e) {
					Log.e("FILE I/O", "Error en la lectura de fichero: " +e.getMessage());
				}
			}
		});
	}
	
    
    private void LeerTexto(String fichero){
    	// Abrir el fichero para lectura
       	File f = new File(Environment.getExternalStorageDirectory()+"/misFicheros/"+fichero); // obtengo el fichero con la ruta completa
       	StringBuilder text = new StringBuilder(); // constructor de String donde concatena las lineas de String's almacenadas en el buffer

       	try {
       	    BufferedReader br = new BufferedReader(new FileReader(f)); // buffer donde alamacena el fichero leido
       	    String line; // lineas

       	    while ((line = br.readLine()) != null) { // mientras el buffer no este vacio LEE
       	        text.append(line);
       	        text.append('\n');
       	    }
       	    br.close(); // cerramos el buffer
       	    
       	}
       	catch (Exception e) {
       		Log.e("FILE I/O", "Error en la lectura de fichero: " +e.getMessage());
       	}

       	texto = (TextView)findViewById(R.id.mostrarTexto);
   		texto.setText(text); // inicializa en el cuadro de texto con el contenido del fichero
    }
}
