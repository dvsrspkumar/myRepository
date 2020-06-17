package com.epam.da.inventory.resources;

import com.epam.da.inventory.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@EnableJpaRepositories("com.epam.da.inventory.resources")
@EntityScan("com.epam.da.inventory.model")
public class InventoryController {

    private static Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/home")
    public String home() {
        log.info("Handling home");
        return "Hello World";
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Product add(Product product) {
        log.info("Our Datastore is : "+dataSource);
       if(product != null) {
            log.info("Saving product");
            product.setId("1");
            product.setName("One Plus");
            product.setCategory("Mobile");
            product.setDescription("one Plus Mobile");
            product.setType("Handset");
            product.setMinPrice(20000);
            productRepository.save(product);
       }
        return product;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @RequestMapping(value = "/addImage", method = {RequestMethod.GET, RequestMethod.POST})
    public void addProductImage(byte[] imageData) {
        log.info("Our Datastore is : "+dataSource);
        if(imageData != null) {
            log.info("Saving product Image");
           // productRepository.save(product);
        }
    }

    @RequestMapping(value = "/view", method = {RequestMethod.GET, RequestMethod.POST})
    public Product view(Product product) {
        Optional<Product> prod = productRepository.findById("1");
        return prod.get();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Product update(Product product) {
        log.info("Our Datastore is : "+dataSource);
        if(product != null) {
            log.info("Updating product");
            productRepository.save(product);
        }
        return product;
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Product> search(Product product) {
        return null;
    }
}
