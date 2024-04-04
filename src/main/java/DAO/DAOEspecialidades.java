package Dao;

import Conexion.Conexion;
import modelo.ModeloEspecialidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOEspecialidades implements DAOGeneral{
    private Conexion conexion;
    
    public DAOEspecialidades(){
        conexion = new Conexion();
    }
    @Override
    public boolean agregar(ModeloEspecialidad elemento){
        if(conexion.abrir()){
            String sql = "INSERT INTO clientes(Nombre, Apellido, Telefono, Ciudad) VALUES (?,?)";
            Connection enlace = conexion.obtener();
            try{
                PreparedStatement pstm = enlace.prepareStatement(sql);
                pstm.setString(1, elemento.getNombre());
                pstm.setString(2, elemento.getApellido());
                pstm.setINT(3, elemento.getTelefono());
                pstm.setString(4, elemento.getCiudad());
                pstm.execute();
                return true;
            } catch (SQLException e){
                return false;
            } finally {
                conexion.cerrar();
            }
        }
        return false;
    }
    @Override
     public boolean agregar(ModeloEspecialidad elemento){
        if(conexion.abrir()){
            String sql = "INSERT INTO productos(Nombre, Precio, Existencia) VALUES (?,?)";
            Connection enlace = conexion.obtener();
            try{
                PreparedStatement pstm = enlace.prepareStatement(sql);
                pstm.setString(1, elemento.getNombre());
                pstm.setINT(2, elemento.getPrecio());
                pstm.setINT(3, elemento.getExistencia());
                pstm.execute();
                return true;
            } catch (SQLException e){
                return false;
            } finally {
                conexion.cerrar();
            }
        }
        return false;
     }
     @Override  
     public boolean agregar(ModeloEspecialidad elemento){
        if(conexion.abrir()){
            String sql = "INSERT INTO proveedor(Nombre, Dirección, Telefono, Correo) VALUES (?,?)";
            Connection enlace = conexion.obtener();
            try{
                PreparedStatement pstm = enlace.prepareStatement(sql);
                pstm.setString(1, elemento.getNombre());
                pstm.setString(2, elemento.getDireccion());
                pstm.setINT(3, elemento.getTelefono());
                pstm.setString(4, elemento.getCorreo());
                pstm.execute();
                return true;
            } catch (SQLException e){
                return false;
            } finally {
                conexion.cerrar();
            }
        }
        return false;
     }
     
    @Override
    public List<ModeloEspecialidad> consultar(){
        List<ModeloEspecialidad> Lista = new Arraylist<>();
        if (conexion.abrir()){
            String sql = "SELECT * FROM clientes";
            Connection enlace = conexion.obtener();
            try{
                Statement stmt = enlace.createStatement();
                ResultSet resultados = stmt.executeQuery(sql);
                while (resultados.next()){
                    ModeloEspecialidad prueba2 = new ModeloEspecialidad();
                    prueba2.setId(resultados.getInt("IdCliente"));
                    prueba2.SetNombre(resultados.getString("Nombre"));
                    prueba2.SetApellido(resultados.getString("Apellido"));
                    prueba2.SetTelefono(resultados.getInt("Telefono"));
                    prueba2.SetCiudad(resultados.getSring("Ciudad"));
                    Lista.add(prueba2);
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }finally{
                conexion.cerrar();
            }
        }
        return Lista.stream().todolist();
    }
    @Override
    public List<ModeloEspecialidad> consultar(){
        List<ModeloEspecialidad> Lista = new Arraylist<>();
        if (conexion.abrir()){
            String sql = "SELECT * FROM productos ";
            Connection enlace = conexion.obtener();
            try{
                Statement stmt = enlace.createStatement();
                ResultSet resultados = stmt.executeQuery(sql);
                while (resultados.next()){
                    ModeloEspecialidad prueba2 = new ModeloEspecialidad();
                    prueba2.setId(resultados.getInt("IdProducto"));
                    prueba2.SetNombre(resultados.getString("Nombre"));
                    prueba2.SetPrecio(resultados.getInt("Precio"));
                    prueba2.SetExistencia(resultados.getInt("Existencia"));
                    Lista.add(prueba2);
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }finally{
                conexion.cerrar();
            }
        }
        return Lista.stream().todolist();
    }
    @Override
    public List<ModeloEspecialidad> consultar(){
        List<ModeloEspecialidad> Lista = new Arraylist<>();
        if (conexion.abrir()){
            String sql = "SELECT * FROM proveedores";
            Connection enlace = conexion.obtener();
            try{
                Statement stmt = enlace.createStatement();
                ResultSet resultados = stmt.executeQuery(sql);
                while (resultados.next()){
                    ModeloEspecialidad prueba2 = new ModeloEspecialidad();
                    prueba2.setId(resultados.getInt("IdProveedor"));
                    prueba2.SetNombre(resultados.getString("Nombre"));
                    prueba2.SetDirección(resultados.getString("Dirección"));
                    prueba2.SetTelefono(resultados.getInt("Telefono"));
                    prueba2.SetCorreo(resultados.getSring("Correo"));
                    Lista.add(prueba2);
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }finally{
                conexion.cerrar();
            }
        }
        return Lista.stream().todolist();
    }
    @Override
    public boolean actualizar(Integer id, ModeloEspecialidad nuevo){
        if(conexion.abrir()){
            String sql = "UPDATE clientes SET nombre=? WHERE id=?";
            Connection enlace = conexion.obtener();
            try{
                PreparedStatement stmt = enlace.prepareStatement(sql);
                stmt.setString(1, nuevo.getNombre());
                stmt.setString(2, nuevo.getApellido());
                stmt.setInt(3, nuevo.getTelefono());
                stmt.setString(4, nuevo.getCiudad());
                stmt.executeUpdate();
                return true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            } finally{
                conexion.cerrar();
            }
        }
        return false;
    }
        public boolean actualizar(Integer id, ModeloEspecialidad nuevo){
        if(conexion.abrir()){
            String sql = "UPDATE productos SET nombre=? WHERE id=?";
            Connection enlace = conexion.obtener();
            try{
                PreparedStatement stmt = enlace.prepareStatement(sql);
                stmt.setString(1, nuevo.getNombre());
                stmt.setInt(2, nuevo.getPrecio());
                stmt.setInt(3, nuevo.getExistencia());
                stmt.executeUpdate();
                return true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            } finally{
                conexion.cerrar();
            }
        }
        return false;
    }
            public boolean actualizar(Integer id, ModeloEspecialidad nuevo){
        if(conexion.abrir()){
            String sql = "UPDATE proveedor SET nombre=? WHERE id=?";
            Connection enlace = conexion.obtener();
            try{
                PreparedStatement stmt = enlace.prepareStatement(sql);
                stmt.setString(1, nuevo.getNombre());
                stmt.setString(2, nuevo.getDirección());
                stmt.setInt(3, nuevo.getTelefono());
                stmt.setString(4, nuevo.getCorreo());
                stmt.executeUpdate();
                return true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            } finally{
                conexion.cerrar();
            }
        }
        return false;
    }
        @Override
        public boolean eliminar(Integer id){
            if (conexion.abrir()){
                String sql = "DELETE FROM clientes WHERE id=?";
                Connection con = conexion.obtener();
                try{
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    return true;
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }finally{
                    conexion.cerrar();
                }
            }
            return false;
        }
                public boolean eliminar(Integer id){
            if (conexion.abrir()){
                String sql = "DELETE FROM productos WHERE id=?";
                Connection con = conexion.obtener();
                try{
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    return true;
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }finally{
                    conexion.cerrar();
                }
            }
            return false;
        }
                        public boolean eliminar(Integer id){
            if (conexion.abrir()){
                String sql = "DELETE FROM proveedor WHERE id=?";
                Connection con = conexion.obtener();
                try{
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    return true;
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }finally{
                    conexion.cerrar();
                }
            }
            return false;
        }
}            