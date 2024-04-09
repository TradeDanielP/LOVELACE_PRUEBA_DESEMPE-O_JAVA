import controller.CoderController;
import controller.ContratacionController;
import controller.VacanteController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        String option = "";
        String option1 = "";
        String option2 = "";
        String option3 = "";
        String option4 = "";

        do {

            option = JOptionPane.showInputDialog("""
                        1. Administrar Coders
                        2. Administrar Vacantes
                        3. Administrar Contrataciones
                        4. Salir
                    """);

            switch (option){

                case "1":
                    do {

                        option1 = JOptionPane.showInputDialog("""
                                    1. Crear Coder
                                    2. Mostrar Todos los Coders
                                    3. Editar la Informacion de un Coder
                                    4. Eliminar un Coder
                                    5. Buscar Coders por Cohorte
                                    6. Buscar Coders por Clan
                                    7. Buscar Coders por Tecnologia
                                    8. Salir
                                """);
                        switch (option1){
                            case "1":
                                CoderController.create();
                                break;
                            case "2":
                                CoderController.getAll();
                                break;
                            case "3":
                                CoderController.update();
                                break;
                            case "4":
                                CoderController.delete();
                                break;
                            case "5":
                                CoderController.buscarCodersPorCohorte();
                                break;
                            case "6":
                                CoderController.buscarCodersPorClan();
                                break;
                            case "7":
                                CoderController.buscarCodersPorTecnologia();
                                break;
                        }


                    }while (!option1.equals("8"));
                    break;
                case "2":
                    do {

                        option2 = JOptionPane.showInputDialog("""
                                    1. Crear una Vacante
                                    2. Mostrar Todas las Vacantes
                                    3. Editar una Vacante
                                    4. Eliminar una Vacante
                                    5. Consultar Vacantes por Titulo
                                    6. Consultar Vacantes por Tecnologia
                                    7. Salir
                                """);
                        switch (option2){
                            case "1":
                                VacanteController.create();
                                break;
                            case "2":
                                VacanteController.getAll();
                                break;
                            case "3":
                                VacanteController.update();
                                break;
                            case "4":
                                VacanteController.delete();
                                break;
                            case "5":
                                VacanteController.buscarVacantesPorTitulo();
                                break;
                            case "6":
                                VacanteController.buscarVacantesPorTecnologia();
                                break;
                        }

                    } while (!option2.equals("7"));
                    break;
                case "3":
                    do {

                        option3 = JOptionPane.showInputDialog("""
                                    1. Contratar un Coder
                                    2. Mostrar Todas las Contrataciones
                                    3. Editar una Contratacion
                                    4. Eliminar una Contratacion
                                    5. Salir
                                """);
                        switch (option3){
                            case"1":
                                ContratacionController.create();
                                break;
                            case"2":
                                ContratacionController.getAll();
                                break;
                            case "3":
                                ContratacionController.update();
                                break;
                            case "4":
                                ContratacionController.delete();
                                break;
                        }

                    } while (!option3.equals("5"));
                    break;

            }

        }while (!option.equals("4"));

    }
}