package com.quindielectricos.warehouse.persistence;

import com.quindielectricos.warehouse.domain.Product;
import com.quindielectricos.warehouse.domain.repository.ProductRepository;
import com.quindielectricos.warehouse.persistence.crud.ProductoCrudRepository;
import com.quindielectricos.warehouse.persistence.entity.Producto;
import com.quindielectricos.warehouse.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;
@Override
    public List<Product> getAll(){
        List<Producto>productos=(List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto>productos=productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProduct(int quantity) {
    Optional<List<Producto>>productos=productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods-> productMapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
    return productoCrudRepository.findById(productId).map(prod-> productMapper.toProduct(prod));
        /*Optional<Producto> producto=  productoCrudRepository.findById(productId);
        return producto.map(prod->productMapper.toProduct(prod));*/
    }

    @Override
    public Product save(Product product) {
        Producto producto= productMapper.toProducto(product);
        return  productMapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
