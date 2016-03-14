package backend.app.dao;

import backend.app.hibernate.HibernateUtil;
import backend.app.model.Cadete;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Jacobo on 13/03/2016.
 */
public class DaoCadete implements DaoBase {
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

    public List<Cadete> obtener(){
        try{
            iniciarOperacion();
            org.hibernate.Query query = sesion.createQuery("FROM Cadete c");
            List<Cadete> listaCadetes = query.list();
            return listaCadetes;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!= null)
                sesion.close();
        }
    }

    public Boolean guardarCadete(Cadete cadete){
        try{
            iniciarOperacion();
            sesion.merge(cadete);
            transaccion.commit();
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null)
                sesion.close();
        }

        return true;
    }
}
