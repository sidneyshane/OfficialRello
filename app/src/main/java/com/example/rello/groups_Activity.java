package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.rello.ui.login.LoginActivity;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.GroupChannelListQuery;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import java.util.ArrayList;
import java.util.List;

public class groups_Activity extends AppCompatActivity {

    ImageButton Btn_event, Btn_group, Btn_chat, Btn_calendar;
    Button Btn_create, Btn_join;
    String USER_ID;
    String CHANNEL_TYPE = Constants.groupChannelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_);

        Btn_event = findViewById(R.id.events_btn);
        Btn_group = findViewById(R.id.groups_btn);
        Btn_chat = findViewById(R.id.chats_btn);
        Btn_calendar = findViewById(R.id.calendar_btn);
        Btn_create = findViewById(R.id.create_group);
        Btn_join = findViewById(R.id.join_group);

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
                openGroupCreation();
            }
        });
        Btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJoinGroups();
            }
        });

        USER_ID = getIntent().getStringExtra("userID");
        init_sendbird();
    }
    @Override
    protected void onResume() {
        super.onResume();
        connect_sendbird();
        create_group_channel();
        get_group_channels();
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

    public void openGroupCreation(){
        Intent intent = new Intent(this, create_groupActivity.class);
        startActivity(intent);
    }

    public void openJoinGroups(){
        Intent intent = new Intent(this, join_groupActivity.class);
        startActivity(intent);
    }
    protected void create_group_channel() {
        List<String> userIds = new ArrayList<>();
        userIds.add("1233");
        userIds.add("12344");
        userIds.add("12335");
        GroupChannel.createChannelWithUserIds(userIds, true, new GroupChannel.GroupChannelCreateHandler() {
            @Override
            public void onResult(GroupChannel groupChannel, SendBirdException e) {
                if (e != null) {    // Error.
                    return;
                               }
                            }
                        });
    }


    protected void get_group_channels() {
        GroupChannelListQuery channelListQuery = GroupChannel.createMyGroupChannelListQuery();
        channelListQuery.setIncludeEmpty(true);
        channelListQuery.setLimit(100);
        channelListQuery.next(new GroupChannelListQuery.GroupChannelListQueryResultHandler() {
            @Override
            public void onResult(List<GroupChannel> list, SendBirdException e) {
                if (e != null) {    // Error.
                    return;
                }
                populate_group_channel_list(list);
            }
        });
    }
    protected void populate_group_channel_list(List<GroupChannel> list) {
        RecyclerView rvGroupChannelList = findViewById(R.id.channelListRecyclerView);

        GroupChannelListAdapter adapter = new GroupChannelListAdapter(list, CHANNEL_TYPE);
        rvGroupChannelList.setAdapter(adapter);
        rvGroupChannelList.setLayoutManager(new LinearLayoutManager(this));
    }
    protected void connect_sendbird() {
        SendBird.connect(USER_ID, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {    // Error.
                    return;
                }
            }
        });
    }
    protected void init_sendbird() {
        SendBird.init(LoginActivity.APP_ID, this);
    }
}

