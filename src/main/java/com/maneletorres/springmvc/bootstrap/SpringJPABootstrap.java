package com.maneletorres.springmvc.bootstrap;

import com.maneletorres.springmvc.domain.Customer;
import com.maneletorres.springmvc.domain.Product;
import com.maneletorres.springmvc.services.CustomerService;
import com.maneletorres.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;

    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    public void loadProducts() {
        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(12.99);
        product1.setImageUrl("http://example.com/product1");

        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(14.99);
        product2.setImageUrl("http://example.com/product2");

        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(34.99);
        product3.setImageUrl("http://example.com/product3");

        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(44.99);
        product4.setImageUrl("http://example.com/product4");

        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(25.99);
        product5.setImageUrl("http://example.com/product5");

        productService.saveOrUpdate(product5);
    }

    public void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Michael");
        customer1.setLastName("Orton Towers");
        customer1.setEmail("michael_orton@test.com");
        customer1.setPhoneNumber("605111111");
        customer1.setAddressLineOne("C/Mayor, nº51");
        customer1.setAddressLineTwo("Avda. Principal, 25A");
        customer1.setCity("Alcorcón");
        customer1.setState("Madrid");
        customer1.setZipCode("28922");

        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Heather");
        customer2.setLastName("Jones");
        customer2.setEmail("heather_jones@test.com");
        customer2.setPhoneNumber("777111325");
        customer2.setAddressLineOne("C/False, nº1");
        customer2.setAddressLineTwo("Plaza Mayor, nº5");
        customer2.setCity("London");
        customer2.setState("England");
        customer2.setZipCode("EC2P");

        customerService.saveOrUpdate(customer2);
    }
}
