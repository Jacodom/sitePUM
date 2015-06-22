package backend.app.service;
import backend.app.dao.DaoCategoria;
import backend.app.model.Categoria;

import java.util.List;

/**
 * Created by Pablo on 20/06/2015.
 */
public class CategoriaService {
    DaoCategoria daoCategoria;

    public Categoria obtenerCategoria(int idCategoria){
        daoCategoria = new DaoCategoria();
        for(Categoria categoria: daoCategoria.obtener())
            if(categoria.getIdCategoria()==idCategoria)
                return categoria;
        return null;
    }
    public List<Categoria> obtenerCategorias(){
        daoCategoria = new DaoCategoria();
        return daoCategoria.obtener();
    }

}
