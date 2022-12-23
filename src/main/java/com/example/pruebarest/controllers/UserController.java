package com.example.pruebarest.controllers;

import com.example.pruebarest.entities.User;
import com.example.pruebarest.repositories.UserRepository;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository repository;
    private Logger log;

    public UserController(UserRepository repository){
        this.repository = repository;
    }
    @GetMapping("/api/users")
    public List<User> findAll(){
        return repository.findAll();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> findOneById(@PathVariable Long id){
        Optional<User> user = repository.findById(id);

        if(user.isPresent()){
            log.info("Recuperando usuario");
            return ResponseEntity.ok(user.get());
        }else {
            log.info("No hay usuarios");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> create(@RequestBody User user){
        if(user.getId() != null){
            log.warn("No se puede crear un usario ya existente");
            return ResponseEntity.badRequest().build();
        }
        log.info("Usuario creado");
        return ResponseEntity.ok(repository.save(user));
    }

    @PutMapping("/api/users")
    public ResponseEntity<User> update(@RequestBody User user){
        if(user.getId() == null){
            log.warn("No se puede actualizar un usuario no existente");
            return ResponseEntity.badRequest().build();
        }
        log.info("Usuario actualizado");
        return ResponseEntity.ok(repository.save(user));
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        if(repository.existsById(id)){
            log.warn("No se puede eliminar el usuario");
            return ResponseEntity.notFound().build();
        }
        log.info("Usuario eliminado");
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/users")
    public ResponseEntity<User> deleteAll(){
        log.info("Usuarios eliminados");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
