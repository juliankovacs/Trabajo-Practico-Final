/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import SQL.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author julian
 */
public class Consorcio {

    private String nombreEdificio;
    private String cuit;
    private String nrocuentabancaria;
    private String direccion;

    public Consorcio() {
    }

    public Consorcio(String nombreEdificio, String cuit, String nrocuentabancaria, String direccion) {

        this.nombreEdificio = nombreEdificio;
        this.cuit = cuit;
        this.nrocuentabancaria = nrocuentabancaria;
        this.direccion = direccion;
    }

   
    
    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNrocuentabancaria() {
        return nrocuentabancaria;
    }

    public void setNrocuentabancaria(String nrocuentabancaria) {
        this.nrocuentabancaria = nrocuentabancaria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
