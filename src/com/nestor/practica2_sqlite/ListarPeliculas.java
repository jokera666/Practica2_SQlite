package com.nestor.practica2_sqlite;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListarPeliculas extends Activity {

	ListView listaPeliculas;
	ArrayList<Pelicula> selectTodasPelis;
	ElementoPeliculaAdaptador pelis;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_peliculas);
		
		DataHelper peli1 = new DataHelper(this);
		listaPeliculas = (ListView)findViewById(R.id.miListViewPeliculas);
		
		
	
		selectTodasPelis = peli1.mostrarTodo();
		
	    //Si no hay registros mostramos un aviso
	    if (selectTodasPelis.isEmpty())
	    {
	    	Toast.makeText(getBaseContext(), "No se han encontrado registros", Toast.LENGTH_LONG).show();
	    }
	    
	    
	    //QUI ME LO IMPRIME TODO CORRECTAMENTE EN UN TEXTVIEW pero EN UN LISTVIEW NO
		//TextView selectView = (TextView) findViewById(R.id.select);
		
	    //peli1.borrarTodo(); //BORRAR TODO
//		StringBuilder builder = new StringBuilder();
//		for (String filas : selectTodasPelis) {
//		   builder.append(filas+"\n");
//		}
		
		//selectView.setText(builder.toString());	
		
	    pelis = new ElementoPeliculaAdaptador(this,selectTodasPelis); 
        listaPeliculas.setAdapter(pelis);
        
        
        
        	// Accion para realizar al pulsar sobre un item de la lista        
        	listaPeliculas.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Toast.makeText(getApplicationContext(), "ID de la pelicula: "+pelis.getPeliId(position), Toast.LENGTH_LONG).show(); 
				
	          	//Creamos el Intent
            	Intent intent = new Intent(ListarPeliculas.this, InsertarPelicula.class);
            	
            	//Creamos la informacion a pasar entre actividades
            	Bundle contenedor = new Bundle();
            	contenedor.putLong("idPelicula",pelis.getPeliId(position)); 

        
            	//A�adimos la informacion al intent
            	intent.putExtras(contenedor);
            	//ponemos en marcha la nueva actividad
            	startActivity(intent);
				
			}
		});
        	
    	  // Accion al manter pulsado sobre el item de la lista    
    	  listaPeliculas.setOnItemLongClickListener (new OnItemLongClickListener() {
		  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			  Toast.makeText(getApplicationContext(), "Precion continua de "+pelis.getPeliId(position), Toast.LENGTH_LONG).show();
			  
			  registerForContextMenu(listaPeliculas);
			  
			  
		    return true;
		  }
		});
		
		
	}
	
	//Menu contextual
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo){
	super.onCreateContextMenu(menu, v, menuInfo);
	MenuInflater inflater = new MenuInflater(this);
		switch (v.getId()){
		case R.id.borrar:
		inflater.inflate(R.menu.borrar_menu_contextual, menu);
		break;
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	switch (item.getItemId()){
	case R.id.borrar:
		Toast.makeText(getApplicationContext(), "borrar", Toast.LENGTH_LONG).show(); 
	return true;

	default:
	return super.onContextItemSelected(item);
	}
	}
	

	
	// Menu de navegacion (action bar)
	 @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; a�ade los elemnetos del menu
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
        	  ListarPeliculas.this.finish();	
  			//Lanzamos la actividad con el tema nuevo
  			
  			Intent intent = new Intent(ListarPeliculas.this, InsertarPelicula.class);
  			startActivity(intent);
  			
          	Context contexto = getApplicationContext();
          	String mensaje = "A�ade una nueva pelicula";
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
