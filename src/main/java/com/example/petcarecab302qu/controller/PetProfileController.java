package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.IPetDAO;
import com.example.petcarecab302qu.model.SqlitePetDAO;
import com.example.petcarecab302qu.model.Pet;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for managing the pet profile functionality in the Pet Care application.
 * Handles displaying pet profiles, adding new pets, and navigating through the application.
 */
public class PetProfileController extends NavigationController {

    @FXML
    public ImageView logoImage;
    public VBox header;

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
    private Button saveOrUpdateButton;

    @FXML
    private VBox petsContainer;

    private IPetDAO petDAO;
    private Pet currentPet = null;

    public PetProfileController(IPetDAO petDAO) {
        this.petDAO = petDAO;
    }

    public PetProfileController() {
        this.petDAO = new SqlitePetDAO();
    }

    /**
     * Initializes the pet profile page by loading all pets from the database and displaying them.
     */
    @FXML
    public void initialize() {
        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }

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
            editButton.setStyle("-fx-background-color: orange; -fx-text-fill: black;");

            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: #f08080; -fx-text-fill: black;");

            editButton.setOnAction(e -> handleEditPet(pet));
            deleteButton.setOnAction(e -> handleDeletePet(pet));

            petItem.getChildren().addAll(editButton, deleteButton);
            petsContainer.getChildren().add(petItem);
        }
    }

    /**
     * Toggles the visibility of the "Add Pet" form, allowing users to add or edit pets.
     */
    @FXML
    private void toggleAddPetForm() {
        if (addPetForm != null && addPetButton != null) {
            boolean formVisible = addPetForm.isVisible();
            addPetForm.setVisible(!formVisible);
            addPetForm.setManaged(!formVisible);
            addPetButton.setVisible(formVisible);
        }
    }

    /**
     * Saves the pet to the database. This method handles both adding new pets and updating existing pets.
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

        if (currentPet == null) {
            Pet newPet = new Pet(0, name, age, gender, breed, weight, height, imageUrl);
            petDAO.addPet(newPet);
        } else {
            currentPet.setName(name);
            currentPet.setAge(age);
            currentPet.setGender(gender);
            currentPet.setBreed(breed);
            currentPet.setWeight(weight);
            currentPet.setHeight(height);
            currentPet.setImageUrl(imageUrl);

            petDAO.updatePet(currentPet);
        }

        resetFormAndButton();
    }

    /**
     * Opens the form for editing the selected pet's details and pre-fills the form with the current values.
     *
     * @param pet The pet to be edited.
     */
    private void handleEditPet(Pet pet) {
        currentPet = pet;
        nameField.setText(pet.getName());
        ageField.setText(String.valueOf(pet.getAge()));
        genderField.setText(pet.getGender());
        breedField.setText(pet.getBreed());
        weightField.setText(String.valueOf(pet.getWeight()));
        heightField.setText(String.valueOf(pet.getHeight()));
        imageUrlField.setText(pet.getImageUrl());

        toggleAddPetForm();
        saveOrUpdateButton.setText("Update Pet");
    }

    /**
     * Deletes the selected pet from the database and refreshes the pet list.
     *
     * @param pet The pet to be deleted.
     */
    private void handleDeletePet(Pet pet) {
        petDAO.deletePet(pet.getId());
        initialize();
    }

    /**
     * Resets the form fields and button after a pet has been saved or updated. Also refreshes the list of pets.
     */
    private void resetFormAndButton() {
        clearForm();
        saveOrUpdateButton.setText("Save New Pet");
        toggleAddPetForm();
        currentPet = null;
        initialize();
    }

    /**
     * Clears the input fields in the form.
     */
    private void clearForm() {
        nameField.clear();
        ageField.clear();
        genderField.clear();
        breedField.clear();
        weightField.clear();
        heightField.clear();
        imageUrlField.clear();
    }

    /**
     * Cancels the action of adding or editing a pet and closes the form.
     */
    @FXML
    private void cancelAddPetForm() {
        clearForm();
        toggleAddPetForm();
    }
}
