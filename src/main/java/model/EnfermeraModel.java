package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Enfermera;
import util.MySqlDBConexion;

public class EnfermeraModel {

	public int insertaEnfermera(Enfermera obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();

			//2 Crear el SQL de insercion
			String sql = "INSERT INTO enfermera (nombres, apellidos, dni, fecha_nacimiento, especialidad, telefono, turno) VALUES (?,?,?,?,?,?,?)";

			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getNombres());
			ps.setString(2, obj.getApellidos());
			ps.setString(3, obj.getDni());
			ps.setDate(4, java.sql.Date.valueOf(obj.getFechaNacimiento()));
			ps.setString(5, obj.getEspecialidad());
			ps.setString(6, obj.getTelefono());
			ps.setString(7, obj.getTurno());

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

	public List<Enfermera> listaEnfermeraPorNombre (String nombres){
		ArrayList<Enfermera> salida = new ArrayList<Enfermera>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 se crea conexion
			conn = MySqlDBConexion.getConexion();

			//2 se prepara la sentencia SQL
			String sql = "SELECT * FROM enfermera WHERE nombres LIKE ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + nombres + "%");

			//3 se ejecuta la consulta
			rs = pstm.executeQuery();

			while (rs.next()) {
				Enfermera obj = new Enfermera();
				obj.setIdEnfermera(rs.getInt("idenfermera"));
				obj.setNombres(rs.getString("nombres"));
				obj.setApellidos(rs.getString("apellidos"));
				obj.setDni(rs.getString("dni"));
				obj.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
				obj.setEspecialidad(rs.getString("especialidad"));
				obj.setTelefono(rs.getString("telefono"));
				obj.setTurno(rs.getString("turno"));

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