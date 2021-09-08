package com.learningandroid.contactmanager.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learningandroid.contactmanager.data.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private static ContactRepository repository;
    private LiveData<List<Contact>> contactList;

    public ContactViewModel(@NonNull Application application) {
        super(application);

        repository = new ContactRepository(application);
        contactList = repository.getContactList();
    }

    public static void insert(Contact contact) {
        repository.insert(contact);
    }

    public static void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactList;
    }

    public LiveData<Contact> get(int id) {return repository.get(id);}

    public static void update(Contact contact) {repository.update(contact);}

    public static void delete(Contact contact) {repository.delete(contact);}
}
