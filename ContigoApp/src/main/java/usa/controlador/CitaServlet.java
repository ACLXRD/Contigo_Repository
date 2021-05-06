/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import usa.factory.AbstractFactory;
import usa.factory.Producer;
import usa.modelo.dao.CitaDao;
import usa.modelo.dao.IDao;
import usa.modelo.dao.IDaoCita;
import usa.modelo.dto.Cita;
import usa.modelo.dao.IDaoEstudiante;
import usa.modelo.dto.Estudiante;
import usa.utils.Utils;

/**
 *
 * @author Valeria
 */
@WebServlet(name = "CitaServlet", urlPatterns = {"/Cita"})
public class CitaServlet extends HttpServlet {

    AbstractFactory factoryDao = Producer.getFabrica("DAO");
    CitaDao dao = (CitaDao) factoryDao.obtener("CitaDao");


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        System.out.println(request);
        JSONObject respuesta = new JSONObject();
        JSONArray arreglo =null;
        String tipo=request.getParameter("tipo");
         String token = request.getHeader("token");
        if(tipo != null){
            if(tipo.equals("getPerca")){
                String fecha=request.getHeader("fecha");
                String hora=request.getHeader("hora");
                System.out.println("---> " + fecha + " - "+ hora);
                LinkedList<Cita> perca = dao.percaCita(fecha,hora);
                respuesta.put("perca", perca);
            }
             if (tipo.equals("historialEstudiante")) {
                IDaoEstudiante daoEstudiante = (IDaoEstudiante) factoryDao.obtener("EstudianteDao");
                Estudiante estudiante = daoEstudiante.consultarPorToken(token);
                if (estudiante != null) {
                    respuesta.put("tipo", "ok");
                    arreglo = new JSONArray(Utils.toJson(((IDaoCita) dao).listarHistorial(estudiante.getDocumento())));
                } else {
                    respuesta.put("tipo", "error");
                }
            }
            
        }else {
           // if (token != null) {
                arreglo = new JSONArray(Utils.toJson(dao.listarTodos()));
            //}
        }
        
        respuesta.put("citas", arreglo);  
        PrintWriter out = response.getWriter();
        out.print(respuesta.toString());
        
        /**
        response.setContentType("application/json;charset=UTF-8");
        System.out.println(request);
        JSONObject respuesta = new JSONObject();
        String tipo = request.getParameter("tipo");
        JSONArray arreglo = null;
        String token = request.getHeader("token");
        if (tipo != null) {
            if (tipo.equals("historialEstudiante")) {
                IDaoEstudiante daoEstudiante = (IDaoEstudiante) factoryDao.obtener("EstudianteDao");
                Estudiante estudiante = daoEstudiante.consultarPorToken(token);
                if (estudiante != null) {
                    respuesta.put("tipo", "ok");
                    arreglo = new JSONArray(Utils.toJson(((IDaoCita) dao).listarHistorial(estudiante.getDocumento())));
                } else {
                    respuesta.put("tipo", "error");
                }
            }
        } else {
            if (token != null) {
                arreglo = new JSONArray(Utils.toJson(dao.listarTodos()));
            }
        }

        respuesta.put("citas", arreglo);
        PrintWriter out = response.getWriter();
        out.print(respuesta.toString());**/

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
        String id = String.valueOf(json.getInt("id"));
        Cita citaActualizar = (Cita) dao.consultar(id);
        if (citaActualizar != null) {
            IDaoCita daocita = (IDaoCita) dao;
            citaActualizar.setEstado(json.getInt("estado"));
            if (json.getInt("estado") == 3 || json.getInt("estado") == 7) {
                citaActualizar.setMotivo(json.getString("motivo"));
                citaActualizar.setRecomendaciones(json.getString("recomendaciones"));
                if (daocita.registroSucedidoEstudiante(citaActualizar)) {
                    json.put("tipo", "ok");
                    json.put("mensaje", "Se ha registrado la información.");
                } else {
                    json.put("tipo", "error");
                    json.put("mensaje", "No se ha podido guardar el registro");
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    

}
