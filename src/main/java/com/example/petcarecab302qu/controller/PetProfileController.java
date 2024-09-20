package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.IPetDAO;
import com.example.petcarecab302qu.model.SqlitePetDAO;
import com.example.petcarecab302qu.model.Pet;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for managing the pet profile functionality in the Pet Care application.
 * Handles displaying pet profiles, adding new pets, and navigating through the application.
 */
public class PetProfileController extends NavigationController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField breedField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField imageUrlField;

    @FXML
    private VBox addPetForm;

    @FXML
    private Button addPetButton;

    @FXML
    private VBox petsContainer;

    private IPetDAO petDAO;

    private boolean isAddPetFormVisible = false;

    public PetProfileController(IPetDAO petDAO) {
        this.petDAO = petDAO;
    }

    public PetProfileController() {
        this.petDAO = new SqlitePetDAO();}

    /**
     * Initialises the pet profile page by loading all pets from the database and displaying them.
     * Sets up the visual components for each pet profile, including an image, details, and buttons for editing and deleting.
     */
    @FXML
    public void initialize() {
        petDAO = new SqlitePetDAO();
        List<Pet> pets = petDAO.getAllPets();
        NavigationBar();
        petsContainer.getChildren().clear();

        for (Pet pet : pets) {
            HBox petItem = new HBox();
            petItem.setSpacing(10);
            petItem.setAlignment(Pos.CENTER_LEFT);

            ImageView petImageView = new ImageView();
            petImageView.setFitWidth(100);
            petImageView.setFitHeight(100);
            petImageView.setPreserveRatio(true);

            if (pet.getImageUrl() != null && !pet.getImageUrl().isEmpty()) {
                try {
                    Image image = new Image(pet.getImageUrl());
                    petImageView.setImage(image);
                } catch (Exception e) {
                    petImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default_pet.png"))));
                }
            } else {
                petImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default_pet.png"))));
            }

            petItem.getChildren().add(petImageView);

            VBox petDetails = new VBox();
            petDetails.setSpacing(5);

            Label petNameLabel = new Label("Name: " + pet.getName());
            Label petAgeLabel = new Label("Age: " + pet.getAge());
            Label petGenderLabel = new Label("Gender: " + pet.getGender());
            Label petBreedLabel = new Label("Breed: " + pet.getBreed());
            Label petWeightLabel = new Label("Weight: " + pet.getWeight() + " kg");
            Label petHeightLabel = new Label("Height: " + pet.getHeight() + " cm");

            petDetails.getChildren().addAll(petNameLabel, petAgeLabel, petGenderLabel, petBreedLabel, petWeightLabel, petHeightLabel);

            petItem.getChildren().add(petDetails);

            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");

            // Add functionality for edit and delete buttons ( not yet done)
            //editButton.setOnAction(e -> handleEditPet(pet));
            //deleteButton.setOnAction(e -> handleDeletePet(pet));

            petItem.getChildren().addAll(editButton, deleteButton);
            petsContainer.getChildren().add(petItem);
        }
    }

    /**
     * Toggles the visibility of the "Add Pet" form, allowing users to add new pets.
     * Updates the visibility and layout based on the current state of the form.
     */
    @FXML
    private void toggleAddPetForm() {
        isAddPetFormVisible = !isAddPetFormVisible;
        if (addPetForm != null && addPetButton != null) {
            addPetForm.setVisible(isAddPetFormVisible);
            addPetForm.setManaged(isAddPetFormVisible);
            addPetButton.setVisible(!isAddPetFormVisible);
        }
    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneLoader.handleBackButton(event);
    }

    /**
     * Saves the information provided in the form to create a new pet profile.
     * Validates the input fields, parses the values, and adds the new pet to the database.
     * After saving, the form is cleared and hidden, and the list of pets is refreshed.
     */
    @FXML
    private void savePet() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String gender = genderField.getText();
        String breed = breedField.getText();
        String weightText = weightField.getText();
        String heightText = heightField.getText();
        String imageUrl = imageUrlField.getText();

        int age = 0;
        double weight = 0.0;
        double height = 0.0;

        if (!ageText.trim().isEmpty()) {
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age: " + ageText);
                return;
            }
        }

        if (!weightText.trim().isEmpty()) {
            try {
                weight = Double.parseDouble(weightText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for weight: " + weightText);
                return;
            }
        }

        if (!heightText.trim().isEmpty()) {
            try {
                height = Double.parseDouble(heightText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for height: " + heightText);
                return;
            }
        }

        Pet newPet = new Pet(0, name, age, gender, breed, weight, height, imageUrl);

        petDAO.addPet(newPet);

        clearForm();

        isAddPetFormVisible = false;
        addPetForm.setVisible(false);
        addPetForm.setManaged(false);
        addPetButton.setVisible(true);

        initialize();
    }

//    private void handleEditPet(Pet pet) {
//        // Logic for editing a pet
//    }

//    private void handleDeletePet(Pet pet) {
//        petDAO.deletePet(pet.getId());
//        initialize(); // Refresh the list after deletion
//    }

    private void clearForm() {
        nameField.clear();
        ageField.clear();
        genderField.clear();
        breedField.clear();
        weightField.clear();
        heightField.clear();
        imageUrlField.clear();
    }

    @FXML
    private void cancelAddPetForm() {
        clearForm();
        toggleAddPetForm();
    }
}
