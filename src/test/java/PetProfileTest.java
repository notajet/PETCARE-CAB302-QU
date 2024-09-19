import com.example.petcarecab302qu.controller.PetProfileController;
import com.example.petcarecab302qu.util.mock.MockPetDAO;
import com.example.petcarecab302qu.model.Pet;
import com.example.petcarecab302qu.model.IPetDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PetProfileTest {
    private PetProfileController petProfileController;
    private IPetDAO mockPetDAO;

    @BeforeEach
    public void setUp() {
        mockPetDAO = new MockPetDAO();
        petProfileController = new PetProfileController(mockPetDAO);

        Pet pet1 = new Pet(0, "Buddy", 3, "Male", "Labrador", 25.0, 60.0, "url1");
        Pet pet2 = new Pet(0, "Mittens", 2, "Female", "Cat", 5.0, 30.0, "url2");
        mockPetDAO.addPet(pet1);
        mockPetDAO.addPet(pet2);
    }

    @Test
    public void testGetAllPets() {
        List<Pet> pets = mockPetDAO.getAllPets();
        assertEquals(2, pets.size());

        assertEquals("Buddy", pets.get(0).getName());
        assertEquals("Mittens", pets.get(1).getName());
    }

    @Test
    public void testAddPet() {
        Pet newPet = new Pet(0, "Charlie", 1, "Male", "Beagle", 10.0, 40.0, "url3");
        mockPetDAO.addPet(newPet);

        List<Pet> pets = mockPetDAO.getAllPets();
        assertEquals(3, pets.size());

        Pet addedPet = pets.get(2);
        assertEquals("Charlie", addedPet.getName());
    }

    /*  Functionality hasn't been implemented yet.
    @Test
    public void testUpdatePet() {
        Pet petToUpdate = mockPetDAO.getAllPets().get(0);
        petToUpdate.setName("Buddy Updated");
        mockPetDAO.updatePet(petToUpdate);

        Pet updatedPet = mockPetDAO.getPet(petToUpdate.getId());
        assertNotNull(updatedPet);
        assertEquals("Buddy Updated", updatedPet.getName());
    }

    // Functionality hasn't been implemented yet
    @Test
    public void testDeletePet() {
        Pet petToDelete = mockPetDAO.getAllPets().get(0);
        mockPetDAO.deletePet(petToDelete.getId());

        List<Pet> pets = mockPetDAO.getAllPets();
        assertEquals(1, pets.size());
        assertNull(mockPetDAO.getPet(petToDelete.getId()));
    }
    */

}


