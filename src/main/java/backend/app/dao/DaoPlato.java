package backend.app.dao;

import backend.app.hibernate.HibernateUtil;
import backend.app.model.Plato;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/**
 * Created by Jacobo on 20/06/2015.
 */
public class DaoPlato {
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

    public List<Plato> obtener(){
        try{
            iniciarOperacion();
            Query query = sesion.createQuery("FROM Plato p");
            List<Plato> listaPlatos = query.list();
            return listaPlatos;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null){
                sesion.close();
            }
        }
    }
}
