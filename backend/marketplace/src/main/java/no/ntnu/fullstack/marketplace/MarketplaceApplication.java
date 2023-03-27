package no.ntnu.fullstack.marketplace;

//import no.ntnu.fullstack.marketplace.repository.UserDataRepository;

import no.ntnu.fullstack.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main class for the marketplace application that starts the Spring Boot application.
 * This class is also responsible for initializing the database with some test data.
 */
@Configuration
@EnableJpaRepositories("no.ntnu.fullstack.marketplace.repository")
@ComponentScan("no.ntnu.fullstack.marketplace.*")
@EntityScan("no.ntnu.fullstack.marketplace.model")
@SpringBootApplication
public class MarketplaceApplication {
//    @Autowired
//    private UserDataRepository userDataRepository;

    /**
     * The item service that is used to initialize the database with some test data.
     */
    @Autowired
    ItemService itemService;

    /**
     * Create a new Spring Boot application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApplication.class, args);


    }




}

