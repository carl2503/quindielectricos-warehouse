package com.quindielectricos.warehouse.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
   @Column(name = "id_categoria")
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idCategoria;
   private String descripcion;
   private boolean estado;

   @OneToMany(mappedBy = "categoria")
   private List<Producto>productos;

}
