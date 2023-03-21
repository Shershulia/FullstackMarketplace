package no.ntnu.fullstack.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import no.ntnu.fullstack.marketplace.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}