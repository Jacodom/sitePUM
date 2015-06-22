package backend.app.dao;


import backend.app.hibernate.HibernateUtil;
import backend.app.model.Menu;
import backend.app.model.Plato;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Jacobo on 20/06/2015.
 */
public class DaoMenu implements DaoBase {
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

    public List<Menu> obtener(){
        try{
            iniciarOperacion();
            Query query = sesion.createQuery("FROM Menu m");
            List<Menu> listaMenues = query.list();
            return listaMenues;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null){
                sesion.close();
            }
        }
    }

    public List<Plato> obtenerPlatosMenu(int idMenu){
        try{
            iniciarOperacion();
            List<Plato> listaPlatos = sesion.createQuery("SELECT platos FROM Menu m where m.idMenu = :idMenu")
                    .setParameter("idMenu",idMenu).list();
            return listaPlatos;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null)
                sesion.close();
        }
    }
}
