package controller;

import entity.Empresa;
import model.EmpresaModel;

public class EmpresaController {

    public static String getAllString() {

        EmpresaModel objModel = new EmpresaModel();

        String listEmpresas = "LISTA DE EMPRESAS \n";

        for (Object empresa : objModel.findAll()) {
            Empresa objEmpresa = (Empresa) empresa;
            listEmpresas += objEmpresa.toString() + "\n";
        }

        return listEmpresas;
    }
}
