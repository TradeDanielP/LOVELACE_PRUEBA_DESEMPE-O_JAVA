package model;

import controller.VacanteController;
import database.CRUD;
import database.ConfigDB;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante = (Vacante) obj;

        try {

            String sql = "INSERT INTO vacante (idEmpresa,titulo,descripcion,duracion,estado,tecnologia) values (?,?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objVacante.getIdEmpresa());
            objPrepare.setString(2, objVacante.getTitulo());
            objPrepare.setString(3, objVacante.getDescripcion());
            objPrepare.setString(4, objVacante.getDuracion());
            objPrepare.setString(5, objVacante.getEstado());
            objPrepare.setString(6, objVacante.getTecnologia());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objVacante.setId_vacante(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Vacante creada correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objVacante;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listVacantes = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM vacante;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Vacante objVacante = new Vacante();
                objVacante.setId_vacante(objResult.getInt("id_vacante"));
                objVacante.setIdEmpresa(objResult.getInt("idEmpresa"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                listVacantes.add(objVacante);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listVacantes;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante = (Vacante) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE vacante SET idEmpresa = ?, titulo = ?, descripcion = ?, duracion = ?, estado = ?, tecnologia = ? WHERE id_vacante = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objVacante.getIdEmpresa());
            objPrepare.setString(2, objVacante.getTitulo());
            objPrepare.setString(3, objVacante.getDescripcion());
            objPrepare.setString(4, objVacante.getDuracion());
            objPrepare.setString(5, objVacante.getEstado());
            objPrepare.setString(6, objVacante.getTecnologia());
            objPrepare.setInt(7, objVacante.getId_vacante());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Vacante actualizada correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        Vacante objVacante = (Vacante) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM vacante WHERE id_vacante = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objVacante.getId_vacante());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "La Vacante se elimino correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Vacante findById(int id) {

        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante = null;

        try {

            String sql = "SELECT * FROM vacante WHERE id_vacante = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objVacante = new Vacante();
                objVacante.setIdEmpresa(objResult.getInt("idEmpresa"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setId_vacante(objResult.getInt("id_vacante"));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objVacante;
    }

    public ArrayList<Object> buscarVacantes(String buscarTitulo){

        List<Object> listVacantes = findAll();

        ArrayList<Object> vacantesFound = new ArrayList<>();

        for (Object vacante : listVacantes){
            Vacante vacanteFound = (Vacante) vacante;
            if (vacanteFound.getTitulo().equalsIgnoreCase(buscarTitulo)){
                vacantesFound.add(vacanteFound);
            }
        }
        return vacantesFound;
    }
}
