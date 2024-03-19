package com.inditex.service;

import com.inditex.entities.Cliente;
import com.inditex.entities.Locker;
import com.inditex.entities.Pedido;
import com.inditex.entities.Producto;
import com.inditex.repositories.LockerRepository;
import com.inditex.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class RoutingServiceImpl implements RoutingService{

    @Autowired
    LockerRepository lockerRepository;
    @Autowired
    private PedidoRepository pedidoRepository;


    @Override
    public Locker assignLocker(Cliente cliente, Producto producto) {
        // Get the full list of Pedidos
        // If pedidos = 0 -> Asignar el locker más cercano
        // If pedidos >= 1 -> Recorrer la lista de pedidos y obtener los valores de distancia de todos los lockers.


        List<Pedido> pedidosActuales = pedidoRepository.findAll();

        Optional<Locker> locker = lockerRepository.findById(Long.valueOf(1));
        if (!locker.isPresent()){
            //TODO: Throw error
        }



        return locker.get();
    }
}
