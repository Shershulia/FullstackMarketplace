package no.ntnu.fullstack.marketplace;

import no.ntnu.fullstack.marketplace.repository.UserDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class MarketplaceApplication {
    @Autowired
    private UserDataRepository userDataRepository;

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApplication.class, args);
    }

    @PostConstruct
    private void postInit(){
        System.out.println("Users :" + userDataRepository.findAll());
    }

}

