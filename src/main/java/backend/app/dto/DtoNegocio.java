package backend.app.dto;

/**
 * Created by Pablo on 20/06/2015.
 */
public class DtoNegocio {

    private int idNegocio;
    private String nombreNegocio;
    private String domicilioNegocio;
    private String telefonoNegocio;
    private String logoNegocio;

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDomicilioNegocio() {
        return domicilioNegocio;
    }

    public void setDomicilioNegocio(String domicilioNegocio) {
        this.domicilioNegocio = domicilioNegocio;
    }

    public String getTelefonoNegocio() {
        return telefonoNegocio;
    }

    public void setTelefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
    }

    public String getLogoNegocio() {
        return logoNegocio;
    }

    public void setLogoNegocio(String logoNegocio) {
        this.logoNegocio = logoNegocio;
    }
}
