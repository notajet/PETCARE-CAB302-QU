package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.IPetDAO;
import com.example.petcarecab302qu.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the IPetDAO interface for testing purposes.
 * This class simulates a data access object (DAO) for pets by storing pets in memory
 * rather than a real database.
 */
public class MockPetDAO implements IPetDAO {

    private List<Pet> pets;
    private int nextId = 1;

    public MockPetDAO() {
        this.pets = new ArrayList<>();
    }

    /**
     * Adds a new pet to the in-memory list, assigning it a unique ID.
     *
     * @param pet The pet object to be added.
     */
    @Override
    public void addPet(Pet pet) {
        pet.setId(nextId++);
        pets.add(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return new ArrayList<>(pets);
    }

    /**
     * Updates the information of an existing pet in the in-memory list.
     *
     * @param pet The pet object with updated information.
     */
    @Override
    public void updatePet(Pet pet) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == pet.getId()) {
                pets.set(i, pet);
                return;
            }
        }
    }

    @Override
    public void deletePet(int petId) {
        pets.removeIf(pet -> pet.getId() == petId);
    }
}
