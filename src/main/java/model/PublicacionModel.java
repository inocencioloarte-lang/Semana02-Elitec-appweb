package model;

import java.sql.Connection;
import java.sql.PreparedStatement;


import entity.Publicacion;
import util.MySqlDBConexion;

public class PublicacionModel {

	public int insertaPublicacion(Publicacion obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();
			
			//2 Crear el SQL de insercion
			String sql = "INSERT INTO publicacion (usuario, titulo, contenido, referencias, fecha) VALUES (?,?,?,?,?)";	
			
			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getUsuario());
			ps.setString(2, obj.getTitulo());
			ps.setString(3, obj.getContenido());
			ps.setString(4, obj.getReferencias());
			ps.setDate(5, java.sql.Date.valueOf(obj.getFecha()));
			
			System.out.println("SQL: " + ps);
			
			//4 Ejecutar el SQL	
			salida = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	

		return salida;
	}
}