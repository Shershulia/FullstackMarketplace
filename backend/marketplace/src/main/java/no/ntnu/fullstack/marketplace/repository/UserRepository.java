package no.ntnu.fullstack.marketplace.repository;

import no.ntnu.fullstack.marketplace.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//@Repository
public interface UserRepository extends CrudRepository<User, String>{
}