package controller;

import java.io.IOException;

import entity.Publicacion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PublicacionModel;

@WebServlet("/registraPublicacionAlias")

public class RegistraPublicacionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1 Recibir los datos del formulario del JSP
				String usuario = req.getParameter("usuario");
				String titulo = req.getParameter("titulo");
				String contenido = req.getParameter("contenido");
				String referencias = req.getParameter("referencias");
				String fecha = req.getParameter("fecha");
				
				System.out.println("Datos recibidos: " + usuario + " - " + titulo + " - " + contenido + " - " + referencias + " - " + fecha);
				
				
				Publicacion publicacion = new Publicacion();
				publicacion.setUsuario(usuario);
				publicacion.setTitulo(titulo);
				publicacion.setContenido(contenido);
				publicacion.setReferencias(referencias);
				publicacion.setFecha(java.time.LocalDate.parse(fecha));

				// 3 Crear un objeto CocnursoModel
				PublicacionModel model = new PublicacionModel();
				int salida = model.insertaPublicacion(publicacion);

				String mensajeSalida = (salida > 0) ? "Publicacion registrada correctamente (OK)" : "Error al registrar la publicacion";

				
				// 4 Enviar una respuesta al cliente en JSON al jquery
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
	}

}
