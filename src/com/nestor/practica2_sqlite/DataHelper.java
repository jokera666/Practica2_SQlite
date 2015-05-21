package com.nestor.practica2_sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DataHelper{//mi clase adaptadora para definir y crear la base de datos
	private static final String NOMBRE_BD = "bdPeliculas.db";
	private static final int VERSION_BD = 1;
	private static final String TABLA_NOMBRE_PELICULAS ="peliculas";
	private static final String[] ATRIBUTOS_PELICULAS = {"_id",	 			  // [0] PRIMARY KEY AUTOINCREMNET
														 "genero",			  // [1]
														 "titulo",			  // [2]
														 "director",		  // [3]
														 "idioma",			  // [4]
														 "fecha_ini_prestamo",// [5]
														 "fecha_fin_prestamo",// [6]
														 "prestado_a",		  // [7]	
														 "valoracion",		  // [8]
														 "formato",			  // [9]	
														 "notas"};			  //[10]
	private static final String CREAR_TABLA_PELICULAS = "CREATE TABLE "+TABLA_NOMBRE_PELICULAS+" ( "+ATRIBUTOS_PELICULAS[0]+" long primary key autoincrement,"
																								   	+ATRIBUTOS_PELICULAS[1]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[2]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[3]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[4]+" text,"
																								   	+ATRIBUTOS_PELICULAS[5]+" text,"
																								   	+ATRIBUTOS_PELICULAS[6]+" text,"
																								   	+ATRIBUTOS_PELICULAS[7]+" text,"
																								   	+ATRIBUTOS_PELICULAS[8]+" double,"
																								   	+ATRIBUTOS_PELICULAS[9]+" text,"
																								   	+ATRIBUTOS_PELICULAS[10]+" text );";
	//propiedad para insertar una pelicula para lanzarla al compileStatemant
	private static final String INSERT = "INSERT INTO "+TABLA_NOMBRE_PELICULAS+"( "+ATRIBUTOS_PELICULAS[1]+","
																				  +ATRIBUTOS_PELICULAS[2]+","
																				  +ATRIBUTOS_PELICULAS[3]+","
																				  +ATRIBUTOS_PELICULAS[4]+","
																				  +ATRIBUTOS_PELICULAS[5]+","
																				  +ATRIBUTOS_PELICULAS[6]+","
																				  +ATRIBUTOS_PELICULAS[7]+","
																				  +ATRIBUTOS_PELICULAS[8]+","
																				  +ATRIBUTOS_PELICULAS[9]+","
																				  +ATRIBUTOS_PELICULAS[10]+")"
																				  +" VALUES (?,?,?,?,?,?,?,?,?,?);";
	private static final String UPDATE = "UPDATE "+TABLA_NOMBRE_PELICULAS+" SET "+ATRIBUTOS_PELICULAS[1]+"= ? WHERE "+ATRIBUTOS_PELICULAS[0]+"=?";
	// propiedades para preparar y atacar la base de datos
	private Context contexto;
	private SQLiteDatabase db;
	private SQLiteStatement statemantInsertar;
	private SQLiteStatement statemantModificar;

	//Constructor por defecto del adaptador dataHelper
	public DataHelper (Context context){ // hay que pasarle el contexto de la clase donde se crea el objeto
		this.contexto = context;
		MiOpenHelper openHelper = new MiOpenHelper(this.contexto);
		this.db = openHelper.getWritableDatabase();
		this.statemantInsertar = this.db.compileStatement(INSERT); // es lo mismo db.execSQL(INSERT); // compara el insert
		this.statemantModificar = this.db.compileStatement(UPDATE); // es lo mismo db.execSQL(UPDATE);
		
	}
	
	public class MiOpenHelper extends SQLiteOpenHelper
	{	
		//constructor por defecto de miOpenHelper
			public MiOpenHelper(Context context){
			super(context,NOMBRE_BD,null,VERSION_BD);// le paso en contexto, el nombre de la BD, el cursor null y la version de la BD
		}
			
		@Override
		public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREAR_TABLA_PELICULAS);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE si existe la tabla " + NOMBRE_BD);
		onCreate(db); //Eliminamos si existe y la volvemos a crear
		}
	}
	
	public int borrarTodo()
	{
		return db.delete(TABLA_NOMBRE_PELICULAS, null, null);
	}
		
		
		//AQUI YA EMPEZAMOS A CREAR NUESTROS PROPIOS METODOS PARA ATACAR A LA BASE DE DATOS
		public long insertar(String genero, String titulo, String director, String idioma, String fecha_ini, String fecha_fin, String prestado_a, double valoracion, String formato, String notas)
		{
			statemantInsertar.bindString(1,genero);
			statemantInsertar.bindString(2,titulo);
			statemantInsertar.bindString(3,director);
			statemantInsertar.bindString(4,idioma);
			statemantInsertar.bindString(5,fecha_ini);
			statemantInsertar.bindString(6,fecha_fin);
			statemantInsertar.bindString(7,prestado_a);
			statemantInsertar.bindDouble(8,valoracion);
			statemantInsertar.bindString(9,formato);
			statemantInsertar.bindString(10,notas);
			
			return statemantInsertar.executeInsert();
		}
		
		public long modificar(String newGenero, long id)
		{
			statemantModificar.bindString(1, newGenero); // los 1, 2 son el ? que se pasan en la en la preparacion del statement de las propiedades
			statemantModificar.bindLong(2, id);
			return statemantInsertar.executeUpdateDelete();
		}
		
		public List<String> mostrarTodo()
		{
			List<String> lista = new ArrayList<String>();
			//String atributos[] = new String[]{"genero","titulo","director","idioma","fecha_ini_prestamo","fecha_fin_prestamo","prestado_a","valoracion","formato","notas"};
			Cursor rs = db.query(TABLA_NOMBRE_PELICULAS, ATRIBUTOS_PELICULAS, null, null, null, null,null);
			if ( rs.moveToFirst() )
			{
				do
				{
					lista.add(rs.getString(0)); //El 0º param es el ID
					lista.add(rs.getString(1)); //El 1º parám. es el genero
					lista.add(rs.getString(2)); //El 2º parám. es el titulo
					lista.add(rs.getString(3)); //El 3º parám. es el director
					lista.add(rs.getString(4)); //El 4º parám. es el idioma
					lista.add(rs.getString(5)); //El 5º parám. es el fecha_ini_prestamo
					lista.add(rs.getString(6)); //El 6º parám. es el fecha_fin_prestamo
					lista.add(rs.getString(7)); //El 7º parám. es el prestado_a
					lista.add(rs.getString(8)); //El 8º parám. es el valoracion
					lista.add(rs.getString(9)); //El 9º parám. es el formato
					lista.add(rs.getString(10)); //El 10º parám. es el notas
				}while (rs.moveToNext());
			}
			
			if ( rs!= null && !rs.isClosed() )
			{
				rs.close();
			}
			
			return lista;
		}	
}