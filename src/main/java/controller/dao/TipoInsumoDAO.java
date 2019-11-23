package controller.dao;

import controller.hibernate.HibernateUtil;
import controller.hibernate.HibernateUtilException;
import model.entity.TipoInsumo;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;

public class TipoInsumoDAO extends AbstractDAO<Long, TipoInsumo> {
    /**
     * Este metodo devuelve todos los registros de la tabla referidas por el tipo de dato "Data".
     *
     * @return Devuelve una lista de todos los registros.
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de obtener todos los registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    @Override
    public List<TipoInsumo> getAll() throws HibernateUtilException, DataAccessObjectException {
        List<TipoInsumo> tipoInsumos;
        HibernateUtil.openSessionAndBindToThread();
        EntityManager entityManager = null;
        try {
            entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory().createEntityManager();
            tipoInsumos = (List<TipoInsumo>) entityManager.createQuery("FROM TipoInsumo ").getResultList();
        } catch (HibernateException he) {
            throw new DataAccessObjectException("No se obtener los resultados");
        } finally {
            if (entityManager != null) {
                if (entityManager.isOpen()) {
                    entityManager.close();
                }
            }
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        return tipoInsumos;
    }

    /**
     * Este metodo obtiene los registros por su Id.
     *
     * @param aLong Es la Id del registro al que se quiere acceder.
     * @return Retorna un Objeto del tipo "Data"
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de obtener el registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    @Override
    public TipoInsumo get(Long aLong) throws HibernateUtilException, DataAccessObjectException {
        TipoInsumo tipoInsumo;
        if (aLong == null) {
            throw new NullPointerException("La llave es nula");
        } else {
            HibernateUtil.openSessionAndBindToThread();
            EntityManager entityManager = null;
            try {
                entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory().createEntityManager();
                tipoInsumo = entityManager.find(TipoInsumo.class, aLong);
            } catch (HibernateException he) {
                throw new DataAccessObjectException("No se pudo crear o actualizar");
            } finally {
                if (entityManager != null) {
                    if (entityManager.isOpen()) {
                        entityManager.close();
                    }
                }
                HibernateUtil.closeSessionAndUnbindFromThread();
            }
        }
        return tipoInsumo;
    }
}
