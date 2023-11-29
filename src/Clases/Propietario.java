/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import SQL.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author julian
 */
public class Propietario {
    private String nombreProp;
    Consorcio consorcio;
    private int piso;
    private String depto;
    private int telefono;

    public Propietario() {
    }

    public Propietario(String nombreProp, Consorcio consorcio, int piso, String depto, int telefono) {
        this.nombreProp = nombreProp;
        this.consorcio = consorcio;
        this.piso = piso;
        this.depto = depto;
        this.telefono = telefono;
    }

    
    public String getNombreProp() {
        return nombreProp;
    }

    public void setNombreProp(String nombreProp) {
        this.nombreProp = nombreProp;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    
    
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Consorcio getConsorcio() {
        return consorcio;
    }

    public void setConsorcio(Consorcio consorcio) {
        this.consorcio = consorcio;
    }

    
    public int insertQueryGetIdprop() {

        int num = 0;
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery("select ID_Propietario from propietario");   // OBTENER ID DE OTRA TABLA
            while (rs.next()) {
                num = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errore = e.getMessage();

        }
        return num;
    }
    
    public int insertQueryGetId() {

        int num = 0;
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery("select ID_Edificio from consorcio");   // OBTENER ID DE OTRA TABLA
            while (rs.next()) {
                num = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errore = e.getMessage();

        }
        return num;
    }
        
}
