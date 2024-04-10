package DAO;

/*@author Edgar*/

import java.util.List;

public interface DAOGeneral <E> {
    public void agregar (E elemento);
    public List<E> consultar();
    public E consultaEspecifica(int id);
    public void actualizar(E nuevo);
    public void eliminar(int id);
}