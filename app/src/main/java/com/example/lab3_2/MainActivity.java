package com.example.lab3_2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Contact> contacts;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteAllContacts();

        db.addContact(new Contact(1,"Ravi", "9100000000"));
        db.addContact(new Contact(2,"Srinivas", "9199999999"));
        db.addContact(new Contact(3,"Tommy", "9522222222"));
        db.addContact(new Contact(4,"Karthik", "9533333333"));

        ListView lvContact = findViewById(R.id.lv_contact);
        contacts = db.getAllContacts();
        adapter = new ContactAdapter(this, android.R.layout.simple_list_item_1, contacts);
        lvContact.setAdapter(adapter);


        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                db.deleteContact(contacts.get(arg2));
                contacts.remove(contacts.get(arg2));
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

}