package backend.app.dao;
import java.util.List;
/**
 * Created by Pablo on 20/06/2015.
 */
public interface DaoBase<T> {
    public List<T> obtener();
   /* public Boolean agregar(T objeto);
    public Boolean modificar(T objeto);
    public Boolean eliminar(T objeto);*/
}
