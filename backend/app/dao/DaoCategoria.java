package app.dao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.hibernate.HibernateUtil;
import app.model.Categoria;

/**
 * Created by Pablo on 20/06/2015.
 */
public class DaoCategoria implements DaoBase<Categoria> {
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

    public List<Categoria> obtener(){
        try{
            iniciarOperacion();
            org.hibernate.Query query = sesion.createQuery("FROM Categoria c");
            List<Categoria> listaCategoria = query.list();
            return listaCategoria;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null)
                sesion.close();
        }
    }

}
