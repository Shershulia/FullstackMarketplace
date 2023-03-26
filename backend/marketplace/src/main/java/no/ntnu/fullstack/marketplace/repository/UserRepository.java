package no.ntnu.fullstack.marketplace.repository;

import no.ntnu.fullstack.marketplace.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository is a repository for the User model class that extends the CrudRepository interface.
 * This interface provides basic CRUD operations for the Item model class.
 * No implementation is needed, Spring will generate the implementation at runtime.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
}