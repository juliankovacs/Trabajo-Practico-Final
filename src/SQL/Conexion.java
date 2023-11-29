/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author julian
 */
public class Conexion {
    Connection conexion=null;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/tpfinal","root","");
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conexion;
    }
}
