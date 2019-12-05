package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class create_eventActivity extends AppCompatActivity {
    private EditText name;
    private EditText description;
    private Switch hiddenFlag;
    private EditText eventDate;
    private Button createEvent;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        name = findViewById(R.id.editText2);
        eventDate = findViewById(R.id.editText7);
        description = findViewById(R.id.editText8);
        hiddenFlag = findViewById(R.id.switch1);
        createEvent = findViewById(R.id.button2);
        db = FirebaseFirestore.getInstance();

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameString = name.getText().toString();
                String eventDateString = eventDate.getText().toString();
                String descriptionString = description.getText().toString();
                boolean privEvent = hiddenFlag.isChecked();
                Event newEvent = new Event(nameString,eventDateString,descriptionString,privEvent);
                db.collection("events").add(newEvent);
                startActivity(new Intent(getApplicationContext(), eventsActivity.class));
            }
        });
    }
}
