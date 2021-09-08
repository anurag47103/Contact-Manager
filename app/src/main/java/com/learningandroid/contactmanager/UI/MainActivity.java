package com.learningandroid.contactmanager.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learningandroid.contactmanager.R;
import com.learningandroid.contactmanager.adapter.ContactOnClickListener;
import com.learningandroid.contactmanager.adapter.RecyclerViewAdapter;
import com.learningandroid.contactmanager.model.Contact;
import com.learningandroid.contactmanager.model.ContactViewModel;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ContactOnClickListener {

    private static final int REQUEST_CODE = 121;
    public static final String CONTACT_ID = "contact_id";
    private FloatingActionButton fab;
    private ContactViewModel viewModel;
    private Button deleteAllButton;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.addButton);
        deleteAllButton = findViewById(R.id.deleteAllButton);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        viewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                    .getApplication())
                    .create(ContactViewModel.class);


        viewModel.getAllContacts().observe(MainActivity.this, contacts -> {
            StringBuilder stringBuilder = new StringBuilder();
                recyclerViewAdapter = new RecyclerViewAdapter (contacts , MainActivity.this , this);
            recyclerView.setAdapter(recyclerViewAdapter);

        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AddContactActivity.class);
                startActivityForResult(intent , REQUEST_CODE);
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactViewModel.deleteAll();
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

    @Override
    public void onContactClick(int position) {

        Contact contact = Objects.requireNonNull(viewModel.getAllContacts().getValue()).get(position);
        Log.d("clicked", "onContactClick: " + contact.getName());
        Intent intent = new Intent(MainActivity.this , AddContactActivity.class);
        intent.putExtra(CONTACT_ID, contact.getId());
        startActivity(intent);
    }
}