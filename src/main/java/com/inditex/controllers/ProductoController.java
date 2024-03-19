package com.inditex.controllers;

import com.inditex.entities.Locker;
import com.inditex.entities.Obstaculo;
import com.inditex.repositories.*;
import com.inditex.entities.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProductoController {

    // Implementa aquí la solución requerida

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getProductos() {
        List<Producto> productos = productoRepository.findAll();

        return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        if (productoRepository.findByNombre(producto.getNombre()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Producto newProducto = productoRepository.save(producto);

        return new ResponseEntity<Producto>(newProducto, HttpStatus.CREATED);
    }

    // Métodos de ejemplo
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") long id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (!producto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/productos")
    public ResponseEntity<HttpStatus> deleteAllProductos() {
        productoRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
