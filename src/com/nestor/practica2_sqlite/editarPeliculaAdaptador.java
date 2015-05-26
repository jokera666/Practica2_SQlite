package com.nestor.practica2_sqlite;


import java.util.ArrayList;

import android.content.Context;
import android.net.ParseException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nestor.practica2_sqlite.R;

public class editarPeliculaAdaptador extends BaseAdapter {
	
	private Context mContext;
	private ArrayList<Pelicula> lista;
	
	 public editarPeliculaAdaptador(Context context,ArrayList<Pelicula> array)  // constructor de mi clase
	 {
	            super();
	            mContext=context;
	            lista =  array;
	 }
	 
    public View getView(int position,  View view, ViewGroup parent) 
    {
        
    	//inflar el layout para cada item del ListView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_insertar_pelicula, null);
        
        EditText titulo =(EditText)view.findViewById(R.id.editTitulo);
        titulo.setText(lista.get(position).getTitulo());
        
        return view;
    }
	 
	 
	 
	 
	 

	    public int getCount() 
	    {
			 return lista.size();
	        //devuelve el tamaño de la array 
	    }

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return lista.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		public long getPeliId(int position)
		{
			return lista.get(position).getId();
		}

}
