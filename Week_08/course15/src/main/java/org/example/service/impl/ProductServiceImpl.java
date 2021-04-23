package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Product;
import org.example.mapper.ProductMapper;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl  extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
