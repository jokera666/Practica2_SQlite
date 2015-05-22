package com.nestor.practica2_sqlite;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ListarPeliculas extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_peliculas);
		
		DataHelper peli1 = new DataHelper(this);
		List<String> selectTodo = peli1.mostrarTodo();
		
	    //Si no hay registros mostramos un aviso
	    if (selectTodo.isEmpty())
	    {
	    	Toast.makeText(getBaseContext(), "No se han encontrado registros", Toast.LENGTH_LONG).show();
	    }
		
		TextView selectView = (TextView) findViewById(R.id.select);
				
		StringBuilder builder = new StringBuilder();
		for (String filas : selectTodo) {
		   builder.append(filas + "\n");
		}

		selectView.setText(builder.toString());	
		
		//peli1.borrarTodo();
	}
}
