
package com.nestor.practica2_sqlite;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.nestor.practica2_sqlite.R;


public class ElementoMenusAdaptador extends BaseAdapter {// Clase Adaptadora que hereda de BaseAdapter
	private Context mContext;
	private ArrayList<String> lista;
	
	 public ElementoMenusAdaptador(Context context,ArrayList<String> array)  // constructor de mi clase
	    {
	            super();
	            mContext=context;
	            lista = array;
	    }
	       
	    public int getCount() 
	    {
			 return lista.size();
	        //devuelve el tamaño de la array 
	    }

       

	// metodo getView llamado para cada item de ListView
    public View getView(int position,  View view, ViewGroup parent) 
    {
        
    	//inflar el layout para cada item del ListView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.elemento_listview_menu, null);
        
        //obtiene la refenrecia de cada opcion y la asigna a in textView
        TextView nombre =(TextView)view.findViewById(R.id.elementOpcion);

        //establece el contenido al textview a apartir de la posicion de la array
        nombre.setText(lista.get(position));
        
        return view;
    }

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}

