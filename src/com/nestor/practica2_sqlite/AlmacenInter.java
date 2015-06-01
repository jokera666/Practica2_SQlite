package com.nestor.practica2_sqlite;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class AlmacenInter extends Activity {
	///Propiedades de la clase AlmacenInter
    ListView listView;
    ElementoMenusAdaptador elemento;
    Toast mensaje;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen_inter);
        
        listView = (ListView) findViewById(R.id.miListView); //asigno mi ListView del xml
        
        ArrayList<String> list = new ArrayList<String>(); // me creo un array list donde voy a almacenar las opciones de mi lista
        Resources res = getResources(); // obtener recurso
        Collections.addAll(list, res.getStringArray(R.array.listaOpciones)); // obtengo los recursos del arrays.xml y se los paso a mi lista
 
	    elemento  = new ElementoMenusAdaptador(this,list);// creo un objeto de mi lista adaptador y le paso la array con las opciones y el contexto
	    
	    // establesco el mi adaptador al list view
	    listView.setAdapter(elemento);
        
		 
        
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intento;
			    // When clicked, show a toast with the TextView text
				//Toast.makeText(getApplicationContext(), "Has pulsado la opcion: "+position, Toast.LENGTH_LONG).show();
				switch(position)
				{
					case 0:
						 intento = new Intent(AlmacenInter.this,LeerFichero.class);
						 startActivity(intento);
					break;
					case 1:
						intento = new Intent(AlmacenInter.this,EscribirFichero.class);
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