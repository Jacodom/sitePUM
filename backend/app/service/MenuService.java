package app.service;

import app.dao.DaoMenu;
import app.model.Menu;
import app.model.Plato;

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
