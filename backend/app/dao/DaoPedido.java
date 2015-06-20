package app.dao;

import app.hibernate.HibernateUtil;
import app.model.Pedido;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Jacobo on 20/06/2015.
 */
public class DaoPedido implements DaoBase {
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

    public List<Pedido> obtener(){
        try{
            iniciarOperacion();
            Query query = sesion.createQuery("FROM Pedido p");
            List<Pedido> listaPedidos = query.list();
            return listaPedidos;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null){
                sesion.close();
            }
        }
    }

    public Boolean guardarPedido(Pedido pedido){
        try{
            iniciarOperacion();
            sesion.save(pedido);
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
