package com.dogs.dogsapi.repository;

import com.dogs.dogsapi.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {

    Optional<Dog> findByBreed(String breed);

    boolean existsByBreed(String breed);
}
