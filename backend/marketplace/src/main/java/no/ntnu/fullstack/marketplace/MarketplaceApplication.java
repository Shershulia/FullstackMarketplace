package no.ntnu.fullstack.marketplace;

//import no.ntnu.fullstack.marketplace.repository.UserDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import java.beans.PropertyVetoException;

@Configuration
@EnableJpaRepositories("no.ntnu.fullstack.marketplace.repository")
@ComponentScan("no.ntnu.fullstack.marketplace.*")
@EntityScan("no.ntnu.fullstack.marketplace.model")
//@SpringBootApplication
public class MarketplaceApplication {
//    @Autowired
//    private UserDataRepository userDataRepository;

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApplication.class, args);
    }

//    @PostConstruct
//    private void postInit(){
//        System.out.println("Users :" + userDataRepository.findAll());
//    }


}

