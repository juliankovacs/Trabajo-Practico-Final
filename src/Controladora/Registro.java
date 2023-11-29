/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladora;

import Clases.Consorcio;
import Clases.Expensa;
import Clases.Propietario;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author julian
 */
public class Registro {

    public static boolean agregarpropietario(Propietario prop, String cons) {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            Statement st = cn.createStatement();
            String sql = "SELECT ID_Edificio FROM consorcio WHERE nombreedificio = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, cons);
            ResultSet rs = pst.executeQuery();
            int ID_Edificio = -1;
            if (rs.next()) {
                ID_Edificio = rs.getInt("ID_Edificio");
            } else {
                System.out.println("Edificio no encontrado");
                return false;
            }
            sql = "INSERT INTO propietario(ID_Edificio,nombre,piso,dpto,telefono) VALUES (?,?,?,?,?)";
            PreparedStatement pst2 = cn.prepareStatement(sql);
            pst2.setInt(1, ID_Edificio);
            pst2.setString(2, prop.getNombreProp());
            pst2.setInt(3, prop.getPiso());
            pst2.setString(4, prop.getDepto());
            pst2.setInt(5, prop.getTelefono());
            pst2.executeUpdate();
            pst2.close();
            pst.close();
            cn.close();
            return true;
        } catch (SQLException s) {
            System.out.print("ERROR DE SQL" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.print("ERROR AL AGREGAR PROPIETARIO" + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Propietario> mostrarTodos() {
        ArrayList<Propietario> lista = new ArrayList<Propietario>();
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            String sql = "SELECT * FROM propietario JOIN consorcio ON propietario.ID_Edificio = consorcio.ID_Edificio";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Propietario prop = new Propietario();
                Consorcio cons = new Consorcio();
                prop.setNombreProp(rs.getString("nombre"));
                cons.setNombreEdificio(rs.getString("nombreedificio"));
                prop.setConsorcio(cons);
                prop.setPiso(rs.getInt("piso"));
                prop.setDepto(rs.getString("dpto"));
                prop.setTelefono(rs.getInt("telefono"));
                lista.add(prop);
            }
            pst.close();
            rs.close();
            cn.close();
        } catch (SQLException s) {
            System.out.print("ERROR DE SQL: " + s.getMessage());
        } catch (Exception e) {
            System.out.print("ERROR AL RECUPERAR PROPIETARIOS: " + e.getMessage());
        }
        return lista;
    }

    public static boolean agregarconsorcio(Consorcio cons) {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            Statement st = cn.createStatement();
            String sql = "INSERT INTO consorcio(nombreedificio,nrocuenta,cuit,direccion) VALUES (?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, cons.getNombreEdificio());
            pst.setString(2, cons.getNrocuentabancaria());
            pst.setString(3, cons.getCuit());
            pst.setString(4, cons.getDireccion());

            pst.execute();
            pst.close();
            cn.close();
            return true;
        } catch (SQLException s) {
            System.out.print("ERROR DE SQL" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.print("ERROR AL AGREGAR CONSORCIO" + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Consorcio> mostrarconsorcios() {
        ArrayList<Consorcio> listaConsorcio = new ArrayList<Consorcio>();
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            String sql = "SELECT * FROM consorcio";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                Consorcio cons = new Consorcio();
                cons.setNombreEdificio(rs.getString("nombreedificio"));
                cons.setNrocuentabancaria(rs.getString("nrocuenta"));
                cons.setCuit(rs.getString("cuit"));
                cons.setDireccion(rs.getString("direccion"));

                listaConsorcio.add(cons);
            }
            pst.close();
            rs.close();
            cn.close();
        } catch (SQLException s) {
            System.out.print("ERROR DE SQL" + s.getMessage());

        } catch (Exception e) {
            System.out.print("ERROR AL MOSTRAR PROPIETARIO" + e.getMessage());
        }
        return listaConsorcio;
    }

    public static boolean agregarexpensa(Expensa exp, String prop) {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            Statement st = cn.createStatement();
            String sql = "SELECT ID_Propietario FROM propietario WHERE nombre = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, prop);
            ResultSet rs = pst.executeQuery();
            int ID_Propietario = -1;
            if (rs.next()) {
                ID_Propietario = rs.getInt("ID_Propietario");
            } else {
                System.out.println("Propietario no encontrado");
                return false;
            }
            sql = "INSERT INTO expensas(ID_Propietario,monto,tipopago,mes,dia,año) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst2 = cn.prepareStatement(sql);

            pst2.setInt(1, ID_Propietario);
            pst2.setDouble(2, exp.getMonto());
            pst2.setString(3, exp.getTipopago());
            pst2.setString(4, exp.getMes());
            pst2.setInt(5, exp.getDia());
            pst2.setInt(6, exp.getAño());
            pst2.executeUpdate();
            pst2.close();
            pst.close();
            cn.close();
            return true;
        } catch (SQLException s) {
            System.out.print("ERROR DE SQL" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.print("ERROR AL AGREGAR EXPENSA" + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Expensa> mostrarexpensas() {
        ArrayList<Expensa> lista = new ArrayList<Expensa>();
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            String sql = "SELECT * FROM expensas JOIN propietario ON expensas.ID_Propietario = propietario.ID_Propietario";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Expensa exp = new Expensa();
                Propietario prop = new Propietario();
                exp.setId(rs.getInt("ID_Expensa"));
                exp.setInquilinos(prop);
                prop.setNombreProp(rs.getString("nombre"));
                exp.setMonto(rs.getDouble("monto"));
                exp.setTipopago(rs.getString("tipopago"));
                exp.setMes(rs.getString("mes"));
                exp.setDia(rs.getInt("dia"));
                exp.setAño(rs.getInt("año"));
                lista.add(exp);
            }
            pst.close();
            rs.close();
            cn.close();
        } catch (SQLException s) {
            System.out.print("ERROR DE SQL: " + s.getMessage());
        } catch (Exception e) {
            System.out.print("ERROR AL RECUPERAR EXPENSAS: " + e.getMessage());
        }
        return lista;
    }

    public static void eliminarExpensa(int idEliminar) { //recibe por parametro el ID a eliminar
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            String sql = "DELETE FROM expensas WHERE ID_Expensa = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idEliminar);
            pst.execute();
            pst.close();
            cn.close();

        } catch (SQLException s) {
            System.out.print("ERROR DE SQL" + s.getMessage());

        } catch (Exception e) {
            System.out.print("ERROR AL ELIMINAR EXPENSA" + e.getMessage());

        }
    }

    public static double calcularTotalExpensasPorEdificio(int idEdificio) {
    double totalExpensas = 0.0;
    try {
        Conexion cc = new Conexion();
        Connection cn = cc.conexion();
        String sql = "SELECT c.ID_Edificio, SUM(e.monto) AS totalExpensas\n"
                + "FROM consorcio c\n"
                + "JOIN propietario p ON c.ID_Edificio = p.ID_Edificio\n"
                + "JOIN expensas e ON p.ID_Propietario = e.ID_Propietario\n"
                + "GROUP BY c.ID_Edificio;";
        PreparedStatement pst = cn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int edificio = rs.getInt("ID_Edificio");
            if (edificio == idEdificio) {
                totalExpensas = rs.getDouble("totalExpensas");
                break;
            }
        }
        pst.close();
        rs.close();
        cn.close();
    } catch (SQLException s) {
        System.out.print("ERROR DE SQL: " + s.getMessage());
    } catch (Exception e) {
        System.out.print("ERROR AL CALCULAR TOTAL DE EXPENSAS: " + e.getMessage());
    }
    return totalExpensas;
}


}
