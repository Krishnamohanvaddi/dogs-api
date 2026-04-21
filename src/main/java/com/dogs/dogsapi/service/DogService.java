package com.dogs.dogsapi.service;

import com.dogs.dogsapi.model.Dog;
import com.dogs.dogsapi.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<Dog> getAllDogs() {
             return dogRepository.findAll();
    }

    public Optional<Dog> getDogByBreed(String breed) {
           return dogRepository.findByBreed(breed);
    }

    public Dog createDog(Dog dog) {
        if (dogRepository.existsByBreed(dog.getBreed())) {
            throw new IllegalArgumentException("User Breed already present please add differnt one " + dog.getBreed());
        }
          return dogRepository.save(dog);
    }

    public Dog updateDog(Long id, Dog updated) {
        Dog existing = dogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("How can u update dog if its not present, Dog not found with this id: " + id));
          existing.setBreed(updated.getBreed());
        existing.setSubBreeds(updated.getSubBreeds());
          return dogRepository.save(existing);
    }

    public void deleteDog(Long id) {
        if (!dogRepository.existsById(id)) {
            throw new RuntimeException("Hey the dog u entered is not found " + id);
        }
        dogRepository.deleteById(id);
    }
}