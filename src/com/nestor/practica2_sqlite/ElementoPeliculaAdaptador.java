
package com.nestor.practica2_sqlite;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import java.util.List;

import android.content.Context;
import android.net.ParseException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nestor.practica2_sqlite.R;


public class ElementoPeliculaAdaptador extends BaseAdapter {// Clase Adaptadora que hereda de BaseAdapter
	private Context mContext;
	private ArrayList<Pelicula> lista;
	
	 public ElementoPeliculaAdaptador(Context context,ArrayList<Pelicula> array)  // constructor de mi clase
	    {
	            super();
	            mContext=context;
	            lista =  array;
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
        view = inflater.inflate(R.layout.activity_elemento_list_pelicula, null);
        
        //obtiene la refenrecia de cada opcion y la asigna a in textView
        ImageView imgIdioma = (ImageView)view.findViewById(R.id.portada);
        TextView titulo =(TextView)view.findViewById(R.id.titTitulo);
        TextView director =(TextView)view.findViewById(R.id.titDirector);
        TextView genero =(TextView)view.findViewById(R.id.titGenero);
        RatingBar valoracion = (RatingBar)view.findViewById(R.id.barraRango);
        TextView fecha = (TextView)view.findViewById(R.id.titFechaIni);
        TextView imgFormato = (TextView)view.findViewById(R.id.titFormato);
        TextView imgNotas = (TextView)view.findViewById(R.id.titNotas);
        TextView imgPrestado = (TextView)view.findViewById(R.id.titPrestado);
        TextView imgFinalizado = (TextView)view.findViewById(R.id.titFinalizado);
        
        
       

        //establece el contenido al textview a apartir de la posicion de la array
     
        //titulo.setText(Long.toString(lista.get(position).getId())); // covertir de long a cadena
        titulo.setText(lista.get(position).getTitulo());
        director.setText(lista.get(position).getDirector());
        genero.setText(lista.get(position).getGenero());
        fecha.setText(lista.get(position).getFechaIni());
        valoracion.setRating(lista.get(position).getValoracion());
       
        
        String idioma = lista.get(position).getIdioma();
        if(idioma.equals("Български")) 	 imgIdioma.setImageResource(R.drawable.pelibg);
        if(idioma.equals("Español"))   	 imgIdioma.setImageResource(R.drawable.peliesp);
        if(idioma.equals("English"))     imgIdioma.setImageResource(R.drawable.pelieng);
        if(idioma.equals("Français"))    imgIdioma.setImageResource(R.drawable.pelifra);
        if(idioma.equals("Català"))   	 imgIdioma.setImageResource(R.drawable.pelicat);
        if(idioma.equals("Euskera"))     imgIdioma.setImageResource(R.drawable.pelivas);
        if(idioma.equals("Galego"))   	 imgIdioma.setImageResource(R.drawable.peligal);
        if(idioma.equals("Deutschland")) imgIdioma.setImageResource(R.drawable.peliale);
        if(idioma.equals("Italiano"))    imgIdioma.setImageResource(R.drawable.peliita);
        if(idioma.equals("漢語"))   		 imgIdioma.setImageResource(R.drawable.pelichi);
        
        String formato = lista.get(position).getFormato();
        if(formato.equals("VHS")) 	  imgFormato.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vhssmall, 0, 0, 0); // right,top,left,bottom
        if(formato.equals("DVD")) 	  imgFormato.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.dvd, 0, 0, 0);
        if(formato.equals("Blu-Ray")) imgFormato.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.bluray, 0, 0, 0);
        
        String notas = lista.get(position).getNotas();
        if(notas.equals("")) 	  imgNotas.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.uncheck1, 0, 0, 0);
        else imgNotas.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.check1, 0, 0, 0);
        
        String prestado_a = lista.get(position).getPrestado_a();
        if(prestado_a.equals("")) 	  imgPrestado.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.uncheck1, 0, 0, 0);
        else imgPrestado.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.check1, 0, 0, 0);
        

        
        String fechaFin = lista.get(position).getFechaFin();
        Date dateFin;
        Date nowDate = new Date(System.currentTimeMillis());
        try{
        	SimpleDateFormat fechaForm = new SimpleDateFormat("dd/MM/yyyy"); // here set the pattern as you date in string was containing like date/month/year
        	dateFin = fechaForm.parse(fechaFin.toString());
        	int diferenciaFechas = nowDate.compareTo(dateFin);
        	
        	//La fecha actual es mayor que la fecha indicada "dateFin"
        	if(diferenciaFechas>0)  imgFinalizado.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.check1, 0, 0, 0);
        	//La fecha actual es menor que la fecha indicada "dateFin"
        	if(diferenciaFechas<0)  imgFinalizado.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.uncheck1, 0, 0, 0);
        	//Las dos fechas son iguales
        	if(diferenciaFechas==0) imgFinalizado.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.uncheck1, 0, 0, 0);
        	
        	
        	} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        
        
        return view;
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

