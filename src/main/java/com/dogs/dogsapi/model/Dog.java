package com.dogs.dogsapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dogs")
@Getter
@Setter
@NoArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String breed;

    @Column(name = "sub_breeds")
    private String subBreeds;

    public Dog(String breed, String subBreeds) {
        this.breed = breed;
        this.subBreeds = subBreeds;
    }
}