package com.nestor.practica2_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DataHelper {//mi clase adaptadora para definir y crear la base de datos
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
	private static final String CREAR_TABLA_PELICULAS = "CREATE TABLE "+TABLA_NOMBRE_PELICULAS+" ( "+ATRIBUTOS_PELICULAS[0]+" integer primary key autoincrement,"
																								   	+ATRIBUTOS_PELICULAS[1]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[2]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[3]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[4]+" text,"
																								   	+ATRIBUTOS_PELICULAS[5]+" text,"
																								   	+ATRIBUTOS_PELICULAS[6]+" text,"
																								   	+ATRIBUTOS_PELICULAS[7]+" text,"
																								   	+ATRIBUTOS_PELICULAS[8]+" float,"
																								   	+ATRIBUTOS_PELICULAS[9]+" text,"
																								   	+ATRIBUTOS_PELICULAS[10]+" text )";
	//propiedad para insertar una pelicula para lanzarla al compileStatemant
	private static final String INSERT = "INSERT INTO"+TABLA_NOMBRE_PELICULAS+"( "+ATRIBUTOS_PELICULAS[1]+","
																				  +ATRIBUTOS_PELICULAS[2]+","
																				  +ATRIBUTOS_PELICULAS[3]+","
																				  +ATRIBUTOS_PELICULAS[4]+","
																				  +ATRIBUTOS_PELICULAS[5]+","
																				  +ATRIBUTOS_PELICULAS[6]+","
																				  +ATRIBUTOS_PELICULAS[7]+","
																				  +ATRIBUTOS_PELICULAS[8]+","
																				  +ATRIBUTOS_PELICULAS[9]+","
																				  +ATRIBUTOS_PELICULAS[10]+")"
																				  +" VALUES (?,?,?,?,?,?,?,?,?,?)";
	// propiedades para preparar y atacar la base de datos
	private Context contexto;
	private SQLiteDatabase db;
	private SQLiteStatement statemantInsertar;

	//Constructor por defecto del adaptador dataHelper
	public DataHelper (Context context){ // hay que pasarle el contexto de la clase donde se crea el objeto
		this.contexto = context;
		MiOpenHelper openHelper = new MiOpenHelper(this.contexto);
		this.db = openHelper.getWritableDatabase();
		this.statemantInsertar = this.db.compileStatement(INSERT);
	}
	
	public class MiOpenHelper extends SQLiteOpenHelper{
		
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
		
		
		//AQUI YA EMPEZAMOS A CREAR NUESTROS PROPIOS METODOS PARA ATACAR A LA BASE DE DATOS
		
	}
																											

}
