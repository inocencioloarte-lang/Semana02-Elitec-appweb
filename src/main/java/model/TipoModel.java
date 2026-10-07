package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Tipo;

public class TipoModel {

	public List<Tipo> listaTipo(){
		ArrayList<Tipo> lista = new ArrayList<Tipo>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
            conn = util.MySqlDBConexion.getConexion();
            String sql = "select * from tipo";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setIdTipo(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descripcion"));
                lista.add(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				if (rs != null)		rs.close();
				if (pstm != null)	pstm.close();
				if (conn != null)	conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
		return lista;
	}
}	