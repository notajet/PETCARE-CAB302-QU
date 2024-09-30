package com.example.petcarecab302qu.model;

import java.util.List;

/**
 * Interface for data access operations related to pets in the PETHUB application.
 * Provides methods for adding, retrieving, updating, and deleting pet records in the database.
 */
public interface IPetDAO {
    void addPet(Pet pet);
    List<Pet> getAllPets();
    void updatePet(Pet pet);
    void deletePet(int petId);
}