package com.learningandroid.contactmanager.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.learningandroid.contactmanager.model.Contact;
import com.learningandroid.contactmanager.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    LiveData<List<Contact>> contactList;
    ContactDao contactDao;

    public ContactRepository(Context context) {
        contactDao = ContactRoomDatabase.getDatabase(context).contactDao();

        contactList = contactDao.getAllContact();
    }

    public void insert(Contact contact) {
        ContactRoomDatabase.databaseExecutorWriter.execute(() -> {
            contactDao.insert(contact);
        });
    }

    public void deleteAll() {
        ContactRoomDatabase.databaseExecutorWriter.execute(() -> {
            contactDao.deleteALL();
        });
    }

    public LiveData<List<Contact>> getContactList() {
        return contactList;
    }
}