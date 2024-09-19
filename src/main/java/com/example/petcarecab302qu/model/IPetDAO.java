package com.example.petcarecab302qu.model;

import java.util.List;

public interface IPetDAO {
    void addPet(Pet pet);
    List<Pet> getAllPets();
    void updatePet(Pet pet);
    void deletePet(int petId);
}