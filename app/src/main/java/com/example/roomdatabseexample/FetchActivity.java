package com.example.roomdatabseexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class FetchActivity extends AppCompatActivity {

    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);


        // recyclerview data fetch

        getroomdata();
    }

    private void getroomdata() {

        //create db
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();

        //conectivity
        UserDao userDao = db.userDao();

        recyclerview=findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        List<User> users=userDao.getalluser();

        MyAdapter adapter=new MyAdapter(this,users);
        recyclerview.setAdapter(adapter);
    }
}