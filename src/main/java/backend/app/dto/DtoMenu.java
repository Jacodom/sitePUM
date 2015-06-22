package backend.app.dto;

/**
 * Created by Jacobo on 22/06/2015.
 */
public class DtoMenu {


    private int idMenu;
    private String nombreMenu;
    private Boolean estadoMenu;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public Boolean getEstadoMenu() {
        return estadoMenu;
    }

    public void setEstadoMenu(Boolean estadoMenu) {
        this.estadoMenu = estadoMenu;
    }
}
