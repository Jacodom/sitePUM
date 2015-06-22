package backend.app.service;

import backend.app.dao.DaoNegocio;
import java.util.List;

import backend.app.model.Negocio;
import backend.app.model.Categoria;
import backend.app.model.Menu;
import backend.app.model.Plato;

/**
 * Created by Pablo on 20/06/2015.
 */
public class NegocioService {
    private DaoNegocio daoNegocio;

    public Negocio obtenerNegocio(int idNegocio){
        daoNegocio = new DaoNegocio();
        for(Negocio negocio: daoNegocio.obtener())
            if(negocio.getIdNegocio()==idNegocio)
                return negocio;
        return null;
    }
    public List<Negocio> obtenerNegocios(){
        daoNegocio = new DaoNegocio();
        return daoNegocio.obtener();
    }
    public List<Categoria> obtenerCategoriasNegocio(Negocio negocio)throws Exception{
        daoNegocio = new DaoNegocio();
        return daoNegocio.obtenerCategoriasNegocio(negocio);
    }
    public Menu obtenerMenuActivo(Negocio negocio){
        daoNegocio= new DaoNegocio();
        return daoNegocio.obtenerMenuActivo(negocio);
    }
}
