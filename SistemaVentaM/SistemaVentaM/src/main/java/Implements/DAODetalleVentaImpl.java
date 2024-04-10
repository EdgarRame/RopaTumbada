/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implements;

import DAO.DAOVenta;
import Model.DetalleVenta;
import Model.Venta;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodri
 */
public class DAODetalleVentaImpl {
    private Connection conn;

    public void setConnection(Connection conexion) {
        conn = conexion;
    }
}
