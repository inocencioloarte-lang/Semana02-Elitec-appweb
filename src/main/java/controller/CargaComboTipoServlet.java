package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import entity.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TipoModel;

@WebServlet("/cargaComboTipoAlias")
public class CargaComboTipoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TipoModel model = new TipoModel();
		List<Tipo> lista = model.listaTipo();

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(new Gson().toJson(lista));
	}

}