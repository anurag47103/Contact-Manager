package com.learningandroid.contactmanager.data;

import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("select * from contact_table where contact_table.id == :id")
    LiveData<Contact> get(int id);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}