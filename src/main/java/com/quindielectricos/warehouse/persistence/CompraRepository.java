package com.quindielectricos.warehouse.persistence;

import com.quindielectricos.warehouse.domain.Purchase;
import com.quindielectricos.warehouse.domain.repository.PurchaseRepository;
import com.quindielectricos.warehouse.persistence.crud.CompraCrudRepository;
import com.quindielectricos.warehouse.persistence.entity.Compra;
import com.quindielectricos.warehouse.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {

        return compraCrudRepository.findByIdCliente(clientId).map(prods->purchaseMapper.toPurchases(prods));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra= purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(prod-> prod.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
