package controller;

import java.io.IOException;

import entity.Enfermera;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EnfermeraModel;

@WebServlet("/registraEnfermeraAlias")
public class RegistraEnfermeraServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1 Recibir los datos del formulario del JSP
		String nombres = req.getParameter("nombres");
		String apellidos = req.getParameter("apellidos");
		String dni = req.getParameter("dni");
		String especialidad = req.getParameter("especialidad");
		String telefono = req.getParameter("telefono");
		String turno = req.getParameter("turno");
		String fechaNacimiento = req.getParameter("fechaNacimiento");

		System.out.println(
				"Datos recibidos: " + nombres + " - " + apellidos + " - " + dni + " - " + especialidad + " - " + telefono + " - " + turno + " - " + fechaNacimiento);

		// 2 Crear un objeto Enfermera
		Enfermera enfermera = new Enfermera();
		enfermera.setNombres(nombres);
		enfermera.setApellidos(apellidos);
		enfermera.setDni(dni);
		enfermera.setEspecialidad(especialidad);
		enfermera.setTelefono(telefono);
		enfermera.setTurno(turno);
		enfermera.setFechaNacimiento(java.time.LocalDate.parse(fechaNacimiento));

		// 3 Crear un objeto EnfermeraModel
		EnfermeraModel model = new EnfermeraModel();
		int salida = model.insertaEnfermera(enfermera);

		String mensajeSalida = (salida > 0) ? "Enfermera registrada correctamente (OK)" : "Error al registrar la enfermera";

		// 4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
	}
}