package com.telaczynski.datamodel;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Rafal Telaczynski on 19.07.2017.
 */
public class ContactItem {
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty phoneNumber = new SimpleStringProperty();
    private SimpleStringProperty notes = new SimpleStringProperty();

    ContactItem() {
    }

    public ContactItem(String firstName, String lastName, String phoneNumber, String notes) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
        this.notes.set(notes);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes.getValue();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    @Override
    public String toString() {
        return "ContactItem{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                ", notes=" + notes +
                '}';
    }
}
