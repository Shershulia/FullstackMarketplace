package no.ntnu.fullstack.marketplace.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import no.ntnu.fullstack.marketplace.model.UserInfoResponse;
import java.io.Serializable;
import java.util.List;

public abstract class AbsctractDao< T extends Serializable>{
    private Class< T > clazz;

    @PersistenceContext
    EntityManager entityManager;

    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public T findOne( long id ){
        return entityManager.find( clazz, id );
    }
    public List< T > findAll(){
        return entityManager.createQuery( "from " + clazz.getName() ).getResultList();
    }

    public void create( T entity ){
        entityManager.persist( entity );
    }

    public T update( T entity ){
        return entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }
    public void deleteById( long entityId ){
        T entity = findOne( entityId );
        delete( entity );
    }

}