package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection(){

        try{
            //Llamamos al driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Creamos las variables de conexión
            String url = "jdbc:mysql://b7bfbyj09wpv6tok0pkb-mysql.services.clever-cloud.com/b7bfbyj09wpv6tok0pkb";
            String user = "un6kndj6lgkfohjv";
            String password = "1PQer0U2qTjuEjDT4TN1";

            //Establecer conexión
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Se abrio la conexion");

        }catch(ClassNotFoundException error){
            System.out.println("ERROR >> Driver no instalado"+error.getMessage());
        } catch (SQLException error){
            System.out.println("ERROR >> al conectar con la base de datos"+error.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection(){

        try {
            //Si hay una conexión activa entonces la cierra
            if (objConnection != null) objConnection.close();
            System.out.println("Se Cerro la conexion");
        }catch (SQLException error){
            System.out.println("Error"+error.getMessage());
        }

    }

}
