package backend.app.service;

import backend.app.dao.DaoMenu;
import backend.app.model.Menu;
import backend.app.model.Plato;

import java.util.List;

/**
 * Created by Jacobo on 20/06/2015.
 */
public class MenuService {

    private DaoMenu daoMenu = new DaoMenu();

    public List<Plato> obtenerPlatosMenu(Menu menu){
        return daoMenu.obtenerPlatosMenu(menu);
    }
}
