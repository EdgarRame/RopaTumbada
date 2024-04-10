/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Edgar
 */
import Model.DetalleVenta;
import java.util.List;

public interface DAODetalleVenta {
    List<DetalleVenta> obtenerDetallesVentaPorIdVenta(int idVenta);
}
