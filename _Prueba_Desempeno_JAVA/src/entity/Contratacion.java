package entity;

import database.ConfigDB;

public class Contratacion {

    private int id_contratacion;
    private int idVacante;
    private int idCoder;
    private String fecha_aplicacion;
    private String estado;
    private double salario;

    public Contratacion(){}

    public Contratacion(int id_contratacion, int idVacante, int idCoder, String fecha_aplicacion, String estado, double salario) {
        this.id_contratacion = id_contratacion;
        this.idVacante = idVacante;
        this.idCoder = idCoder;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId_contratacion() {
        return id_contratacion;
    }

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public int getIdCoder() {
        return idCoder;
    }

    public void setIdCoder(int idCoder) {
        this.idCoder = idCoder;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }


    @Override
    public String toString() {
        return "Contratacion" + id_contratacion + "{" + '\n' +
                "idContratacion = " + id_contratacion + '\n' +
                "idVacante = " + idVacante + '\n' +
                "idCoder = " + idCoder + '\n' +
                "Fecha aplicacion = " + fecha_aplicacion + '\n' +
                "Estado = " + estado + '\n' +
                "Salario = " + salario + '}' + '\n';
    }
}
