
package controller;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Enfermera;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import model.EnfermeraModel;

@WebServlet("/crudEnfermeraAlias")
public class CrudEnfermeraServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    

	private Gson crearGson() {
	    return new GsonBuilder()
	        .registerTypeAdapter(
	            java.time.LocalDate.class,
	            new util.LocalDateAdapter()
	        )
	        .setPrettyPrinting()
	        .create();
	}


    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        String metodo = req.getParameter("metodo");

        if (metodo == null) {
            res.getWriter().write(
                "{\"mensajeSalida\":\"Debe indicar un método\"}"
            );
            return;
        }

        switch (metodo) {
            case "listaPorNombre":
                listaPorNombre(req, res);
                break;

            case "registra":
                registra(req, res);
                break;

            case "actualiza":
                actualiza(req, res);
                break;

            case "eliminacionFisica":
                eliminacionFisica(req, res);
                break;

            case "buscarPorId":
                buscarPorId(req, res);
                break;

            default:
                res.getWriter().write(
                    "{\"mensajeSalida\":\"Método no encontrado\"}"
                );
        }
    }

    // Listar enfermeras por nombres
    public void listaPorNombre(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String nombres = req.getParameter("nombres");

        EnfermeraModel model = new EnfermeraModel();
        List<Enfermera> lista = model.listaPorNombreLike(
            nombres == null ? "" : nombres
        );

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(
                    java.time.LocalDate.class,
                    new util.LocalDateAdapter()
                )
                .setPrettyPrinting()
                .create();

        String jsonSalida = gson.toJson(lista);

        System.out.println("Respuesta JSON: " + jsonSalida);
        res.getWriter().write(jsonSalida);
    }

    // Registrar enfermera
    public void registra(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String nombres = req.getParameter("nombres");
        String apellidos = req.getParameter("apellidos");
        String dni = req.getParameter("dni");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        String especialidad = req.getParameter("especialidad");
        String telefono = req.getParameter("telefono");
        String turno = req.getParameter("turno");

        Enfermera objEnfermera = new Enfermera();

        objEnfermera.setNombres(nombres);
        objEnfermera.setApellidos(apellidos);
        objEnfermera.setDni(dni);

        if (fechaNacimiento != null && !fechaNacimiento.isBlank()) {
            objEnfermera.setFechaNacimiento(
                LocalDate.parse(fechaNacimiento)
            );
        }

        objEnfermera.setEspecialidad(especialidad);
        objEnfermera.setTelefono(telefono);
        objEnfermera.setTurno(turno);

        EnfermeraModel model = new EnfermeraModel();
        model.registrarEnfermera(objEnfermera);

        List<Enfermera> lista = model.listaPorNombreLike("");


		Gson gson = new GsonBuilder()
		    .registerTypeAdapter(
		        java.time.LocalDate.class,
		        new util.LocalDateAdapter()
		    )
		    .setPrettyPrinting()
		    .create();


        String jsonSalida = gson.toJson(lista);

        System.out.println("Respuesta JSON: " + jsonSalida);
        res.getWriter().write(jsonSalida);
    }

    // Actualizar enfermera
    public void actualiza(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String idEnfermera = req.getParameter("idEnfermera");
        String nombres = req.getParameter("nombres");
        String apellidos = req.getParameter("apellidos");
        String dni = req.getParameter("dni");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        String especialidad = req.getParameter("especialidad");
        String telefono = req.getParameter("telefono");
        String turno = req.getParameter("turno");

        Enfermera objEnfermera = new Enfermera();

        objEnfermera.setIdEnfermera(
            Integer.parseInt(idEnfermera)
        );
        objEnfermera.setNombres(nombres);
        objEnfermera.setApellidos(apellidos);
        objEnfermera.setDni(dni);

        if (fechaNacimiento != null && !fechaNacimiento.isBlank()) {
            objEnfermera.setFechaNacimiento(
                LocalDate.parse(fechaNacimiento)
            );
        }

        objEnfermera.setEspecialidad(especialidad);
        objEnfermera.setTelefono(telefono);
        objEnfermera.setTurno(turno);

        EnfermeraModel model = new EnfermeraModel();
        model.actualizarEnfermera(objEnfermera);

        List<Enfermera> lista = model.listaPorNombreLike("");


		Gson gson = new GsonBuilder()
		    .registerTypeAdapter(
		        java.time.LocalDate.class,
		        new util.LocalDateAdapter()
		    )
		    .setPrettyPrinting()
		    .create();


        String jsonSalida = gson.toJson(lista);

        System.out.println("Respuesta JSON: " + jsonSalida);
        res.getWriter().write(jsonSalida);
    }

    // Eliminar enfermera físicamente
    public void eliminacionFisica(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String idEnfermera = req.getParameter("idEnfermera");

        EnfermeraModel model = new EnfermeraModel();

        model.eliminarEnfermera(
            Integer.parseInt(idEnfermera)
        );

        List<Enfermera> lista = model.listaPorNombreLike("");


		Gson gson = new GsonBuilder()
		    .registerTypeAdapter(
		        java.time.LocalDate.class,
		        new util.LocalDateAdapter()
		    )
		    .setPrettyPrinting()
		    .create();


        String jsonSalida = gson.toJson(lista);

        System.out.println("Respuesta JSON: " + jsonSalida);
        res.getWriter().write(jsonSalida);
    }

    // Buscar enfermera por ID
    public void buscarPorId(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String idEnfermera = req.getParameter("idEnfermera");

        EnfermeraModel model = new EnfermeraModel();

        Enfermera enfermera = model.buscarPorId(
            Integer.parseInt(idEnfermera)
        );


		Gson gson = new GsonBuilder()
		    .registerTypeAdapter(
		        java.time.LocalDate.class,
		        new util.LocalDateAdapter()
		    )
		    .setPrettyPrinting()
		    .create();


        String jsonSalida = gson.toJson(enfermera);

        System.out.println("Respuesta JSON: " + jsonSalida);
        res.getWriter().write(jsonSalida);
    }
}
