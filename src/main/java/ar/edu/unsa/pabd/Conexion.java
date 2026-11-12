package ar.edu.unsa.pabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

		/*
		 * Lo primero es armar la url que se compone de 4 partes:
		 * - La primera: jdbc:postgresql, indica el driver que se usara que es el que se le indico al maven project.
		 * - La segunda: La direccion que usa la base de datos dentro del sistema, que por defecto es localhost.
		 * - La tercera: El puerto que se usa para la conexion, que por defecto es 5432 (lo use como 40 veces para el TP7).
		 * - La cuarta: La base de datos que vamos a usar.
		 */
		
		private static final String url = "jdbc:postgresql://localhost:5432/northwind";
		
		/*
		 * Luego debo tener mi usuario y contraseña listos para poder acceder a la base de datos.
		 */
		
		private static final String usuario = "postgres";
		private static final String contrasena = "123456789";/*admin_1234*/
		
		/*
		 * Ahora dado que una conexion puede fallar lo que hacemos es armar un try catch donde intentaremos establecer la conexion y guardarla
		 * en un objeto de tipo... Connection. Lo que se hace es usar DriverManager una clase funcional de java que permite ejecutar el metodo
		 * getConnection mediante el cual, si se ingresa una url, usuario y contraseña validos, se permite instanciar un objeto de tipo conexion con el
		 * que podemos trabajar.
		 */
		
		public static Connection getConexion() throws SQLException {
	        return DriverManager.getConnection(url, usuario, contrasena);
	    }
		
}


