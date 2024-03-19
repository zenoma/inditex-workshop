package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Locker;

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
public class LockerController {

    // Implementa aquí la solución requerida
    @Autowired
    LockerRepository lockerRepository ;
    @GetMapping("/lockers")
    public ResponseEntity<List<Locker>> getLockers() {
        List<Locker> lockers = lockerRepository.findAll();

        if (lockers.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Locker>>(lockers, HttpStatus.OK);
    }

    @GetMapping("/lockers/{id}")
    public ResponseEntity<Locker> getLockersById(@PathVariable("id") long id) {
        Optional<Locker> locker = lockerRepository.findById(id);

        if (locker.isPresent()){
            return new ResponseEntity<Locker>(locker.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/locker")
    public ResponseEntity<Locker> createLocker(@RequestBody Locker locker) {

        //TODO: ¿Validate input?

        Locker newLocker = lockerRepository.save(locker);

        return new ResponseEntity<Locker>(newLocker, HttpStatus.CREATED);
    }

    // Métodos de ejemplo
    @DeleteMapping("/lockers/{id}")
    public ResponseEntity<HttpStatus> deleteLocker(@PathVariable("id") long id){
        Optional<Locker> locker = lockerRepository.findById(id);

        if (! locker.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lockerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/lockers")
    public ResponseEntity<HttpStatus> deleteAllLockers(){
        lockerRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
