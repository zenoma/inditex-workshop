package com.inditex.service;

import com.inditex.entities.Cliente;
import com.inditex.entities.Locker;
import com.inditex.entities.Pedido;
import com.inditex.entities.Producto;
import com.inditex.repositories.LockerRepository;
import com.inditex.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RoutingServiceImpl implements RoutingService{

    @Autowired
    LockerRepository lockerRepository;
    @Autowired
    private PedidoRepository pedidoRepository;



    private Locker encontrarMejorLocker(Cliente cliente){
        List<Locker> lockers = lockerRepository.findAll();
        Locker mejorLocker = null;
        int distanciaMinima = Integer.MAX_VALUE;

        for (Locker item : lockers) {
            int distancia = calcularDistancia(item, cliente);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                mejorLocker = item;
            }
        }
        return mejorLocker;
    }
    private int calcularDistancia(Locker locker, Cliente cliente) {
        // Calculate the absolute differences between the X and Y coordinates
        int deltaX = Math.abs(locker.getDireccionX() - cliente.getDireccionX());
        int deltaY = Math.abs(locker.getDireccionY() - cliente.getDireccionY());

        return deltaX + deltaY;
    }


    @Override
    public Locker assignLocker(Cliente cliente, Producto producto) {
        Locker locker = encontrarMejorLocker(cliente);
        locker.setPeso(calcularDistancia(locker, cliente));
        lockerRepository.save(locker);


        return locker;
    }
}
