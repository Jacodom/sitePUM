package backend.app.dto;

import java.math.BigDecimal;

/**
 * Created by Jacobo on 22/06/2015.
 */
public class DtoPlato {


    private int idPlato;
    private String nombrePlato;
    private String descPlato;
    private BigDecimal precioPlato;
    private int coccionPlato;
    private DtoCategoria categoria;

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescPlato() {
        return descPlato;
    }

    public void setDescPlato(String descPlato) {
        this.descPlato = descPlato;
    }

    public BigDecimal getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(BigDecimal precioPlato) {
        this.precioPlato = precioPlato;
    }

    public int getCoccionPlato() {
        return coccionPlato;
    }

    public void setCoccionPlato(int coccionPlato) {
        this.coccionPlato = coccionPlato;
    }

    public DtoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(DtoCategoria categoria) {
        this.categoria = categoria;
    }
}
