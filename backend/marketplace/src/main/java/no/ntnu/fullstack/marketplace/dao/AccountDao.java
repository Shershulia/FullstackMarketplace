package no.ntnu.fullstack.marketplace.dao;

import no.ntnu.fullstack.marketplace.model.UserInfoResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean checkUserCredentials(final String username, final String password) {
        TypedQuery<UserInfoResponse> query = entityManager.createQuery(
                "SELECT u FROM UserInfoResponse u WHERE u.username = :username AND u.password = :password",
                UserInfoResponse.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<UserInfoResponse> results = query.getResultList();
        return results.size() > 0;
    }

    @Transactional
    public UserInfoResponse getUser(final String userId) {
        return entityManager.find(UserInfoResponse.class, userId);
    }

    @Transactional
    public UserInfoResponse saveUser(final UserInfoResponse user) {
        entityManager.persist(user);
        return user;
    }

}
