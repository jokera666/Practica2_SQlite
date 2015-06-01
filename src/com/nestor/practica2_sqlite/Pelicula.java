package com.nestor.practica2_sqlite;

public class Pelicula {
	
	//Propiedades de la pelicula
	int id;
	String genero;
	String titulo;
	String director;
	String idioma;
	String formato;
	String fecha_ini_prestamo;
	String fecha_fin_prestamo;
	String prestado_a;
	String notas;
	long valoracion;
	
	//constructor por defecto
	public Pelicula(int id,
					String genero,
					String titulo,
					String director,
					String idioma,
					String formato,
					String fecha_ini,
					String fecha_fin,
					String prestado_a,
					String notas,
					long valoracion)
					{
					
						this.id = id;
						this.genero = genero;
						this.titulo = titulo;
						this.director = director;
						this.idioma = idioma;
						this.formato = formato;
						this.fecha_ini_prestamo = fecha_ini;
						this.fecha_fin_prestamo = fecha_fin;
						this.prestado_a = prestado_a;
						this.notas = notas;
						this.valoracion = valoracion;
					}
	
	//SETERS
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setGenero(String genero)
	{
		this.genero = genero;
	}
	
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	
	public void setDirector(String director)
	{
		this.director = director;
	}
	
	public void setIdioma(String idioma)
	{
		this.idioma = idioma;
	}
	
	public void setFormato(String formato)
	{
		this.formato = formato;
	}
	
	public void setFechaIni(String fecha_ini)
	{
		this.fecha_ini_prestamo = fecha_ini;
	}
	
	public void setFechaFin(String fecha_fin)
	{
		this.fecha_fin_prestamo = fecha_fin;
	}
	
	public void setPrestado_a(String prestado_a)
	{
		this.prestado_a = prestado_a;
	}
	
	public void setNotas(String notas)
	{
		this.notas = notas;
	}
	
	public void setValoracion(long valoracion)
	{
		this.valoracion = valoracion;
	}
	
	
	//GETERS
	public int getId()
	{
		return id;
	}
	
	public String getGenero()
	{
		return genero;
	}
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public String getDirector()
	{
		return director;
	}
	
	public String getIdioma()
	{
		return idioma;
	}
	
	public String getFormato()
	{
		return formato;
	}
	
	public String getFechaIni()
	{
		return fecha_ini_prestamo;
	}
	
	public String getFechaFin()
	{
		return fecha_fin_prestamo;
	}
	
	public String getPrestado_a()
	{
		return prestado_a;
	}
	
	public String getNotas()
	{
		return notas;
	}
	
	public long getValoracion()
	{
		return valoracion;
	}
}