package com.example.mycontactpro;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mycontactpro.pojos.Contact;
import com.example.mycontactpro.pojos.ContactDatabase;

public class AddContactActivity extends AppCompatActivity {

    private EditText firstName, lastName, company, address, tel, mail;
    private Spinner sectorSpinner;
    private Button addButton, cancelButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initSpinner();
        initInterface();
        initButton();
    }

    private void initInterface() {
        context = getApplicationContext();
        Toolbar toolbar = findViewById(R.id.toolbarAddContact);
        setSupportActionBar(toolbar);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        company = findViewById(R.id.company);
        address = findViewById(R.id.address);
        tel = findViewById(R.id.tel);
        mail = findViewById(R.id.mail);
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initButton() {
        addButton.setOnClickListener(v -> {
            addContact();
        });

        cancelButton.setOnClickListener(v -> finish());
    }

    private void initSpinner() {
        sectorSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sector,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectorSpinner.setAdapter(adapter);
    }

    public void addContact() {
        if (areFieldFill()) {
            Contact contact = new Contact(
                    lastName.getText().toString(),
                    firstName.getText().toString(),
                    company.getText().toString(),
                    address.getText().toString(),
                    tel.getText().toString(),
                    mail.getText().toString(),
                    sectorSpinner.getSelectedItem().toString(),
                    0
            );


            ContactDatabase.getDb(context).contactDAO().add(contact);
            Toast.makeText(AddContactActivity.this,
                    "Contact crée", Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(AddContactActivity.this,
                    "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean areFieldFill() {
        return !firstName.getText().toString().trim().isEmpty() &&
                !lastName.getText().toString().trim().isEmpty() &&
                !company.getText().toString().trim().isEmpty() &&
                !address.getText().toString().trim().isEmpty() &&
                !tel.getText().toString().trim().isEmpty() &&
                !mail.getText().toString().trim().isEmpty();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}