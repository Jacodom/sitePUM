package backend.app.dao;
import java.util.List;

import backend.app.hibernate.HibernateUtil;
import backend.app.model.Categoria;
import backend.app.model.Menu;
import backend.app.model.Negocio;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Jacobo on 20/06/2015.
 */
public class DaoNegocio implements DaoBase<Negocio>{
    private Session sesion;
    private Transaction transaccion;


    private void iniciarOperacion() throws HibernateException
    {
        sesion = HibernateUtil.obtenerInstanciaSesion().openSession();
        transaccion = sesion.beginTransaction();
    }

    private void manejarExcepcion(HibernateException he) throws HibernateException
    {
        transaccion.rollback();
        throw new HibernateException("Error en la capa de acceso a datos", he);
    }
    public List<Negocio> obtener(){
        try {
            iniciarOperacion();
            org.hibernate.Query query = sesion.createQuery("FROM Negocio n");
            List<Negocio> listaNegocio = query.list();
            return listaNegocio;
        }catch (HibernateException he)
        {
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null)
                sesion.close();
        }
    }
    public List<Categoria> obtenerCategoriasNegocio(Negocio negocio){
        try{
            iniciarOperacion();
            List<Categoria> listaCategorias= sesion.createQuery("select categorias from Negocio n WHERE n.idNegocio = :idNegocio")
                    .setParameter("idNegocio",negocio.getIdNegocio()).list();
            return listaCategorias;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null)
                sesion.close();
        }
    }
    public Menu obtenerMenuActivo(Negocio negocio){
        try{
            iniciarOperacion();
            Menu menu =(Menu)sesion.createQuery("select menu from Negocio n where n.idNegocio = :idNegocio and n.menu.estado = true")
                    .setParameter("idNegocio",negocio.getIdNegocio());
            return menu;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if (sesion!=null)
                sesion.close();
        }
    }

}
