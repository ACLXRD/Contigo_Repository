package usa.modelo.dto;


/**
 * 
 */
public class Grado {

    /**
     * Default constructor
     */
    public Grado() {
    }

    /**
     * 
     */
    private String codigo;

    /**
     * 
     */
    private int institucion_id;

    /**
     * 
     */
    private int clasificacion_id;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getInstitucion_id() {
        return institucion_id;
    }

    public void setInstitucion_id(int institucion_id) {
        this.institucion_id = institucion_id;
    }

    public int getClasificacion_id() {
        return clasificacion_id;
    }

    public void setClasificacion_id(int clasificacion_id) {
        this.clasificacion_id = clasificacion_id;
    }

}