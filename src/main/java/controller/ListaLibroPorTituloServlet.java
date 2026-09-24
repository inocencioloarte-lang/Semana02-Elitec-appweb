package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LibroModel;

@WebServlet("/listaLibroPorTitulo")
public class ListaLibroPorTituloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1 Recibir el parametro del titulo
		String titulo = req.getParameter("titulo");
		
		//2 Crear un objeto LibroModel
		LibroModel model = new LibroModel();
		List<Libro> lista = model.listaLibroPorTitulo(titulo);
		
		//3 Enviar la lista de libros al cliente en JSON
		resp.setContentType("application/json");
		
		//4 Construir el JSON mmediante Gson modo pretty print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		
		System.out.println("Respuesta JSON: " + jsonSalida);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonSalida);
		
		
	}


	
	
}
