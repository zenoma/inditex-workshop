package com.inditex.service;

import com.inditex.entities.Cliente;
import com.inditex.entities.Locker;
import com.inditex.entities.Producto;


public interface RoutingService {
    Locker assignLocker(Cliente cliente, Producto producto);
}
