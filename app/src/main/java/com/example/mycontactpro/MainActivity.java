package com.example.mycontactpro;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontactpro.pojos.Contact;
import com.example.mycontactpro.pojos.ContactAdapter;
import com.example.mycontactpro.pojos.ContactDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Resources resources;
    private Toolbar toolbar;

    private RecyclerView rvContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = getApplicationContext();
        resources = getResources();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initRecyclerView();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addContact) {
            Intent intent = new Intent(context, AddContactActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadContacts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }

    private void loadContacts() {
        new Thread() {
            public void run() {
                runOnUiThread(() -> {
                    List<Contact> contacts = ContactDatabase.getDb(context).contactDAO().getAll();
                    ContactAdapter contactAdapter = new ContactAdapter(contacts);
                    contactAdapter.setOnItemClickListener(contact -> {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("contact_id", contact.getId());
                        startActivity(intent);
                    });
                    rvContact.setAdapter(contactAdapter);
                });
            }
        }.start();
    }

    private void initRecyclerView() {
        rvContact = findViewById(R.id.rvContact);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvContact.setHasFixedSize(true);
        rvContact.setLayoutManager(layoutManager);
    }

}