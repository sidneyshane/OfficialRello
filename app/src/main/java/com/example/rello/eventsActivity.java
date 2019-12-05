package com.example.rello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import butterknife.BindView;
import butterknife.ButterKnife;

public class eventsActivity extends AppCompatActivity {

    ImageButton Btn_event, Btn_group, Btn_chat, Btn_calendar;
    Button Btn_create;
    private FirestoreRecyclerAdapter<Event, EventHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        Btn_group = findViewById(R.id.groups_btn);
        Btn_chat = findViewById(R.id.chats_btn);
        Btn_calendar = findViewById(R.id.calendar_btn);
        Btn_create = findViewById(R.id.create_event_btn);

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

        RecyclerView recyclerView = findViewById(R.id.event_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = FirebaseFirestore.getInstance()
                .collection("events")
                .limit(50);
        FirestoreRecyclerOptions<Event> options = new FirestoreRecyclerOptions.Builder<Event>()
                .setQuery(query, Event.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<Event, EventHolder>(options) {
            @Override
            public void onBindViewHolder(EventHolder holder, int position, Event model) {
                holder.textName.setText(model.getName());
                holder.textDate.setText(model.getEventDate());
                holder.textDescription.setText(model.getDescription());
            }

            @Override
            public EventHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.relativelayout, group, false);

                return new EventHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
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
    public class EventHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.eventName)
        TextView textName;
        @BindView(R.id.eventDate)
        TextView textDate;
        @BindView(R.id.eventDescription)
        TextView textDescription;

        public EventHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
