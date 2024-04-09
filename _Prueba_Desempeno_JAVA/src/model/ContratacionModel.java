package model;

import controller.VacanteController;
import database.CRUD;
import database.ConfigDB;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = (Contratacion) obj;

        int id = 0;

        try {

            String sql = "INSERT INTO contratacion (idVacante,idCoder,estado,salario) values (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objContratacion.getIdVacante());
            objPrepare.setInt(2,objContratacion.getIdCoder());
            objPrepare.setString(3, objContratacion.getEstado());
            objPrepare.setDouble(4, objContratacion.getSalario());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objContratacion.setId_contratacion(objResult.getInt(1));
                id = objContratacion.getId_contratacion();
            }

            JOptionPane.showMessageDialog(null, "Contratacion asignada correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return findById(id);
    }

    @Override
    public List<Object> findAll() {

        List<Object> listContrataciones = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM contratacion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Contratacion objContratacion = new Contratacion();
                objContratacion.setId_contratacion(objResult.getInt("id_contratacion"));
                objContratacion.setIdVacante(objResult.getInt("idVacante"));
                objContratacion.setIdCoder(objResult.getInt("idCoder"));
                objContratacion.setFecha_aplicacion(objResult.getString("fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("estado"));
                objContratacion.setSalario(objResult.getDouble("salario"));

                listContrataciones.add(objContratacion);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listContrataciones;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = (Contratacion) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE contratacion SET idVacante = ?, idCoder = ?, estado = ?, salario = ? WHERE id_contratacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objContratacion.getIdVacante());
            objPrepare.setInt(2, objContratacion.getIdCoder());
            objPrepare.setString(3, objContratacion.getEstado());
            objPrepare.setDouble(4, objContratacion.getSalario());
            objPrepare.setInt(5, objContratacion.getId_contratacion());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Contratacion actualizada correctamente");
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

        Contratacion objContratacion = (Contratacion) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM contratacion WHERE id_contratacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objContratacion.getId_contratacion());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "La Contratacion se elimino correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Contratacion findById(int id) {

        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = null;

        try {

            String sql = "SELECT * FROM contratacion WHERE id_contratacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objContratacion = new Contratacion();
                objContratacion.setIdVacante(objResult.getInt("idVacante"));
                objContratacion.setIdCoder(objResult.getInt("idCoder"));
                objContratacion.setFecha_aplicacion(objResult.getString("fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("estado"));
                objContratacion.setSalario(objResult.getDouble("salario"));
                objContratacion.setId_contratacion(objResult.getInt("id_contratacion"));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objContratacion;
    }

}
