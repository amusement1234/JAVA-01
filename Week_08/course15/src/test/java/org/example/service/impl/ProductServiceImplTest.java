package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void addProduct() {
        for (int i = 0; i < 10; i++) {

            Product product = new Product(null, "123", "2", 3d,
                    "", "", true, false, System.currentTimeMillis(), System.currentTimeMillis());
            boolean result = productService.save(product);
            System.out.println(result);
        }
    }


    @Test
    public void deleteProduct() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1385159609129410562L);
        boolean success = productService.remove(queryWrapper);
        System.out.println(success);

    }


    @Test
    public void updateProduct(){
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",1385159611931205634L);

        Product Product = new Product();
        Product.setPrice(10D);
        boolean success = productService.update(Product,updateWrapper);
        System.out.println(success);
    }

    @Test
    public void get(){
        Product product = productService.getById(1385159611931205634L);
        System.out.println(product);
    }
}
