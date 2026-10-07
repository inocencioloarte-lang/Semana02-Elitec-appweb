package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Enfermera;
import util.MySqlDBConexion;

public class EnfermeraModel {

	public int registrarEnfermera(Enfermera enfermera) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into enfermera(nombres,apellidos,dni,fecha_nacimiento,especialidad,telefono,turno) values (?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, enfermera.getNombres());
			pstm.setString(2, enfermera.getApellidos());
			pstm.setString(3, enfermera.getDni());
			pstm.setDate(4, java.sql.Date.valueOf(enfermera.getFechaNacimiento()));
			pstm.setString(5, enfermera.getEspecialidad());
			pstm.setString(6, enfermera.getTelefono());
			pstm.setString(7, enfermera.getTurno());
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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