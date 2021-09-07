package com.learningandroid.contactmanager.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learningandroid.contactmanager.R;
import com.learningandroid.contactmanager.model.Contact;
import com.learningandroid.contactmanager.model.ContactViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 121;
    private TextView textView;
    private FloatingActionButton fab;
   private ContactViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        fab = findViewById(R.id.addButton);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                    .getApplication())
                    .create(ContactViewModel.class);


        viewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                StringBuilder stringBuilder = new StringBuilder();
                for(Contact contact: contacts) {
                    stringBuilder.append("-").append(contact.getName()).append(" ").append(contact.getOccupation());
                }
                textView.setText(stringBuilder);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AddContactActivity.class);
                startActivityForResult(intent , REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK) {
            ContactViewModel.insert(new Contact(data.getStringExtra(AddContactActivity.NAME_REPLY) ,
                    data.getStringExtra(AddContactActivity.OCCUPATION_REPLY)));
        }
    }
}