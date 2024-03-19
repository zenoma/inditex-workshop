package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Locker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Long> {

    Optional<Locker> findByDireccionXAndDireccionY(int direccionX, int direccionY);
    List<Locker> findAll();
    Locker save(Locker locker);
    void delete(Locker locker);
}
