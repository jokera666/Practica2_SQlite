package com.nestor.practica2_sqlite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class AlmacenInter extends Activity {
    ListView listView ;
    String s = new String();
    ElementoListaAdaptador elemento;
    Toast mensaje;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen_inter);
        
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.miListView);
        
        ArrayList<String> list = new ArrayList<String>(); // me creo un Arraylist de string
        Resources res = getResources(); // obtener recurso recursos
        Collections.addAll(list, res.getStringArray(R.array.listaOpciones)); // obtengo los recursos del array.xml y se los paso a mi lista
 
        
	    // Cree el adaptador 
	    elemento  = new ElementoListaAdaptador(this,list);
	    
	    // establesco el mi adaptador al list view
	    listView.setAdapter(elemento);
        
		 
        
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intento;
			    // When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(), "Has pulsado la opcion: "+position, Toast.LENGTH_LONG).show();
				switch(position)
				{
					case 0:
						 intento = new Intent(AlmacenInter.this,LeerFichero.class);
						 startActivity(intento);
					break;
					case 1:
						intento = new Intent(AlmacenInter.this,MainActivity.class);
						startActivity(intento);
					break;
					case 2:
						 intento = new Intent(AlmacenInter.this,MainActivity.class);
						 startActivity(intento);
					break;	
				}
				
				
					
			}
		});
        
    }

}