package app.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Jacobo on 20/06/2015.
 */
@Entity
@Table(name = "negocios")
@NamedQuery(name = "Negocio.findall",query = "select n from Negocio n")
public class Negocio extends BaseModelEntity implements Serializable {
}
