package controller;



import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;
import model.CoderModel;
import model.ContratacionModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class ContratacionController {

    public static void create() {

        ContratacionModel objContratacionModel = new ContratacionModel();
        CoderModel objCoderModel = new CoderModel();
        VacanteModel objVacanteModel = new VacanteModel();

        String listVacantes = VacanteController.getAllStringActivas();
        String listCoders = CoderController.getAllString();

        if(listVacantes.equals("LISTA DE VACANTES \n")){
            JOptionPane.showMessageDialog(null,"No hay ninguna Vacante Activa \n");
        } else {

            int idVacante = Integer.parseInt(JOptionPane.showInputDialog(listVacantes + "\n Ingresa el ID de la Vacante a la que desea asignar esta contratacion"));
            int idCoder = Integer.parseInt(JOptionPane.showInputDialog(listCoders+"\n Ingresa el ID del Coder a quien va a contratar"));
            Coder objCoder = objCoderModel.findById(idCoder);
            Vacante objVacante = objVacanteModel.findById(idVacante);
            if(objCoder.getCv().equalsIgnoreCase(objVacante.getTecnologia())){
                String estado = "ACTIVA";
                double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el Salario acordado en esta contratacion"));

                Contratacion objContratacion = new Contratacion();
                objContratacion.setIdVacante(idVacante);
                objContratacion.setIdCoder(idCoder);
                objContratacion.setEstado(estado);
                objContratacion.setSalario(salario);

                String estadoVacante = "INACTIVA";
                objVacante.setEstado(estadoVacante);
                objVacanteModel.update(objVacante);

                objContratacion = (Contratacion) objContratacionModel.insert(objContratacion);

                JOptionPane.showMessageDialog(null, objContratacionModel.findNewContratacion(objContratacion.getId_contratacion()));
            } else {
                JOptionPane.showMessageDialog(null,"Este Coder No tiene la Tecnologia requerida para esta Vacante");
            }
        }
    }


    public static void getAll() {

        ContratacionModel objModel = new ContratacionModel();

        String listContrataciones = "LISTA DE CONTRATACIONES \n";

        for (Object contratacion : objModel.findAll()) {
            Contratacion objContratacion = (Contratacion) contratacion;
            listContrataciones += objContratacion.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listContrataciones);
    }

    public static String getAllString() {

        ContratacionModel objModel = new ContratacionModel();

        String listContrataciones = "LISTA DE CONTRATACIONES \n";

        for (Object contratacion : objModel.findAll()) {
            Contratacion objContratacion = (Contratacion) contratacion;
            listContrataciones += objContratacion.toString() + "\n";
        }
        return listContrataciones;
    }

    public static void update() {

        ContratacionModel objContratacionModel = new ContratacionModel();

        String listContrataciones = getAllString();
        String listVacantes = VacanteController.getAllString();
        String listCoders = CoderController.getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listContrataciones + "\nIngresa el ID de la Contratacion que desea editar"));

        Contratacion objContratacion = objContratacionModel.findById(idUpdate);

        int confirm = 1;
        String estado = "";

        if (objContratacion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro esta Contratacion");
        } else {

            int idVacante = Integer.parseInt(JOptionPane.showInputDialog(null,listVacantes + "\n Ingresa el ID de la vacante si desea cambiarla \n de lo contrario deje el ID que ya esta",objContratacion.getIdVacante()));
            int idCoder = Integer.parseInt(JOptionPane.showInputDialog(null,listCoders+"\n Ingresa el ID del Coder si desea cambiarlo \n de lo contrario deje el id que ya esta",objContratacion.getIdCoder()));
            double salario = Double.parseDouble(JOptionPane.showInputDialog(null,"Modifica el Salario de esta contratacion",objContratacion.getSalario()));
            confirm = JOptionPane.showConfirmDialog(null,"Quieres cambiar el estado de la contratacion?");
            if (confirm == 0){
                estado = objContratacion.getEstado();
                if (objContratacion.getEstado().equals("ACTIVA")){
                    estado = "INACTIVA";
                } else {
                    estado = "ACTIVA";
                }
            } else {estado = objContratacion.getEstado();}

            objContratacion.setIdVacante(idVacante);
            objContratacion.setIdCoder(idCoder);
            objContratacion.setEstado(estado);
            objContratacion.setSalario(salario);

            objContratacionModel.update(objContratacion);
        }
    }


    public static void delete() {

        ContratacionModel objModel = new ContratacionModel();

        String listContrataciones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listContrataciones + "\n Ingresa el ID de la contratacion que desea eliminar"));

        Contratacion objContratacion = objModel.findById(idDelete);

        int confirm = 1;

        if (objContratacion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id de la Contratacion");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar esta Contratacion?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objContratacion);
        }

    }

}
