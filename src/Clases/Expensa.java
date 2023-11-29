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
public class Expensa {
    private int id;
    Propietario inquilinos;
    private double monto;
    private int dia;
    private String mes;
    private int año;
    private String tipopago;

    public Expensa() {
    }

    public Expensa(int id, Propietario inquilinos, double monto, int dia, String mes, int año, String tipopago) {
        this.id = id;
        this.inquilinos = inquilinos;
        this.monto = monto;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.tipopago = tipopago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Propietario getInquilinos() {
        return inquilinos;
    }

    public void setInquilinos(Propietario inquilinos) {
        this.inquilinos = inquilinos;
    }

    
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

     public int insertQueryGetId() {

        int num = 0;
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conexion();
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery("select ID_Expensa from expensa");   // OBTENER ID DE OTRA TABLA
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
