package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PedidoController {


    // Implementa aquí la solución requerida
	@Autowired
	PedidoRepository pedidoRepository ;

	@Autowired
	ProductoRepository productoRepository ;

	@GetMapping("/pedidos")
	public ResponseEntity<List<Pedido>> getPedidos() {
		List<Pedido> pedido = pedidoRepository.findAll();

		return new ResponseEntity<List<Pedido>>(pedido, HttpStatus.OK);
	}

    // Método de ejemplo
    @DeleteMapping("/pedidos")
    public ResponseEntity<HttpStatus> deleteAllPedidos(){
	List<Pedido> pedidos = pedidoRepository.findAll();
	for (Pedido p : pedidos){
	    Producto prod = p.getProducto();
	    prod.setStock(prod.getStock() + 1);
	    productoRepository.save(prod);
	}

	pedidoRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
