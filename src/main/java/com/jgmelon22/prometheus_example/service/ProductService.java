package com.jgmelon22.prometheus_example.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgmelon22.prometheus_example.dto.ProductDto;
import com.jgmelon22.prometheus_example.model.Product;
import com.jgmelon22.prometheus_example.repository.ProductRepository;

@Service
public class ProductService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        long count = productRepository.count();
        logger.info("Retrieved {} products", count);
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            logger.warn("Product not found with id: {}", id);
        }
        return product;
    }

    public Product saveProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        Product savedProduct = productRepository.save(product);
        logger.info("Product created with id: {}", savedProduct.getId());
        return savedProduct;
    }

    public Product updateProduct(Long id, ProductDto productDetails) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product not found for update with id: {}", id);
                return new RuntimeException("Product not found");
            });
        BeanUtils.copyProperties(productDetails, product);
        Product updatedProduct = productRepository.save(product);
        logger.info("Product updated with id: {}", id);
        return updatedProduct;
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product not found for deletion with id: {}", id);
                return new RuntimeException("Product not found");
            });
        productRepository.delete(product);
        logger.info("Product deleted with id: {}", id);
    }
}