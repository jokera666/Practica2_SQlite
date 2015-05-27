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
														 "formato",			  // [5]
														 "fecha_ini_prestamo",// [6]
														 "fecha_fin_prestamo",// [7]
														 "prestado_a",		  // [8]	
														 "notas",			  // [9]
														 "valoracion"};		  //[10]
	private static final String CREAR_TABLA_PELICULAS = "CREATE TABLE "+TABLA_NOMBRE_PELICULAS+" ( "+ATRIBUTOS_PELICULAS[0]+" long primary key autoincrement,"
																								   	+ATRIBUTOS_PELICULAS[1]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[2]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[3]+" text not null,"
																								   	+ATRIBUTOS_PELICULAS[4]+" text,"
																								   	+ATRIBUTOS_PELICULAS[5]+" text,"
																								   	+ATRIBUTOS_PELICULAS[6]+" text,"
																								   	+ATRIBUTOS_PELICULAS[7]+" text,"
																								   	+ATRIBUTOS_PELICULAS[8]+" text,"
																								   	+ATRIBUTOS_PELICULAS[9]+" text,"
																								   	+ATRIBUTOS_PELICULAS[10]+" long );";
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
	private static final String UPDATE = "UPDATE "+TABLA_NOMBRE_PELICULAS+" SET "+ATRIBUTOS_PELICULAS[1]+"= ?, "+ATRIBUTOS_PELICULAS[2]+"= ?, "+ATRIBUTOS_PELICULAS[3]+"= ?, "+ATRIBUTOS_PELICULAS[4]+"= ?, "+ATRIBUTOS_PELICULAS[5]+"= ?, "+ATRIBUTOS_PELICULAS[6]+"= ?, "+ATRIBUTOS_PELICULAS[7]+"= ?, "+ATRIBUTOS_PELICULAS[8]+"= ?, "+ATRIBUTOS_PELICULAS[9]+"= ?, "+ATRIBUTOS_PELICULAS[10]+"= ? WHERE "+ATRIBUTOS_PELICULAS[0]+"=?";
	private static final String DELETE = "DELETE FROM "+TABLA_NOMBRE_PELICULAS+" WHERE "+ATRIBUTOS_PELICULAS[0]+"=?";
	// propiedades para preparar y atacar la base de datos
	private Context contexto;
	private SQLiteDatabase db;
	private SQLiteStatement statemantInsertar;
	private SQLiteStatement statemantModificar;
	private SQLiteStatement statemantBorrar;

	//Constructor por defecto del adaptador dataHelper
	public DataHelper (Context context){ // hay que pasarle el contexto de la clase donde se crea el objeto
		this.contexto = context;
		MiOpenHelper openHelper = new MiOpenHelper(this.contexto);
		this.db = openHelper.getWritableDatabase();
		this.statemantInsertar = this.db.compileStatement(INSERT); // es lo mismo db.execSQL(INSERT); // compara el insert
		this.statemantModificar = this.db.compileStatement(UPDATE); // es lo mismo db.execSQL(UPDATE);
		this.statemantBorrar = this.db.compileStatement(DELETE); // es lo mismo db.execSQL(UPDATE);
		
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
		public long insertar(String genero, String titulo, String director, String idioma, String formato, String fecha_ini, String fecha_fin, String prestado_a, String notas,long valoracion)
		{
			statemantInsertar.bindString(1,genero);
			statemantInsertar.bindString(2,titulo);
			statemantInsertar.bindString(3,director);
			statemantInsertar.bindString(4,idioma);
			statemantInsertar.bindString(5,formato);
			statemantInsertar.bindString(6,fecha_ini);
			statemantInsertar.bindString(7,fecha_fin);
			statemantInsertar.bindString(8,prestado_a);
			statemantInsertar.bindString(9,notas);
			statemantInsertar.bindLong(10,valoracion);
			
			
			return statemantInsertar.executeInsert();
		}
		
		public long borrar(long id)
		{
			statemantBorrar.bindLong(1, id);
			return statemantBorrar.executeUpdateDelete();
		}
		
		public long modificar(String newGenero, String newTitulo, String newDirector, String newIdioma, String newFormato, String newFecha_ini, String newFecha_fin, String newPrestado_a, String newNotas,long newValoracion, long id)
		{
			statemantModificar.bindString(1, newGenero); // los 1, 2 son el ? que se pasan en la en la preparacion del statement de las propiedades
			statemantModificar.bindString(2, newTitulo);
			statemantModificar.bindString(3, newDirector);
			statemantModificar.bindString(4, newIdioma);
			statemantModificar.bindString(5, newFormato);
			statemantModificar.bindString(6, newFecha_ini);
			statemantModificar.bindString(7, newFecha_fin);
			statemantModificar.bindString(8, newPrestado_a);
			statemantModificar.bindString(9, newNotas);
			statemantModificar.bindLong(10, newValoracion);
			statemantModificar.bindLong(11, id);
			return statemantModificar.executeUpdateDelete();
		}
		
		public ArrayList<Pelicula> mostrarTodo()
		{
			ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
			//String atributos[] = new String[]{"genero","titulo","director","idioma","fecha_ini_prestamo","fecha_fin_prestamo","prestado_a","valoracion","formato","notas"};
			//String casa = "hola";
			//String id[] = new String[]{"id"};
			//String arg[] = new String[]{"2"};
			Cursor rs = db.query(TABLA_NOMBRE_PELICULAS, ATRIBUTOS_PELICULAS, null, null, null, null,null);
			if ( rs.moveToFirst() )
			{
				do
				{
					lista.add(new Pelicula (rs.getLong(0),
											rs.getString(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getString(7),
											rs.getString(8),
											rs.getString(9),
											rs.getLong(10)));
					
//					lista.add(rs.getString(0)); //El 0º param es el ID
//					lista.add(rs.getString(1)); //El 1º parám. es el genero
//					lista.add(rs.getString(2)); //El 2º parám. es el titulo
//					lista.add(rs.getString(3)); //El 3º parám. es el director
//					lista.add(rs.getString(4)); //El 4º parám. es el idioma
//					lista.add(rs.getString(5)); //El 5º parám. es el formato
//					lista.add(rs.getString(6)); //El 6º parám. es el fecha_ini_prestamo
//					lista.add(rs.getString(7)); //El 7º parám. es el fecha_fin_prestamo
//					lista.add(rs.getString(8)); //El 8º parám. es el prestado_a
//					lista.add(rs.getString(9)); //El 9º parám. es el notas
//					lista.add(rs.getString(10)); //El 10º parám. es el valoracion
				}while (rs.moveToNext());
			}
			
			if ( rs!= null && !rs.isClosed() )
			{
				rs.close();
			}
			
			return lista;
		}	
		
		// No hace falta crear una una array de Pelicula ya que devuelve un registro
		public Pelicula mostrarPelicula(long id)
		{
			Pelicula peli = null;
			//String atributos[] = new String[]{"genero","titulo","director","idioma","fecha_ini_prestamo","fecha_fin_prestamo","prestado_a","valoracion","formato","notas"};
			//String casa = "hola";
			String newid[] = new String[]{Long.toString(id)};
			
			Cursor rs = db.query(TABLA_NOMBRE_PELICULAS, ATRIBUTOS_PELICULAS, "_id=?", newid, null, null,null);
			if ( rs.moveToFirst() )
			{
				
				 peli = new Pelicula (rs.getLong(0),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getLong(10));
			}
			
			if ( rs!= null && !rs.isClosed() )
			{
				rs.close();
			}
			
			return peli;
		}			
}