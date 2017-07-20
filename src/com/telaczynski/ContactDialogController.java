package com.telaczynski;

import com.telaczynski.datamodel.ContactItem;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by Rafal Telaczynski on 19.07.2017.
 */
public class ContactDialogController {
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextArea notesTextArea;

    public ContactItem addNewContact() {
        String firstName = firstNameTextField.getText();
        String laseName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String notes = notesTextArea.getText();
//        System.out.println(firstName + "\n" + laseName + "\n" + phoneNumber + "\n" + notes);
        ContactItem item;
        if(!firstName.equals("") && !laseName.equals("") && !phoneNumber.equals("")) {
            item = new ContactItem(firstName, laseName, phoneNumber, notes);
        } else {
            System.out.println("Missing input data.");
            item = null;
        }
        return item;
    }

    public void printContactDetails(ContactItem contact) {
        firstNameTextField.setText(contact.getFirstName());
        lastNameTextField.setText(contact.getLastName());
        phoneNumberTextField.setText(contact.getPhoneNumber());
        notesTextArea.setText(contact.getNotes());
    }

    public ContactItem editContact(ContactItem contact) {
        contact.setFirstName(firstNameTextField.getText());
        contact.setLastName(lastNameTextField.getText());
        contact.setPhoneNumber(phoneNumberTextField.getText());
        contact.setNotes(notesTextArea.getText());
        return contact;

    }

}
