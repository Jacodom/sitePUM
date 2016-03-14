package backend.app.service;

import backend.app.dao.DaoCadete;
import backend.app.model.Cadete;

import java.util.List;

/**
 * Created by Jacobo on 13/03/2016.
 */
public class CadeteService {
    private DaoCadete daoCadete;

    public List<Cadete> obtenerCadetes(){
        daoCadete = new DaoCadete();
        return daoCadete.obtener();
    }

    public Boolean modificarCadete(Cadete cadete){
        daoCadete =  new DaoCadete();
        return daoCadete.guardarCadete(cadete);
    }
}
