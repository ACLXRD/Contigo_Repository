/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.modelo.dto;

/**
 *
 * @author Valeria Bermúdez - Santiago Pérez
 */
public class Mensaje {
    private String mensaje;
    private String emisor;
    /**
     * Tipo de mensaje
     * 1: de estudiante a personal calificado
     * 2: de personal calfiicado a estudiante
     */
    private int tipo;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
