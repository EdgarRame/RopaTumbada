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
import Model.Venta;
import java.sql.SQLException;
import java.util.List;

public interface DAOVenta {
    void insertarVenta(Venta venta) throws SQLException;
    void insertarDetalleVenta(DetalleVenta detalleVenta);
    Venta obtenerVentaPorId(int idVenta);
    List<Venta> obtenerTodasVentas();
}
