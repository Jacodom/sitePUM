package app.service;

import app.dao.DaoNegocio;
import java.util.List;

import app.model.Negocio;
import app.model.Categoria;
import app.model.Menu;
/**
 * Created by Pablo on 20/06/2015.
 */
public class NegocioService {
    DaoNegocio daoNegocio;

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
