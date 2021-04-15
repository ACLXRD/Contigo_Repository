package usa.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import usa.modelo.dto.Clasificacion;
import usa.modelo.dto.Conversatorio;

/**
 *
 * Clase de Conversatorios
 *
 * @author Miguel Angel Rippe y Natalia Montenegro
 * @since 2021-03-13
 */
public class ConversatoriosDao implements IDaoConversatorios {
// SQL

    private PreparedStatement pat;
    
    /**
     *
     * @param conver
     * @return
     */
    @Override
    public int crearConver(Conversatorio conver) {
        try {
            String sql = "insert into CONVERSATORIO (PERSONAL_PERSONA_documento,titulo,cronograma,imagen,descripcion,lugar,infografia)values(?,?,?,?,?,?,?);";
            pat = conn.prepareStatement(sql);
            pat.setString(1, conver.getOrador());
            pat.setString(2, conver.getTitulo());
            pat.setString(3, conver.getCronograma());
            pat.setString(4, conver.getImagen());
            pat.setString(5, conver.getDescripcion());
            pat.setString(6, conver.getLugar());
            pat.setString(7, conver.getInfografia());
            pat.execute();
            pat.close();
            String sql2 = "select id from CONVERSATORIO order by id desc limit 1;";
            pat = conn.prepareStatement(sql2);
            ResultSet rs = pat.executeQuery();
            if (rs.next()) {
                int idConver = rs.getInt("id");
                rs.close();
                pat.close();
                return idConver;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int crearClasi(String conver, int resultado) {
        try {
            String sql = "insert into CLASIFICACION_has_CONVERSATORIO (CLASIFICACION_id,CONVERSATORIO_id) values (?,?);";
            pat = conn.prepareStatement(sql);
            pat.setString(1, conver);
            pat.setString(2, String.valueOf(resultado));
            pat.execute();
            pat.close();
            return 0;

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    /**
     *
     * @param id
     * @return
     */
    public LinkedList<Clasificacion> consultar(int id) {
        LinkedList<Clasificacion> clasificaciones = new LinkedList();
        try {
            String sql = "select * from CLASIFICACION_has_CONVERSATORIO where CLASIFICACION_id =\"" + id + "\"";
            pat = conn.prepareStatement(sql);
            ResultSet rs = pat.executeQuery();

            while (rs.next()) {
                Clasificacion clasi = new Clasificacion();
                clasi.setId(rs.getInt("CLASIFICACION_id"));
                clasi.setIdConversatorio(rs.getInt("CONVERSATORIO_id"));
                clasificaciones.add(clasi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConversatoriosDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return clasificaciones;
    }

    @Override
    public LinkedList<Conversatorio> listarTodos() {
        LinkedList<Conversatorio> conversatorios = new LinkedList();
        try {
            String sql = "select * from Conversatorio";
            pat = conn.prepareStatement(sql);
            ResultSet rs = pat.executeQuery();
            while (rs.next()) {
                Conversatorio conversatorio = new Conversatorio();
                conversatorio.setCronograma(rs.getString("cronograma"));
                conversatorio.setOrador(rs.getString("PERSONAL_PERSONA_documento"));
                conversatorio.setTitulo(rs.getString("titulo"));
                conversatorio.setImagen(rs.getString("imagen"));
                conversatorio.setDescripcion(rs.getString("descripcion"));
                conversatorio.setId(rs.getInt("id"));
                conversatorio.setInfografia(rs.getString("infografia"));
                conversatorio.setLugar(rs.getString("lugar"));
                conversatorios.add(conversatorio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConversatoriosDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return conversatorios;
    }

    

    @Override
    public boolean eliminar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Conversatorio t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Conversatorio consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean crear(Conversatorio t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
