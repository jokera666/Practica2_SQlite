
package com.nestor.practica2_sqlite;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.nestor.practica2_sqlite.R;

public class ElementoListaAdaptador extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> lista;
	
	 public ElementoListaAdaptador(Context context,ArrayList<String> array) 
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

       

    // getView method is called for each item of ListView
    public View getView(int position,  View view, ViewGroup parent) 
    {
        
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_elementos_list_menu, null);
        
        // get the reference of textViews
        TextView nombre =(TextView)view.findViewById(R.id.elementOpcion);


        
    	// Set the Sender number and smsBody to respective TextViews
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

