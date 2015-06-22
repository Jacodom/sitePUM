package backend.app.service;

import backend.app.dao.DaoNegocio;
import backend.app.dao.DaoPlato;
import backend.app.model.Plato;

import java.util.List;

/**
 * Created by Pablo on 20/06/2015.
 */
public class PlatoService {
    private DaoPlato daoPlato;

    public List<Plato> obtenerPlatos(){
        daoPlato = new DaoPlato();
        return daoPlato.obtener();
    }


}
