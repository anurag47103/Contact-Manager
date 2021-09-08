package com.learningandroid.contactmanager.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.learningandroid.contactmanager.R;
import com.learningandroid.contactmanager.model.Contact;
import com.learningandroid.contactmanager.model.ContactViewModel;

public class AddContactActivity extends AppCompatActivity {

    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION_REPLY = "occupation_reply";
    private EditText name;
    private EditText occupation;
    private Button saveButton;
    private Button updateButton;
    private Button deleteButton;
    private ContactViewModel contactViewModel;
    private Contact contact;
    private boolean isEdit = false;
    private int contactId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        name = findViewById(R.id.name_edit_view);
        occupation = findViewById(R.id.occupation_edit_view);
        saveButton = findViewById(R.id.saveButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(
                AddContactActivity.this.getApplication())
                .create(ContactViewModel.class);

        if(getIntent().hasExtra(MainActivity.CONTACT_ID)) {
            contactId = getIntent().getIntExtra(MainActivity.CONTACT_ID , 0);

            contactViewModel.get(contactId).observe(this , contact2 -> {
                if(contact2 != null) {
                    name.setText(contact2.getName());
                    occupation.setText(contact2.getOccupation());
                    contact = contact2;
                }
            });
            isEdit = true;
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(name.getText().toString().isEmpty() || occupation.getText().toString().isEmpty()) {
                    setResult(RESULT_CANCELED, intent);
                }
                else {
                    intent.putExtra(NAME_REPLY, name.getText().toString());
                    intent.putExtra(OCCUPATION_REPLY, occupation.getText().toString());
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });

        updateButton.setOnClickListener(view -> {
            int id = contactId;
            String edit_name = name.getText().toString().trim();
            String edit_occupation = occupation.getText().toString().trim();

            if(TextUtils.isEmpty(edit_name) || TextUtils.isEmpty(edit_occupation)) {
                Snackbar.make(name , R.string.empty , Snackbar.LENGTH_SHORT);
            }
            else {
                Contact contact = new Contact();
                contact.setId(id);
                contact.setName(edit_name);
                contact.setOccupation(edit_occupation);
                ContactViewModel.update(contact);
                finish();
            }

        });

        deleteButton.setOnClickListener(view -> {
            ContactViewModel.delete(contact);
            finish();
        });

        if(isEdit) {
            saveButton.setVisibility(View.GONE);
        }
        else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

    }
}