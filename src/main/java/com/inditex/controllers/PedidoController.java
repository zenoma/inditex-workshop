package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PedidoController {


    // Implementa aquí la solución requerida
	@Autowired
	PedidoRepository pedidoRepository ;

	@Autowired
	ProductoRepository productoRepository ;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private LockerRepository lockerRepository;

	@GetMapping("/pedidos")
	public ResponseEntity<List<Pedido>> getPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();

		if (pedidos.isEmpty()){

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
	}
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (pedido.isPresent()){
			return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/pedido")
	public ResponseEntity<Pedido> createProducto(@RequestParam long idCliente, @RequestParam long idProducto) {
		// TODO: Validate all inputs

		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		Optional<Producto> producto = productoRepository.findById(idProducto);
		Locker locker = new Locker();

		Pedido pedido = new Pedido( producto.get(),cliente.get() ,locker );

		return new ResponseEntity<Pedido>(pedido, HttpStatus.CREATED);
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
