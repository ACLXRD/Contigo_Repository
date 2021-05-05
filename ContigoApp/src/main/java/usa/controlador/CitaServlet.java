/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.controlador;

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
import usa.modelo.dao.IDaoCita;
import usa.modelo.dto.Cita;
import usa.utils.Utils;

/**
 *
 * @author Valeria
 */
@WebServlet(name = "CitaServlet", urlPatterns = {"/Cita"})
public class CitaServlet extends HttpServlet {

    AbstractFactory factoryDao = Producer.getFabrica("DAO");
    IDao dao = (IDao) factoryDao.obtener("CitaDao");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        System.out.println(request);
        JSONObject respuesta = new JSONObject();
        JSONArray arreglo = new JSONArray(Utils.toJson(dao.listarTodos()));
        respuesta.put("tipo", "ok");
        respuesta.put("citas", arreglo);
        PrintWriter out = response.getWriter();
        out.print(respuesta.toString());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject(Utils.readParams(request));
        Cita citaActualizar = (Cita) dao.consultar(json.getString("id"));
        if (citaActualizar != null) {
            IDaoCita daocita = (IDaoCita) dao;
            citaActualizar.setEstado(json.getInt("estado"));
            if (json.getInt("estado") == 3 || json.getInt("estado") == 7 ) {
                citaActualizar.setMotivo(json.getString("motivo"));
                citaActualizar.setRecomendaciones(json.getString("recomendaciones"));
                if (daocita.registroSucedidoEstudiante(citaActualizar)) {
                    json.put("tipo", "ok");
                    json.put("mensaje", "Se ha registrado la información");
                } else {
                    json.put("tipo", "error");
                    json.put("mensaje", "No se ha podido guardar el registro");
                }
            }
        }
    }
    

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
