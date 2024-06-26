package entity;

public class Coder {

    private int id_coder;
    private String nombre;
    ;
    private String apellido;
    private String documento;
    private String cohorte;
    private String cv;
    private String clan;

    public Coder() {

    }

    public Coder(int id_coder, String nombre, String apellido, String documento, String cohorte, String cv, String clan) {
        this.id_coder = id_coder;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.cohorte = cohorte;
        this.cv = cv;
        this.clan = clan;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCohorte() {
        return cohorte;
    }

    public void setCohorte(String cohorte) {
        this.cohorte = cohorte;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return "Coder" + id_coder + "{" + '\n' +
                "idCoder = " + id_coder + '\n' +
                "Nombre = " + nombre + '\n' +
                "Apellido = " + apellido + '\n' +
                "Documento = " + documento + '\n' +
                "Cohorte = " + cohorte + '\n' +
                "CV = " + cv + '\n' +
                "Clan = " + clan + '}' + '\n';
    }
}
