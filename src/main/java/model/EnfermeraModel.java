package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Enfermera;
import util.MySqlDBConexion;

public class EnfermeraModel {

    // Columnas reales de la tabla: idenfermera, nombres, apellidos, dni,
    // fecha_nacimiento, especialidad, telefono, turno

    // Registrar enfermera
    public int registrarEnfermera(Enfermera enfermera) {
        int salida = -1;
        String sql = "INSERT INTO enfermera "
                + "(nombres, apellidos, dni, fechaNacimiento, especialidad, telefono, turno) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySqlDBConexion.getConexion();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, enfermera.getNombres());
            pstm.setString(2, enfermera.getApellidos());
            pstm.setString(3, enfermera.getDni());

            if (enfermera.getFechaNacimiento() != null) {
                pstm.setDate(4, Date.valueOf(enfermera.getFechaNacimiento()));
            } else {
                pstm.setDate(4, null);
            }

            pstm.setString(5, enfermera.getEspecialidad());
            pstm.setString(6, enfermera.getTelefono());
            pstm.setString(7, enfermera.getTurno());

            System.out.println("SQL ==> " + pstm);
            salida = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return salida;
    }

    // Listar enfermeras por nombres
    public List<Enfermera> listaPorNombreLike(String nombres) {
        List<Enfermera> salida = new ArrayList<>();
        String sql = "SELECT idenfermera, nombres, apellidos, dni, fechaNacimiento, "
                + "especialidad, telefono, turno "
                + "FROM enfermera WHERE nombres LIKE ?";

        try (Connection conn = MySqlDBConexion.getConexion();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, "%" + nombres + "%");
            System.out.println("SQL ==> " + pstm);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    salida.add(mapear(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return salida;
    }

    // Actualizar enfermera
    public int actualizarEnfermera(Enfermera enfermera) {
        int salida = -1;
        String sql = "UPDATE enfermera SET nombres=?, apellidos=?, dni=?, "
                + "fechaNacimiento=?, especialidad=?, telefono=?, turno=? "
                + "WHERE idenfermera=?";

        try (Connection conn = MySqlDBConexion.getConexion();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, enfermera.getNombres());
            pstm.setString(2, enfermera.getApellidos());
            pstm.setString(3, enfermera.getDni());

            if (enfermera.getFechaNacimiento() != null) {
                pstm.setDate(4, Date.valueOf(enfermera.getFechaNacimiento()));
            } else {
                pstm.setDate(4, null);
            }

            pstm.setString(5, enfermera.getEspecialidad());
            pstm.setString(6, enfermera.getTelefono());
            pstm.setString(7, enfermera.getTurno());
            pstm.setInt(8, enfermera.getIdEnfermera());

            System.out.println("SQL ==> " + pstm);
            salida = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return salida;
    }

    // Eliminar enfermera físicamente
    public int eliminarEnfermera(int idEnfermera) {
        int salida = -1;
        String sql = "DELETE FROM enfermera WHERE idenfermera=?";

        try (Connection conn = MySqlDBConexion.getConexion();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idEnfermera);

            System.out.println("SQL ==> " + pstm);
            salida = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return salida;
    }

    // Buscar enfermera por ID
    public Enfermera buscarPorId(int idEnfermera) {
        Enfermera enfermera = null;
        String sql = "SELECT idenfermera, nombres, apellidos, dni, fechaNacimiento, "
                + "especialidad, telefono, turno "
                + "FROM enfermera WHERE idenfermera=?";

        try (Connection conn = MySqlDBConexion.getConexion();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idEnfermera);
            System.out.println("SQL ==> " + pstm);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    enfermera = mapear(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return enfermera;
    }

    // Convierte la fila actual del ResultSet en un objeto Enfermera
    private Enfermera mapear(ResultSet rs) throws Exception {
        Enfermera enfermera = new Enfermera();

        enfermera.setIdEnfermera(rs.getInt("idenfermera"));
        enfermera.setNombres(rs.getString("nombres"));
        enfermera.setApellidos(rs.getString("apellidos"));
        enfermera.setDni(rs.getString("dni"));

        Date fecha = rs.getDate("fechaNacimiento");
        if (fecha != null) {
            enfermera.setFechaNacimiento(fecha.toLocalDate());
        }

        enfermera.setEspecialidad(rs.getString("especialidad"));
        enfermera.setTelefono(rs.getString("telefono"));
        enfermera.setTurno(rs.getString("turno"));

        return enfermera;
    }
}