package com.example.mycontactpro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mycontactpro.pojos.Contact;
import com.example.mycontactpro.pojos.ContactDatabase;

public class DetailActivity extends AppCompatActivity {

    private Context context;
    private TextView tvdFirstName, tvdLastName, tvdCompany, tvdTel, tvdMail, tvdSector, tvdAddress;
    private Button callButton, locationButton, smsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = getApplicationContext();

        long contactId = getIntent().getLongExtra("contact_id", -1L);
        Contact contact = ContactDatabase.getDb(context).contactDAO().getById(contactId);

        initToolBar();
        initInterface();
        loadContactData(contact);
        setupButtons(contact);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initInterface() {
        tvdFirstName = findViewById(R.id.tvdFirstName);
        tvdLastName = findViewById(R.id.tvdLastName);
        tvdCompany = findViewById(R.id.tvdCompany);
        tvdTel = findViewById(R.id.tvdTel);
        tvdMail = findViewById(R.id.tvdEmail);
        tvdSector = findViewById(R.id.tvdSector);
        tvdAddress = findViewById(R.id.tvdAddress);

        callButton = findViewById(R.id.callButton);
        locationButton = findViewById(R.id.locationButton);
        smsButton = findViewById(R.id.smsbutton);
    }

    private void loadContactData(Contact contact) {

        tvdFirstName.setText(contact.getPrenom());
        tvdLastName.setText(contact.getNom());
        tvdCompany.setText(contact.getSociete());
        tvdTel.setText(contact.getTel());
        tvdMail.setText(contact.getEmail());
        tvdSector.setText(contact.getSecteur());
        tvdAddress.setText(contact.getAddresse());
    }

    private void setupButtons(Contact contact) {
        callButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + contact.getTel()));
            startActivity(intent);
        });

        smsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + contact.getTel()));
            startActivity(intent);
        });

        locationButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=" + Uri.encode(contact.getAddresse())));
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}