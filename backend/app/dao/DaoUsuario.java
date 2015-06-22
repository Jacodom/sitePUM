package app.dao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.hibernate.HibernateUtil;
import app.model.Usuario;
import app.model.Pedido;
/**
 * Created by Pablo on 20/06/2015.
 */
public class DaoUsuario implements DaoBase<Usuario>{
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
    public List<Usuario> obtener(){
     try{
         iniciarOperacion();
         org.hibernate.Query query = sesion.createQuery("FROM Usuario u");
         List<Usuario> listaUsuarios = query.list();
         return listaUsuarios;
     }catch (HibernateException he){
         manejarExcepcion(he);
         throw he;
     }finally {
         if(sesion!= null)
             sesion.close();
     }
    }
    public List<Pedido> obtenerPedidosUsuario(int idUsuario){
        try{
            iniciarOperacion();
            List<Pedido> listaPedidosUsuario = sesion.createQuery("SELECT pedidos from Usuarios u where u.idUsuario = :idUsuario")
                    .setMaxResults(5).list();
            return listaPedidosUsuario;
        }catch (HibernateException he){
            manejarExcepcion(he);
            throw he;
        }finally {
            if(sesion!=null)
                sesion.close();
        }
    }
}
