package com.quindielectricos.warehouse.domain.service;

import com.quindielectricos.warehouse.domain.Product;
import com.quindielectricos.warehouse.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
   public List<Product> getAll(){
       return productRepository.getAll();
    }
    public Optional<List<Product>> getByCategory(int categoryId){
       return productRepository.getByCategory(categoryId);
    }
    public Optional<List<Product>>getScarseProduct(int quantity){
       return productRepository.getScarseProduct(quantity);
    }
    public Optional<Product>getProduct(int productId){
       return productRepository.getProduct(productId);
    }
    public Product save(Product product){
       return productRepository.save(product);
    }
    public  boolean delete(int productId){
       return getProduct(productId).map(prod ->{productRepository.delete(productId);return true;
       }).orElse( false);

    }

}




