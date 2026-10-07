package controller;

import java.io.IOException;
import java.time.LocalDate;

import entity.Enfermera;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EnfermeraModel;

@WebServlet("/registroEnfermeraAlias")
public class RegistroEnfermeraServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1 Recibir los datos del formulario del JSP
		String nombres = req.getParameter("nombres");
		String apellidos = req.getParameter("apellidos");
		String dni = req.getParameter("dni");
		String fechaNacimiento = req.getParameter("fechaNacimiento");
		String especialidad = req.getParameter("especialidad");
		String telefono = req.getParameter("telefono");
		String turno = req.getParameter("turno");

		//2 Crear un objeto Enfermera
		Enfermera objEnfermera = new Enfermera();
		objEnfermera.setNombres(nombres);
		objEnfermera.setApellidos(apellidos);
		objEnfermera.setDni(dni);
		objEnfermera.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
		objEnfermera.setEspecialidad(especialidad);
		objEnfermera.setTelefono(telefono);
		objEnfermera.setTurno(turno);

		//3 Crear un objeto EnfermeraModel
		EnfermeraModel model = new EnfermeraModel();
		int salida = model.registrarEnfermera(objEnfermera);
		String mensajeSalida = (salida > 0) ? "Enfermera registrada correctamente (OK)" : "Error al registrar la enfermera";

		//4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
	}

}