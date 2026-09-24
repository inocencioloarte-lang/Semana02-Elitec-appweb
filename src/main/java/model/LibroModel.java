package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Libro;
import util.MySqlDBConexion;

public class LibroModel {

	public int insertaLibro(Libro obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();
			
			//2 Crear el SQL de insercion
			String sql = "INSERT INTO libro (registro, titulo, pais, autor, fechaCreacion) VALUES (?,?,?,?,?)";
			
			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getRegistro());
			ps.setString(2, obj.getTitulo());
			ps.setString(3, obj.getPais());
			ps.setString(4, obj.getAutor());
			ps.setDate(5, java.sql.Date.valueOf(obj.getFechaCreacion()));
			
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
	
	public List<Libro> listaLibroPorTitulo (String titulo){
		ArrayList<Libro> salida = new ArrayList<Libro>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 se crea conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara la sentencia SQL
			String sql = "SELECT * FROM libro WHERE titulo LIKE ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + titulo + "%");
			
			//3 se ejecuta la consulta
			rs = pstm.executeQuery();

			while (rs.next()) {
				Libro obj = new Libro();
				obj.setIdLibro(rs.getInt("idLibro"));
				obj.setRegistro(rs.getString("registro"));
				obj.setTitulo(rs.getString("titulo"));
				obj.setPais(rs.getString("pais"));
				obj.setAutor(rs.getString("autor"));
				obj.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());

				salida.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}
	
}






