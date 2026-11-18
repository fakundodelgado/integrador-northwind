package aplicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class Operador {

	// Se usa el throws porque lo que se busca es que el error "suba" a la interfaz tal como se produce en la base de datos.
	public String abm(String accion, String customerid, String companyname, String contactname, String contacttitle, 
			String address, String city, String region, String postalcode, String country, String phone, String fax) throws SQLException {
		
		/*
		 * Los once parametros que debe recibir la funcion para operar sobre la tabla customers son los siguientes:
		 * (?,?,?,?,?,?,?,?,?,?,?,?)
		 * (accion, customerid, companyname, contactname, contacttitle, address, city, region, postalcode, country, phone, fax)
		 */
		
		/* 
		 * Esta es la forma que tendra el llamado a la funcion, ahora debo poder reemplazar los ? por los valores correspondientes. 
		 * Para eso se puede utilizar el metodo propio de la clase Connection prepareStatement que devuelve un PreparedStatement que es una especie
		 * de objeto parametrizado que ayuda a justamente, pasar los parametros para la ejecución de la sentencia.
		 */
		String sql = "select fn_abm_cliente(?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = Conexion.getConexion();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			/*
			 * Uno por uno pasamos los parametros en el orden en que los definimos mas arriba, aparentemente por como funciona el driver JDBC el orden de los parametros
			 * inicia en 1 a diferencia de como se trabaja con los indices en un array. Al parecer esto se debe a como se trabaja con las columnas en las bases de datos relacionales
			 * normalmente cuando uno por ejemplo usa un exists y medio que no importa las columnas pone un 1, o por ejemplo cuando quiero ordernar por algo (usualmente la pk) simplemente
			 * hago order by 1 y se ordenara en base a la primera columna que se definio a la hora de crear la tabla.
			 */
			
			ps.setString(1, accion);
			ps.setString(2, customerid);
			ps.setString(3, companyname);
			ps.setString(4, contactname);
			ps.setString(5, contacttitle);
			ps.setString(6, address);
			ps.setString(7, city);
			ps.setString(8, region);
			ps.setString(9, postalcode);
			ps.setString(10, country);
			ps.setString(11, phone);
			ps.setString(12, fax);
			
			/*
			 * Demas esta decir lo que hace executeQuery() sin embargo es util aclarar que devuelve un ResultSet que basicamente los mensajes de exito de la funcion que definimos.
			 */
			try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
			
			return "Operación finalizada correctamente.";
			
		}
		
	}
	
	
	//-----------------------NUEVO-------------------------------------	
	
	/*
	 * DefaultTableModel es una clase de Java (paquete javax.swing.table) que actúa como 
	 * el modelo de datos predeterminado para un componente JTable en interfaces gráficas Swing.
	 * Separa los datos de la interfaz visual: mientras que JTable se encarga de dibujar la tabla en la 
	 * pantalla, DefaultTableModel se encarga de almacenar, organizar y manipular la información que hay 
	 * dentro de ella (filas y columnas).
	 */

	public DefaultTableModel consulta_ord(String cliente, String empleado) throws SQLException {
        // Definimos las columnas de la tabla, aqui meteremos los datos
        String[] columnas = {"Cliente_id", "cliente_contacto", "orden_id", "orden_fecha", "orden_cantidad_items","orden_importe_total","empleado_id","empleado_nombre"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que el usuario edite las celdas directamente
            }
        };
        
        String sql = "select * from fn_consulta_ordenes(?, ?)";
        
        try (Connection con = Conexion.getConexion();
        	PreparedStatement ps = con.prepareStatement(sql)) {

            // Pasamos los dos parámetros
            ps.setString(1, cliente);
            ps.setString(2, empleado);

            // Ejecutamos y recorremos los resultados
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = {
                    	rs.getString("Cliente_id"),
                    	rs.getString("cliente_contacto"),
                    	rs.getInt("orden_id"),
                    	rs.getDate("orden_fecha"),
                    	rs.getInt("orden_cantidad_items"),
                    	rs.getFloat("orden_importe_total"),
                        rs.getInt("empleado_id"),
                        rs.getString("empleado_nombre")
                    };
                    modelo.addRow(fila);
                }
            }
        }
        return modelo;
    }
	
	
	public DefaultTableModel consulta_cli(String cliente, String empleado) throws SQLException {
        // Definimos las columnas de la tabla, aqui meteremos los datos
        String[] columnas = {"id", "nombre_de_compania", "nombre_de_contacto ", "pais", "ciudad","direccion","ordenes_registradas","total_gastado"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que el usuario edite las celdas directamente
            }
        };
        
        String sql = "select * from fn_consulta_clientes(?, ?)";
        
        try (Connection con = Conexion.getConexion();
        	PreparedStatement ps = con.prepareStatement(sql)) {

            // Pasamos los dos parámetros
            ps.setString(1, cliente);
            ps.setString(2, empleado);

            // Ejecutamos y recorremos los resultados
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = {
                    	rs.getString("id"),
                    	rs.getString("nombre_de_compania"),
                    	rs.getString("nombre_de_contacto"),
                    	rs.getString("pais"),
                    	rs.getString("ciudad"),
                    	rs.getString("direccion"),
                        rs.getInt("ordenes_registradas"),
                        rs.getFloat("total_gastado")
                    };
                    modelo.addRow(fila);
                }
            }
        }
        return modelo;
    }
	
	
}
