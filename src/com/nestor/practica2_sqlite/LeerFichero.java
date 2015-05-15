package com.nestor.practica2_sqlite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LeerFichero extends Activity {
	ArrayList<String> nombreFichero;
	File[] files;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leer_fichero);
		
	    // Array TEXTO donde guardaremos los nombres de los ficheros
		nombreFichero = new ArrayList<String>();

 
        //Defino la ruta donde busco los ficheros
        File fichero = new File(Environment.getExternalStorageDirectory()+"/misFicheros/");
        //Creo el array de tipo File con el contenido de la carpeta
        files = fichero.listFiles();
 
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
 
            	nombreFichero.add(file.getName());
        }
        
        //Localizamos y llenamos la lista con el array
        ListView listaFicheros = (ListView) findViewById(R.id.miListViewFicheros);
        //ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreFichero);
     // Cree el adaptador 
        ElementoListaAdaptador elemento = new ElementoListaAdaptador(this,nombreFichero);
        listaFicheros.setAdapter(elemento);
        
 
        // Accion para realizar al pulsar sobre un item de la lista        
        listaFicheros.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				//Cuando pulses sobre el nombre de fichero el toast mostrar su posicion 
				Toast.makeText(getApplicationContext(), "Posicion de fichero: "+nombreFichero.get(position), Toast.LENGTH_LONG).show();
                //Devolvemos el resultado de la selección
                //Intent data = new Intent();
                //data.putExtra("filename", nombreFichero.get(position));
                //setResult(RESULT_OK, data);
                //finish();
                String fichero = nombreFichero.get(position);
                cargarTexto(fichero);
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

		        
				try {
			        for (int i = 0; i < files.length; i++)
			        	 
			        {
			        	File file = files[i];
			            if (!file.delete()) throw new IOException("El fichero " + files[i] + " no puede ser borrado!");
			        }
				} 
				
				catch (IOException e) {
					Toast.makeText(getApplicationContext(),"Error: "+e, Toast.LENGTH_LONG).show();
				} // end try
			}
		});
	}
	
    // Inserta en el cuadro de texto el contenido del fichero
    private void cargarTexto(String fichero){
    	// Abrir el fichero para lectura
       	File f = new File(Environment.getExternalStorageDirectory()+"/misFicheros/"+fichero);
       	StringBuilder text = new StringBuilder();

       	try {
       	    BufferedReader br = new BufferedReader(new FileReader(f));
       	    String line;

       	    while ((line = br.readLine()) != null) {
       	        text.append(line);
       	        text.append('\n');
       	    }
       	    br.close();
       	    
       	}
       	catch (Exception e) {
       	    //You'll need to add proper error handling here
       		Toast.makeText(getApplicationContext(), "Hay un error al leer el fichero.", Toast.LENGTH_LONG).show();
       	}

       	TextView texto = (TextView)findViewById(R.id.mostrarTexto);
   		texto.setText(text);
    }
}
