package no.ntnu.fullstack.marketplace.repository;

import no.ntnu.fullstack.marketplace.entity.UserData;
import org.springframework.data.repository.CrudRepository;
public interface UserDataRepository extends CrudRepository<UserData, Long>{

}