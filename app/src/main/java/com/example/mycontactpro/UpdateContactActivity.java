package com.example.mycontactpro;

import android.content.Context;
import android.content.Intent;
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

public class UpdateContactActivity extends AppCompatActivity {

    private EditText firstName, lastName, company, address, tel, mail;
    private Spinner sectorSpinner;
    private Button updateButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = getApplicationContext();
        long contactId = getIntent().getLongExtra("contact_id", -1L);
        Contact contact = ContactDatabase.getDb(context).contactDAO().getById(contactId);
        initInterface();
        initSpinner();
        loadContactInformation(contact);
        initButton(contact);
    }

    private void initInterface() {

        Toolbar toolbar = findViewById(R.id.toolbarUpdateContact);
        setSupportActionBar(toolbar);
        firstName = findViewById(R.id.tv_update_firstName);
        lastName = findViewById(R.id.tv_update_lastName);
        company = findViewById(R.id.tv_update_company);
        address = findViewById(R.id.tv_update_address);
        tel = findViewById(R.id.tv_update_tel);
        mail = findViewById(R.id.tv_update_mail);
        updateButton = findViewById(R.id.updateButton);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initButton(Contact contact) {
        updateButton.setOnClickListener(v -> updateContact(contact));
    }

    private void initSpinner() {
        sectorSpinner = findViewById(R.id.tv_update_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sector,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectorSpinner.setAdapter(adapter);
    }

    private void loadContactInformation(Contact contact) {
        firstName.setText(contact.getPrenom());
        lastName.setText(contact.getNom());
        address.setText(contact.getAddresse());
        tel.setText(contact.getTel());
        mail.setText(contact.getEmail());
        company.setText(contact.getSociete());
    }

    public void updateContact(Contact contact) {
        if (areFieldFill()) {
            Contact updatedcontact = new Contact(
                    lastName.getText().toString(),
                    firstName.getText().toString(),
                    company.getText().toString(),
                    address.getText().toString(),
                    tel.getText().toString(),
                    mail.getText().toString(),
                    sectorSpinner.getSelectedItem().toString(),
                    0
            );

            contact.setAddresse(updatedcontact.getAddresse());
            contact.setNom(updatedcontact.getNom());
            contact.setPrenom(updatedcontact.getPrenom());
            contact.setEmail(updatedcontact.getEmail());
            contact.setSociete(updatedcontact.getSociete());
            contact.setSecteur(updatedcontact.getSecteur());
            contact.setTel(updatedcontact.getTel());

            ContactDatabase.getDb(context).contactDAO().update(contact);
            Toast.makeText(UpdateContactActivity.this,
                    "Contact mis à jour", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(UpdateContactActivity.this,
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