package controller;



import entity.Coder;
import entity.Empresa;
import entity.Vacante;
import model.CoderModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class CoderController {

    public static void create() {

        CoderModel objCoderModel = new CoderModel();

        String nombreCoder = JOptionPane.showInputDialog("Ingrese solo los nombres del Coder");
        String apellidosCoder = JOptionPane.showInputDialog("Ingrese los apellidos del Coder");
        String documentoCoder = JOptionPane.showInputDialog("Ingrese el Documento del Coder");
        String cohorteCoder = JOptionPane.showInputDialog("A que Cohorte pertenece este Coder");
        String tecnologiaCoderr = JOptionPane.showInputDialog("Cual es la Tecnologia de este Coder");
        String clanCoder = JOptionPane.showInputDialog("A que Clan pertenece este Coder");

        Coder objCoder = new Coder();
        objCoder.setNombre(nombreCoder);
        objCoder.setApellido(apellidosCoder);
        objCoder.setDocumento(documentoCoder);
        objCoder.setCohorte(cohorteCoder);
        objCoder.setCv(tecnologiaCoderr);
        objCoder.setClan(clanCoder);

        objCoder = (Coder) objCoderModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());
    }


    public static void getAll() {

        CoderModel objModel = new CoderModel();

        String listCoders = "LISTA DE CODERS \n";

        for (Object coder : objModel.findAll()) {
            Coder objCoder = (Coder) coder;
            listCoders += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listCoders);
    }

    public static String getAllString() {

        CoderModel objModel = new CoderModel();

        String listCoders = "LISTA DE CODERS \n";

        for (Object coder : objModel.findAll()) {
            Coder objCoder = (Coder) coder;
            listCoders += objCoder.toString() + "\n";
        }
        return listCoders;
    }

    public static void update() {

        CoderModel objCoderModel = new CoderModel();

        String listCoders = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\nIngresa el ID del Coder que desea editar"));

        Coder objCoder = objCoderModel.findById(idUpdate);

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el Coder");
        } else {

            String nombre = JOptionPane.showInputDialog(null, "Modifica el nombre del Coder", objCoder.getNombre());
            String apellido = JOptionPane.showInputDialog(null, "Modifica los Apellidos del Coder", objCoder.getApellido());
            String documento = JOptionPane.showInputDialog(null, "Modifica el Documento del Coder", objCoder.getDocumento());
            String cohorte = JOptionPane.showInputDialog(null,"Modifica la Cohorte del Coder",objCoder.getCohorte());
            String cv = JOptionPane.showInputDialog(null,"Modifica la Tecnologia del Coder",objCoder.getCv());
            String clan = JOptionPane.showInputDialog(null,"Modifica el Clan del Coder",objCoder.getClan());

            objCoder.setNombre(nombre);
            objCoder.setApellido(apellido);
            objCoder.setDocumento(documento);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);

            objCoderModel.update(objCoder);
        }
    }


    public static void delete() {

        CoderModel objModel = new CoderModel();

        String listCoders = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\n Ingresa el id del Coder que desea eliminar"));

        Coder objCoder = objModel.findById(idDelete);

        int confirm = 1;

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id del Coder");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar este Coder?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objCoder);
        }

    }

    public static void buscarCodersPorCohorte(){

        CoderModel objModel = new CoderModel();

        int cohorteBuscar = Integer.parseInt(JOptionPane.showInputDialog("Digite la Cohorte por la que desea filtrar los Coders"));

        if (objModel.buscarCodersPorCohorte(cohorteBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No Hay ningun Coder en esta Cohorte");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarCodersPorCohorte(cohorteBuscar).toString());
        }
    }

    public static void buscarCodersPorTecnologia(){

        CoderModel objModel = new CoderModel();

        String tecnologiaBuscar = JOptionPane.showInputDialog("Escriba la Tecnologia por la que quiere filtrar los Coders");

        if (objModel.buscarCodersPorTecnologia(tecnologiaBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No Hay ningun Coder con esta tecnologia");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarCodersPorTecnologia(tecnologiaBuscar).toString());
        }
    }

    public static void buscarCodersPorClan(){

        CoderModel objModel = new CoderModel();

        String clanBuscar = JOptionPane.showInputDialog("Escriba el Clan por la que quiere filtrar los Coders");

        if (objModel.buscarCodersPorClan(clanBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No Hay ningun Coder en este clan");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarCodersPorClan(clanBuscar).toString());
        }
    }

}
