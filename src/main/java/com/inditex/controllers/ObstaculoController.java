package com.inditex.controllers;

import com.inditex.entities.Obstaculo;
import com.inditex.repositories.*;

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
public class ObstaculoController {


    // Implementa aquí la solución requerida

    @Autowired
    ObstaculoRepository obstaculoRepository;

    @GetMapping("/obstaculos")
    public ResponseEntity<List<Obstaculo>> getObstaculos() {
        List<Obstaculo> obstaculos = obstaculoRepository.findAll();

        if (obstaculos.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Obstaculo>>(obstaculos, HttpStatus.OK);
    }

    @GetMapping("/obstaculos/{id}")
    public ResponseEntity<Obstaculo> getObstaculosById(@PathVariable("id") long id) {
        Optional<Obstaculo> obstaculo = obstaculoRepository.findById(id);

        if (obstaculo.isPresent()){
            return new ResponseEntity<Obstaculo>(obstaculo.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/obstaculo")
    public ResponseEntity<Obstaculo> createObstaculo(@RequestBody Obstaculo obstaculo) {

        //TODO: ¿Validate input?

        Obstaculo newObstaculo = obstaculoRepository.save(obstaculo);

        return new ResponseEntity<Obstaculo>(newObstaculo, HttpStatus.CREATED);
    }


    // Métodos de ejemplo
    @DeleteMapping("/obstaculos/{direccionX}/{direccionY}")
    public ResponseEntity<HttpStatus> deleteObstaculo(@PathVariable("direccionX") int direccionX, @PathVariable("direccionY") int direccionY) {
        Optional<Obstaculo> obstaculo = obstaculoRepository.findByDireccionXAndDireccionY(direccionX, direccionY);

        if (!obstaculo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        obstaculoRepository.delete(obstaculo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/obstaculos")
    public ResponseEntity<HttpStatus> deleteAllObstaculos() {
        obstaculoRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
