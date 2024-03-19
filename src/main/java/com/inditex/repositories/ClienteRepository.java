package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Cliente;

import com.inditex.entities.Obstaculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAll();

    Optional<Cliente> findByNombre(String nombre);

    List<Cliente> findByDireccionX(int direccionX);
    List<Cliente> findByDireccionY(int direccionY);
    Cliente save(Cliente client);
    void delete(Cliente client);
}
