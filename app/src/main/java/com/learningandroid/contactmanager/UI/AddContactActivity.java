package com.learningandroid.contactmanager.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.learningandroid.contactmanager.R;

public class AddContactActivity extends AppCompatActivity {

    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION_REPLY = "occupation_reply";
    private EditText name;
    private EditText occupation;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        name = findViewById(R.id.name_edit_view);
        occupation = findViewById(R.id.occupation_edit_view);
        saveButton = findViewById(R.id.saveButton);

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


    }
}