package com.telaczynski;

import com.telaczynski.datamodel.ContactData;
import com.telaczynski.datamodel.ContactItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MainWindowController {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableView<ContactItem> tableView;
    @FXML
    private TableColumn<ContactItem, String> firstNameColumn;
    @FXML
    private TableColumn<ContactItem, String> lastNameColumn;
    @FXML
    private TableColumn<ContactItem, String> phoneNumberColumn;
    @FXML
    private TableColumn<ContactItem, String> notesColumn;

    private ContactData data;

    public void initialize() {

        firstNameColumn.setCellValueFactory(param -> param.getValue().firstNameProperty());

        lastNameColumn.setCellValueFactory(param -> param.getValue().lastNameProperty());

        phoneNumberColumn.setCellValueFactory(param -> param.getValue().phoneNumberProperty());

        notesColumn.setCellValueFactory(param -> param.getValue().notesProperty());

        data = new ContactData();
        data.loadContacts();
        tableView.setItems(data.getContactItems());
    }

    @FXML
    public void showAddContactDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ContactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load ContactDialog.fxml");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ContactDialogController contactDialogController = fxmlLoader.getController();
            ContactItem newItem = contactDialogController.addNewContact();
            if (newItem != null) {
                data.addContact(newItem);
                data.saveContacts();
                tableView.getSelectionModel().select(newItem);
            }
        }
    }

    @FXML
    public void showEditContactDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ContactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load ContactDialog.fxml");
            e.printStackTrace();
        }

        ContactDialogController contactDialogController = fxmlLoader.getController();
        ContactItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            showAlert(selectedItem);
            return;
        }
        contactDialogController.printContactDetails(selectedItem);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            contactDialogController.editContact(selectedItem);
            data.saveContacts();
            tableView.getSelectionModel().select(selectedItem);
        }
    }

    @FXML
    public void handleDeleteContact() {
        ContactItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            showAlert(selectedItem);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected contact: " + selectedItem.getFirstName() + " "
                + selectedItem.getLastName());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            data.deleteContact(selectedItem);
            data.saveContacts();
        }
    }

    private void showAlert(ContactItem item) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Contact Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select the contact you want to edit");
        alert.showAndWait();
        return;
    }
}
