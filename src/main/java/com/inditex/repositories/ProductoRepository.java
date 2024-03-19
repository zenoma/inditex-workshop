package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Locker;
import com.inditex.entities.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);
    List<Producto> findAll();
    Producto save(Producto prod);
    void delete(Producto prod);
}
