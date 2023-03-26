package no.ntnu.fullstack.marketplace.repository;

import no.ntnu.fullstack.marketplace.model.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * ItemRepository is a repository for the Item model class that extends the CrudRepository interface.
 * This interface provides basic CRUD operations for the Item model class.
 * No implementation is needed, Spring will generate the implementation at runtime.
 */
//@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{
}