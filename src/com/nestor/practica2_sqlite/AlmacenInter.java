package com.nestor.practica2_sqlite;

import android.app.Activity;
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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen_inter);
        
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.miListView);
        
        // Defined Array values to show in ListView
        String[] values = new String[] { "Leer ficheros", 
                                         "Escribir en ficheros",
                                         "Volver al menu principal <---",
                                        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
		 
        
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
        
    }

}