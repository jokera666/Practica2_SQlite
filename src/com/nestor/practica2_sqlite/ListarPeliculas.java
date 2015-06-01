package com.nestor.practica2_sqlite;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListarPeliculas extends Activity {

	ListView listaPeliculas;
	ArrayList<Pelicula> arrayPeliculas;
	ElementoPeliculaAdaptador adaptadorPelicula;
	DataHelper dataHelperPelicula;
	int posicionPulsada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_peliculas);
		
		dataHelperPelicula = new DataHelper(this);
		listaPeliculas = (ListView)findViewById(R.id.miListViewPeliculas);
		
		registerForContextMenu(listaPeliculas); //es necesario para el menu contextual pasandole el listview
	
		arrayPeliculas = dataHelperPelicula.mostrarTodo();
		
	    //Si no hay registros mostramos un aviso
	    if (arrayPeliculas.isEmpty())
	    {
	    	Toast.makeText(getBaseContext(), "No se han encontrado registros", Toast.LENGTH_LONG).show();
	    }
	    
	 	
		
	    adaptadorPelicula = new ElementoPeliculaAdaptador(this,arrayPeliculas); 
        listaPeliculas.setAdapter(adaptadorPelicula);
        
        
        
    	// Accion para realizar al pulsar sobre un item de la lista        
    	listaPeliculas.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
			//Toast.makeText(getApplicationContext(), "ID de la pelicula: "+pelis.getPeliId(position), Toast.LENGTH_LONG).show(); 
			
			
			//Creamos el Intent
        	Intent intent = new Intent(ListarPeliculas.this, InsertarPelicula.class);
        	
        	//Creamos la informacion a pasar entre actividades
        	Bundle contenedor = new Bundle();
        	contenedor.putInt("idPelicula",adaptadorPelicula.getPeliId(position)); 
    
        	//Añadimos la informacion al intent
        	intent.putExtras(contenedor);
        	//ponemos en marcha la nueva actividad
        	startActivity(intent);
			
		}
	});		
	}
	
/*#####################-->MENU CONTEXTUAL<--########################*/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo){
	super.onCreateContextMenu(menu, v, menuInfo);
	MenuInflater inflater = new MenuInflater(this);
		switch (v.getId()){
		case R.id.miListViewPeliculas:
		inflater.inflate(R.menu.borrar_menu_contextual, menu);
		break;
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	switch (item.getItemId()){
	case R.id.borrar:
		AlertDialog.Builder advertencia = new AlertDialog.Builder(this);
		posicionPulsada = ((AdapterContextMenuInfo) item.getMenuInfo()).position;
		String nombrePeli = arrayPeliculas.get(posicionPulsada).getTitulo();
		advertencia.setTitle("Borrar la pelicula "+nombrePeli);
		advertencia.setMessage("Seguro que quieres borrar la pelicula?");
		
		advertencia.setPositiveButton("Sí", new OnClickListener() {
				public void onClick(DialogInterface dialog, int arg1) {
					
					dataHelperPelicula.borrar(arrayPeliculas.get(posicionPulsada).getId());
					//Refrescar el ListView despues del borrado cerrando y abriendo la actividad
					ListarPeliculas.this.finish();	
					startActivity(new Intent(ListarPeliculas.this, ListarPeliculas.this.getClass()));

				}
				});
		
		advertencia.setNegativeButton("No", new OnClickListener(){
				public void onClick(DialogInterface dialog, int arg1) {
					//no borres nada
				}
				});
		
		advertencia.show();
		
		
		return true;
	
		default:
		return super.onContextItemSelected(item);
	 	}
	}
	
/*################-->FIN MENU CONTEXTUAL<--####################*/

	
/*################-->MENU BARRA DE NAVEGACION<--###############*/
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
        	  ListarPeliculas.this.finish();	
  			//Lanzamos la actividad con el tema nuevo
  			
  			Intent intent = new Intent(ListarPeliculas.this, InsertarPelicula.class);
  			startActivity(intent);
  			
          	Context contexto = getApplicationContext();
          	String mensaje = "Añade una nueva pelicula";
          	int duracion = Toast.LENGTH_SHORT;
          	Toast toast = Toast.makeText(contexto, mensaje,duracion);
          	// para los toast se puede utilizar tambien:
          	//Toast toast = Toast.makeText(this, "esto es mi mensaje",Toast.LENGTH_SHORT);
          	toast.show();
              return true;
          }
          
          if (id == R.id.borrarTodo) {
          	item.setChecked(true);
          	
          	AlertDialog.Builder advertencia = new AlertDialog.Builder(this);
      		advertencia.setTitle("Borrar todo");
      		advertencia.setMessage("Seguro que quieres borrar todas las peliculas?");
      		
      		advertencia.setPositiveButton("Si", new OnClickListener() {
      				public void onClick(DialogInterface dialog, int arg1) {
      					
      				dataHelperPelicula.borrarTodo();
					ListarPeliculas.this.finish();	
					startActivity(new Intent(ListarPeliculas.this, ListarPeliculas.this.getClass()));
      					
      				}
      				});
      		
      		advertencia.setNegativeButton("No", new OnClickListener(){
      				public void onClick(DialogInterface dialog, int arg1) {
      					//no borres nada
      				}
      				});
      		
      		advertencia.show();
          	
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
/*################-->FIN MENU BARRA DE NAVEGACION<--###############*/
}
