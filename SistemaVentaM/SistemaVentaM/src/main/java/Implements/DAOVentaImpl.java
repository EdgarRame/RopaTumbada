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
public class DAOVentaImpl implements DAOVenta {

    private Connection conn;
    private List<DetalleVenta> listaProductos = new ArrayList<>();

    public void setConnection(Connection conexion) {
        conn = conexion;
    }

    public void agregarCarrito(DetalleVenta producto) {
        listaProductos.add(producto);
    }

    public void eliminarProductoCarrito(int idProducto) {
        // Iterar sobre la lista de productos del carrito
        for (DetalleVenta producto : listaProductos) {
            // Verificar si el ID del producto coincide con el ID especificado
            if (producto.getIdProducto() == idProducto) {
                // Eliminar el producto del carrito
                listaProductos.remove(producto);
                // Se detiene la iteración luego de eliminar el producto para evitar errores
                break;
            }
        }
    }
    
    public List<DetalleVenta> mostrarCarrito() {
        return listaProductos;
    }

    @Override
    public void insertarVenta(Venta venta) throws SQLException {
        String insert_venta = "INSERT INTO ventas (id_venta, nombre_cliente, total_venta) VALUES(?, ?, ?)";
        String insert_producto = "INSERT INTO detalles_venta (id_venta, id_producto, cantidad, precio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insert_venta)) {
            pstmt.setInt(1, venta.getIdVenta());
            pstmt.setString(2, venta.getNombreCliente());
            pstmt.setDouble(3, venta.getTotalVenta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (DetalleVenta producto : listaProductos) {
            try (PreparedStatement pstmt = conn.prepareStatement(insert_producto)) {
                pstmt.setInt(1, venta.getIdVenta());
                pstmt.setInt(2, producto.getIdProducto());
                pstmt.setInt(3, producto.getCantidad());
                pstmt.setDouble(4, producto.getPrecioUnitario());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void insertarDetalleVenta(DetalleVenta detalleVenta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Venta obtenerVentaPorId(int idVenta) {
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Venta(
                        rs.getInt("id_venta"),
                        rs.getString("nombre_cliente"),
                        rs.getDouble("total_venta")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Venta> obtenerTodasVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("id_venta"),
                        rs.getString("nombre_cliente"),
                        rs.getDouble("total_venta")
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

}
