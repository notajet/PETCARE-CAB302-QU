package com.example.petcarecab302qu.model.interfaces;

import com.example.petcarecab302qu.model.entities.Pet;

import java.util.List;

/**
 * Interface for data access operations related to pets in the PETHUB application.
 * Provides methods for adding, retrieving, updating, and deleting pet records in the database.
 */
public interface IPetDAO {
    void addPet(Pet pet);
    List<Pet> getAllPets();
    void updatePet(Pet pet);
    Pet getPet(int petId);
    void deletePet(int petId);
}