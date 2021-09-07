package com.learningandroid.contactmanager.data;

import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.learningandroid.contactmanager.model.Contact;

import java.util.List;

@Dao
public interface ContactDao  {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("delete from contact_table")
    void deleteALL();

    @Query("select * from contact_table order by name asc")
    LiveData<List<Contact>> getAllContact();
}