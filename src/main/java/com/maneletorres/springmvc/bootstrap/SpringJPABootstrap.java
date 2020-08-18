package com.maneletorres.springmvc.bootstrap;

import com.maneletorres.springmvc.domain.*;
import com.maneletorres.springmvc.domain.security.Role;
import com.maneletorres.springmvc.enums.OrderStatus;
import com.maneletorres.springmvc.services.ProductService;
import com.maneletorres.springmvc.services.RoleService;
import com.maneletorres.springmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ProductService productService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
    }

    public void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
                // TODO - Is this necessary? -> userService.saveOrUpdate(user);
            });
        });
    }

    public void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
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

    public void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("mweston");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setFirstName("Michael");
        customer1.setLastName("Orton Towers");
        customer1.setEmail("michael_orton@test.com");
        customer1.setPhoneNumber("605111111");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("C/Mayor, nº51");
        customer1.getBillingAddress().setAddressLine2("Avda. Principal, 25A");
        customer1.getBillingAddress().setCity("Alcorcón");
        customer1.getBillingAddress().setState("Madrid");
        customer1.getBillingAddress().setZipCode("28922");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("fglenanne");
        user2.setPassword("password");

        Customer customer2 = new Customer();
        customer2.setFirstName("Heather");
        customer2.setLastName("Jones");
        customer2.setEmail("heather_jones@test.com");
        customer2.setPhoneNumber("777111325");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("C/False, nº1");
        customer2.getBillingAddress().setAddressLine2("Plaza Mayor, nº5");
        customer2.getBillingAddress().setCity("London");
        customer2.getBillingAddress().setState("England");
        customer2.getBillingAddress().setZipCode("EC2P");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("saxe");
        user3.setPassword("password");

        Customer customer3 = new Customer();
        customer3.setFirstName("Sam");
        customer3.setLastName("Axe");
        customer3.setEmail("sam@test.com");
        customer3.setPhoneNumber("305.426.9832");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZipCode("33101");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

    public void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
    }

    public void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("CUSTOMER")) {
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }
}
