package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.firestore.FirebaseFirestore;

public class create_groupActivity extends AppCompatActivity {
    EditText groupName,groupDescription;
    ImageButton Btn_event, Btn_group, Btn_chat, Btn_calendar;
    Button Btn_create;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        Btn_event = findViewById(R.id.events_btn);
        Btn_group = findViewById(R.id.groups_btn);
        Btn_chat = findViewById(R.id.chats_btn);
        Btn_calendar = findViewById(R.id.calendar_btn);
        Btn_create = findViewById(R.id.create_group_btn);
        groupName = findViewById(R.id.group_name_et);
        groupDescription = findViewById(R.id.group_description_et);

        db = FirebaseFirestore.getInstance();

        Btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });
        Btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGroups();
            }
        });
        Btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChats();
            }
        });
        Btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });
        Btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do storage of Group Created here
                String nameString = groupName.getText().toString();
                String descriptionString = groupDescription.getText().toString();
                Group newGroup = new Group(nameString, descriptionString);
                db.collection("groups").add(newGroup);
                startActivity(new Intent(getApplicationContext(), groups_Activity.class));

                openGroups();
            }
        });
    }

    public void openEvents(){
        Intent intent = new Intent(this, eventsActivity.class);
        startActivity(intent);
    }
    public void openGroups(){
        Intent intent = new Intent(this, groups_Activity.class);
        startActivity(intent);
    }
    public void openChats(){
        Intent intent = new Intent(this, chats_Activity.class);
        startActivity(intent);
    }
    public void openCalendar(){
        Intent intent = new Intent(this, calendar_Activity.class);
        startActivity(intent);
    }
}
