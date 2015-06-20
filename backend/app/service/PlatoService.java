package app.service;

import app.dao.DaoNegocio;
import app.dao.DaoPlato;
import app.model.Plato;

import java.util.List;

/**
 * Created by Pablo on 20/06/2015.
 */
public class PlatoService {
    DaoPlato daoPlato;

    public List<Plato> obtenerPlatos(){
        daoPlato = new DaoPlato();
        return daoPlato.obtener();
    }


}
