package no.ntnu.fullstack.marketplace.repository;

import no.ntnu.fullstack.marketplace.model.Item;
import org.springframework.data.repository.CrudRepository;


//@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{
}