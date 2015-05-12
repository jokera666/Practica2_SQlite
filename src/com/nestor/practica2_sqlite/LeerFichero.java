package com.nestor.practica2_sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LeerFichero extends Activity {
	ArrayList<String> nombreFichero;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leer_fichero);
		
	    // Array TEXTO donde guardaremos los nombres de los ficheros
		nombreFichero = new ArrayList<String>();

 
        //Defino la ruta donde busco los ficheros
        File fichero = new File(Environment.getExternalStorageDirectory()+"/misFicheros/");
        //Creo el array de tipo File con el contenido de la carpeta
        File[] files = fichero.listFiles();
 
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
                Intent data = new Intent();
                data.putExtra("filename", nombreFichero.get(position));
                setResult(RESULT_OK, data);
                //finish();
				switch(position){
				case 1:
					
					
				break;
				}
			}
		});
	}
	
    // Inserta en el cuadro de texto el contenido del fichero
//    private void cargarTexto(){
//    	// Abrir el fichero para lectura
//        FileInputStream fis = null;
//       	try {
//       		fis = openFileInput();
//       	} catch (FileNotFoundException e) {
//       		Log.e("TAG", "lectura: FileNotFound");
//       		return;
//       	}
//       	EditText texto = (EditText)findViewById(R.id.texto);
//       	byte[] datos = new byte[1024]; 	// buffer de bytes para copiar ah’ el contenido
//       	String lectura = "";			// texto que se insertar‡ en el cuadro
//       	int leidos = 0;
//       	try{
//       		// A–adir los datos le’dos mientras que todav’a queden
//       		while ((leidos = fis.read(datos))>0){ // read(byte[]) devuelve el nœmero de bytes leídos
//       			lectura += (new String(datos)).substring(0, leidos);
//       		}
//       		fis.close();
//       	} catch(IOException e){
//       		Log.e("TAG", "lectura: IOException");
//       	}
//   		texto.setText(lectura);
//    }
}
