package com.project.lifeline.Models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VideoSendModel {
    public List<Contact> getContacts() {
        return Contacts;
    }

    public void setContacts(List<Contact> contacts) {
        Contacts = contacts;
    }

    public Contact getSelectedContact() {
        return SelectedContact;
    }

    public void setSelectedContact(Contact selectedContact) {
        SelectedContact = selectedContact;
    }

    private List<Contact> Contacts = new ArrayList<Contact>();

    private Contact SelectedContact = new Contact();

    public UUID getContactId() {
        return ContactId;
    }

    public void setContactId(UUID contactId) {
        ContactId = contactId;
    }

    private UUID ContactId;

    public String getMessage() {
        return Message;
    }


    public void setMessage(String message) {
        Message = message;
    }

    private String Message;

    public UUID getVideoId() {
        return VideoId;
    }

    public void setVideoId(UUID videoId) {
        VideoId = videoId;
    }

    private UUID VideoId;

    public String getMimeType() {
        return MimeType;
    }

    public void setMimeType(String mimeType) {
        MimeType = mimeType;
    }

    private String MimeType;
}
