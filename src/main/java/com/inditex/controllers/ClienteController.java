package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ClienteController {

    // Implementa aquí la solución requerida
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getClientesById(@PathVariable("id") long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()){
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/cliente")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {

        //TODO: ¿Validate input?

        Cliente newCliente = clienteRepository.save(cliente);

        return new ResponseEntity<Cliente>(newCliente, HttpStatus.CREATED);
    }


    // Métodos de ejemplo
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clienteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/clientes")
    public ResponseEntity<HttpStatus> deleteAllClientes() {
        clienteRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
