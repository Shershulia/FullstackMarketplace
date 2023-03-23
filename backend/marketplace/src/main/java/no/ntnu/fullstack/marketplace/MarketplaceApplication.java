package no.ntnu.fullstack.marketplace;

//import no.ntnu.fullstack.marketplace.repository.UserDataRepository;

import no.ntnu.fullstack.marketplace.model.Item;
import no.ntnu.fullstack.marketplace.service.ItemService;
import no.ntnu.fullstack.marketplace.service.UserService;
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
import java.util.Arrays;

@Configuration
@EnableJpaRepositories("no.ntnu.fullstack.marketplace.repository")
@ComponentScan("no.ntnu.fullstack.marketplace.*")
@EntityScan("no.ntnu.fullstack.marketplace.model")
//@SpringBootApplication
public class MarketplaceApplication {
//    @Autowired
//    private UserDataRepository userDataRepository;
    @Autowired
    ItemService itemService;
    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApplication.class, args);


    }

    @PostConstruct
    private void postInit() {
        System.out.println("init");
        System.out.println(itemService.getAllItem());
        Item item = new Item();
        item.setName("test");
        item.setDescription("This is a test item");
        item.setUserid(1L);
        item.setPrice(1000L);
        item.setLocation("New York");
        item.setImage(Arrays.asList("https://epicmpls.com/wp-content/uploads/2020/03/arozzi_vernazza_bl_vernazza_gaming_chair_blue_1273402.jpg", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.6OHLdGidpuZcpqqqeOk3eAHaHa%26pid%3DApi&f=1&ipt=e971a2f7c54ae6edca4a2f26d56965a915f1359a40e9cb769177edae10d3549b&ipo=images"));
        item.setCategories(Arrays.asList("category1", "category2"));
        item.setMetadata(Arrays.asList("metadata1", "metadata2", "metadata3"));
        itemService.newItem(item);
        System.out.println(itemService.getAllItem());
    }



}

