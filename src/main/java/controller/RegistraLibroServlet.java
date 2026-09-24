package controller;

import java.io.IOException;

import entity.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LibroModel;

@WebServlet("/registraLibroAlias")
public class RegistraLibroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1 Recibir los datos del formulario del JSP
		String registro = req.getParameter("registro");
		String titulo = req.getParameter("titulo");
		String pais = req.getParameter("pais");
		String autor = req.getParameter("autor");
		String fechaCreacion = req.getParameter("fecCreacion");

		System.out.println(
				"Datos recibidos: " + registro + " - " + titulo + " - " + pais + " - " + autor + " - " + fechaCreacion);

		// 2 Crear un objeto Libro
		Libro libro = new Libro();
		libro.setRegistro(registro);
		libro.setTitulo(titulo);
		libro.setPais(pais);
		libro.setAutor(autor);
		libro.setFechaCreacion(java.time.LocalDate.parse(fechaCreacion));

		// 3 Crear un objeto CocnursoModel
		LibroModel model = new LibroModel();
		int salida = model.insertaLibro(libro);

		String mensajeSalida = (salida > 0) ? "Libro registrado correctamente (OK)" : "Error al registrar el libro";

		// 4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
	}
}