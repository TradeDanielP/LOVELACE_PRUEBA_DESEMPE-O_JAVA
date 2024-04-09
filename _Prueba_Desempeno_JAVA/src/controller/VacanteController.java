package controller;



import entity.Empresa;
import entity.Vacante;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class VacanteController {

    public static void create() {

        VacanteModel objVacanteModel = new VacanteModel();

        String listEmpresas = EmpresaController.getAllString();

        int idEmpresa = Integer.parseInt(JOptionPane.showInputDialog(listEmpresas + "\n Ingresa el ID de la empresa a la que pertenece esta vacante"));
        String tituloVacante = JOptionPane.showInputDialog("Ingrese el titulo de la vacante");
        String descripcionVacante = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante");
        String duracionVacante = JOptionPane.showInputDialog("Ingrese la duracion de la vacante");
        String estadoVacante = "ACTIVA";
        String tecnologiaVacante = JOptionPane.showInputDialog("Cual es la Tecnologia necesaria para esta Vacante?");


        Vacante objVacante = new Vacante();
        objVacante.setIdEmpresa(idEmpresa);
        objVacante.setTitulo(tituloVacante);
        objVacante.setDescripcion(descripcionVacante);
        objVacante.setDuracion(duracionVacante);
        objVacante.setEstado(estadoVacante);
        objVacante.setTecnologia(tecnologiaVacante);

        objVacante = (Vacante) objVacanteModel.insert(objVacante);

        JOptionPane.showMessageDialog(null, objVacante.toString());
    }


    public static void getAll() {

        VacanteModel objModel = new VacanteModel();

        String listVacantes = "LISTA DE VACANTES \n";

        for (Object vacante : objModel.findAll()) {
            Vacante objVacante = (Vacante) vacante;
            listVacantes += objVacante.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listVacantes);
    }

    public static String getAllString() {

        VacanteModel objModel = new VacanteModel();

        String listVacantes = "LISTA DE VACANTES \n";

        for (Object vacante : objModel.findAll()) {
            Vacante objVacante = (Vacante) vacante;
            listVacantes += objVacante.toString() + "\n";
        }
        return listVacantes;
    }

    public static String getAllStringActivas() {

        VacanteModel objModel = new VacanteModel();

        String listVacantes = "LISTA DE VACANTES \n";

        for (Object vacante : objModel.findAll()) {
            Vacante objVacante = (Vacante) vacante;
            if (objVacante.getEstado().equals("ACTIVA")){
                listVacantes += objVacante.toString() + "\n";
            }
        }
            return listVacantes;
    }

    public static void update() {

        VacanteModel objVacanteModel = new VacanteModel();

        String listVacantes = getAllString();
        String listEmpresas = EmpresaController.getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVacantes + "\nIngresa el ID de la Vacante que desea editar"));

        Vacante objVacante = objVacanteModel.findById(idUpdate);

        int confirm = 1;
        String estado = "";

        if (objVacante == null) {
            JOptionPane.showMessageDialog(null, "No se encontro la Vacante");
        } else {

            int idEmpresa = Integer.parseInt(JOptionPane.showInputDialog(null,listEmpresas + "\n Ingresa el ID de la empresa si desea cambiarla \n de lo contrario deje el ID que ya esta",objVacante.getIdEmpresa()));
            String titulo = JOptionPane.showInputDialog(null, "Modifica el titulo de la Vacante", objVacante.getTitulo());
            String descripcion = JOptionPane.showInputDialog(null, "Modifica la descripcion de la vacante", objVacante.getDescripcion());
            String duracion = JOptionPane.showInputDialog(null, "Modifica la duracion de la vacante", objVacante.getDuracion());
            confirm = JOptionPane.showConfirmDialog(null,"Quieres cambiar el estado de la vacante?");
            if (confirm == 0){
                estado = objVacante.getEstado();
                if (objVacante.getEstado().equals("ACTIVA")){
                    estado = "INACTIVA";
                } else {
                    estado = "ACTIVA";
                }
            }else {estado = objVacante.getEstado();}

            String tecnologia = JOptionPane.showInputDialog(null,"Modifica la tecnologia necesaria para esta vacante",objVacante.getTecnologia());

            objVacante.setIdEmpresa(idEmpresa);
            objVacante.setTitulo(titulo);
            objVacante.setDescripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setTecnologia(tecnologia);

            objVacanteModel.update(objVacante);
        }
    }


    public static void delete() {

        VacanteModel objModel = new VacanteModel();

        String listVacantes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVacantes + "\n Ingresa el id de la Vacante que desea eliminar"));

        Vacante objVacante = objModel.findById(idDelete);

        int confirm = 1;

        if (objVacante == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id de la Vacante");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar esta Vacante?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objVacante);
        }

    }

    public static void buscarVacantesPorTitulo(){

        VacanteModel objModel = new VacanteModel();

        String tituloBuscar = JOptionPane.showInputDialog("Escriba el Titulo de la Vacante para mostrar las Vacantes Disponibles");

        if (objModel.buscarVacantesPorTitulo(tituloBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No Hay ninguna Vacante por este titulo");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarVacantesPorTitulo(tituloBuscar).toString());
        }
    }

    public static void buscarVacantesPorTecnologia(){

        VacanteModel objModel = new VacanteModel();

        String tituloBuscar = JOptionPane.showInputDialog("Escriba la Tecnologia de la Vacante para mostrar las Vacantes Disponibles");

        if (objModel.buscarVacantesPorTecnologia(tituloBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No Hay ninguna Vacante con esta tecnologia");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarVacantesPorTecnologia(tituloBuscar).toString());
        }
    }

}
