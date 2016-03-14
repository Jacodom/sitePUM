package backend.app.service;

import backend.app.dao.DaoRonda;
import backend.app.model.Ronda;

import java.util.List;

/**
 * Created by Jacobo on 13/03/2016.
 */
public class RondaService {
    private DaoRonda daoRonda;

    public List<Ronda> obtenerRondas(){
        daoRonda =  new DaoRonda();
        return daoRonda.obtener();
    }

    public Boolean guardarRonda(Ronda ronda){
        daoRonda = new DaoRonda();
        return daoRonda.guardarRonda(ronda);
    }
}
