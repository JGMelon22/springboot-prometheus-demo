package com.jgmelon22.prometheus_example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jgmelon22.prometheus_example.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}