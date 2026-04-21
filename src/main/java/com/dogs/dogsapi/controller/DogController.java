package com.dogs.dogsapi.controller;

import com.dogs.dogsapi.model.Dog;
import com.dogs.dogsapi.service.DogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@CrossOrigin(origins = "*")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public ResponseEntity<List<Dog>> getAllDogsList() {
        return ResponseEntity.ok(dogService.getAllDogs());
    }

    @GetMapping("/{breed}")
    public ResponseEntity<Dog> getDogByBreed(@PathVariable String breed) {
        return dogService.getDogByBreed(breed)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        try {
            Dog created = dogService.createDog(dog);
              return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
              return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        try {
            return ResponseEntity.ok(dogService.updateDog(id, dog));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
        try {
            dogService.deleteDog(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}