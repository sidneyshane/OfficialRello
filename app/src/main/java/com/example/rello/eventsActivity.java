package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class eventsActivity extends AppCompatActivity {

    ImageButton Btn_event, Btn_group, Btn_chat, Btn_calendar;
    Button Btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Btn_event = findViewById(R.id.events_btn);
        Btn_group = findViewById(R.id.groups_btn);
        Btn_chat = findViewById(R.id.chats_btn);
        Btn_calendar = findViewById(R.id.calendar_btn);
        Btn_create = findViewById(R.id.create_event_btn);

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
                openCreateEvents();
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

    public void openCreateEvents(){
        Intent intent = new Intent(this, create_eventActivity.class);
        startActivity(intent);
    }
}
