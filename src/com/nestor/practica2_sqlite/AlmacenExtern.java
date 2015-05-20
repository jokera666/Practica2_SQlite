package com.nestor.practica2_sqlite;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AlmacenExtern extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_almacen_extern);
		
		DataHelper dh = new DataHelper(this); // this es igual a getApplicationContext()
		dh.insertar("hola1","hola2","hola2","hola2","hola2","hola2","hola2",5.555,"hola2","hola2");
		List<String> selectTodo = dh.mostrarTodo();
		
		TextView selectView = (TextView) findViewById(R.id.select);
				
		StringBuilder builder = new StringBuilder();
		for (String details : selectTodo) {
		   builder.append(details + "\n");
		}

		selectView.setText(builder.toString());		
		
	}
}
