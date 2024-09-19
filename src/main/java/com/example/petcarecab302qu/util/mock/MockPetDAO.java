package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.IPetDAO;
import com.example.petcarecab302qu.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class MockPetDAO implements IPetDAO {

    private List<Pet> pets;
    private int nextId = 1;

    public MockPetDAO() {
        this.pets = new ArrayList<>();
    }

    @Override
    public void addPet(Pet pet) {
        pet.setId(nextId++);
        pets.add(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return new ArrayList<>(pets);
    }

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
