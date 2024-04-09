package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder = (Coder) obj;

        try {

            String sql = "INSERT INTO coder (nombreCoder,apellido,documento,cohorte,cv,clan) values (?,?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellido());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setString(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setString(6, objCoder.getClan());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objCoder.setId_coder(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Coder Registrado correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objCoder;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listCoders = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM coder;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();
                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombreCoder"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getString("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoders.add(objCoder);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listCoders;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder = (Coder) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE coder SET nombreCoder = ?, apellido = ?, documento = ?, cohorte = ?, cv = ?, clan = ? WHERE id_coder = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellido());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setString(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setString(6, objCoder.getClan());
            objPrepare.setInt(7, objCoder.getId_coder());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Coder actualizado correctamente");
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

        Coder objCoder = (Coder) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM coder WHERE id_coder = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCoder.getId_coder());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El Coder se elimino correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Coder findById(int id) {

        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder = null;

        try {

            String sql = "SELECT * FROM coder WHERE id_coder = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objCoder = new Coder();
                objCoder.setNombre(objResult.getString("nombreCoder"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getString("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setId_coder(objResult.getInt("id_coder"));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objCoder;
    }

    public ArrayList<Object> buscarCodersPorCohorte(int buscarCohorte){

        List<Object> listCoders = findAll();

        ArrayList<Object> codersFound = new ArrayList<>();

        for (Object coder : listCoders){
            Coder coderFound = (Coder) coder;
            if (Integer.parseInt(coderFound.getCohorte()) == buscarCohorte){
                codersFound.add(coderFound);
            }
        }
        return codersFound;
    }

    public ArrayList<Object> buscarCodersPorClan(String buscarClan){

        List<Object> listCoders = findAll();

        ArrayList<Object> codersFound = new ArrayList<>();


        for (Object coder : listCoders){
            Coder coderFound = (Coder) coder;
            if (coderFound.getClan().contains(buscarClan)){
                codersFound.add(coderFound);
            }
        }
        return codersFound;
    }

    public ArrayList<Object> buscarCodersPorTecnologia(String tecnologiaBuscar){

        List<Object> listCoders = findAll();

        ArrayList<Object> codersFound = new ArrayList<>();


        for (Object coder : listCoders){
            Coder coderFound = (Coder) coder;
            if (coderFound.getCv().contains(tecnologiaBuscar)){
                codersFound.add(coderFound);
            }
        }
        return codersFound;
    }
}
