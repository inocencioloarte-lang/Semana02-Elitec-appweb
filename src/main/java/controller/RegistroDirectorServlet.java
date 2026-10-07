package controller;

import java.io.IOException;

import entity.Director;
import entity.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DirectorModel;

@WebServlet("/registroDirectorAlias")
public class RegistroDirectorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1 Recibir los datos del formulario del JSP
		String nombre = req.getParameter("nombre");
		String email = req.getParameter("email");
		String tipo = req.getParameter("tipo");
		
		//2 Crear un objeto Director y tipo
		Tipo objTipo = new Tipo();
		objTipo.setIdTipo(Integer.parseInt(tipo));
		
		Director objDirector = new Director();
		objDirector.setNombre(nombre);
		objDirector.setEmail(email);
		objDirector.setTipo(objTipo);
		
		//3 Crear un objeto DirectorModel
		DirectorModel model = new DirectorModel();
		int salida = model.registrarDirector(objDirector);
		String mensajeSalida = (salida > 0) ? "Director registrado correctamente (OK)" : "Error al registrar el director";
		
		// 4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
	}

}