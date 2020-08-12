package com.maneletorres.springmvc.services;

import com.maneletorres.springmvc.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // Spring context.
@SpringBootTest // Spring configuration to use.
@ActiveProfiles({"jpadao"})
public class ProductServiceJapDaoImplTest {

    private ProductService productService;

    @Autowired // Inject ProductService through Spring context.
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() == 5;
    }
}
