package usa.controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import usa.factory.AbstractFactory;
import usa.factory.Producer;
import usa.modelo.dao.IDao;
import usa.modelo.dto.Estudiante;
import usa.utils.Utils;

/**
 *
 * @author Santiago Pérez
 */
@WebServlet(name = "Estudiante", urlPatterns = {"/Estudiante"})
public class EstudianteServlet extends HttpServlet {

    AbstractFactory factoryDao = Producer.getFabrica("DAO");
    IDao dao = (IDao) factoryDao.obtener("EstudianteDao");

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        JSONObject respuesta = new JSONObject();
        JSONArray arreglo = new JSONArray(Utils.toJson(dao.listarTodos()));
        respuesta.put("tipo", "ok");
        respuesta.put("estudiante",arreglo);
        out.print(respuesta.toString());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        Gson gson = new Gson();
        String mensaje = Utils.readParams(request);
        System.out.println(mensaje);
        Estudiante estudiante = (Estudiante) gson.fromJson(mensaje, Estudiante.class);

        if (dao.crear(estudiante)) {
            json.put("tipo", "ok");
            json.put("mensaje", "Estudiante creado");
        } else {
            json.put("tipo", "error");
            json.put("mensaje", "Error al crear estudiante");
        }
        out.print(json.toString());

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
