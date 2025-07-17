package service;

import java.io.Serializable;

import org.hibernate.Session;

import util.IModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class AbstractService <T extends IModel<ID>, ID extends Serializable> implements Serializable{
	
	private Class<T> type;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractService() {
        Class<?> clazz = getClass();
        do {
            if (clazz.getSuperclass().equals(AbstractService.class)) {
                break;
            }
        } while ((clazz = clazz.getSuperclass()) != null);

        this.type = (Class<T>) ((ParameterizedType) clazz
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findById(Long id) {
        return this.entityManager.find(type, id);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Transactional
    public void remove(T object) {
        this.entityManager.remove(this.entityManager.getReference(this.type, object.getId()));
    }

    @Transactional
    public void create(T object) {
        this.entityManager.persist(object);
    }

    @Transactional
    public void update(T object) {
        this.entityManager.merge(object);
    }


    protected Session createSession() {
        return (Session) this.entityManager.getDelegate();
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

   

}
