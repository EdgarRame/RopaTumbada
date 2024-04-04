package Dao;

/*@author Edgar*/

import java.util.List;

public interface DAOGeneral <K,E> {
    public boolean agregar (E elemento);
    public List<E> consultar();
    public boolean actualizar(K id, E nuevo);
    public boolean eliminar(K id);
}
