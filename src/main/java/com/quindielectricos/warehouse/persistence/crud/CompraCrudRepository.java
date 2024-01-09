package com.quindielectricos.warehouse.persistence.crud;

import com.quindielectricos.warehouse.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
   Optional<List<Compra>>findByIdCliente(String clienteId);
}
