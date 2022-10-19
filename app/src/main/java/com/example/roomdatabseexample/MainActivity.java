package com.example.roomdatabseexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText firstnmae,lastnmae,id;
    Button btn,fetch;
    TextView lbl,texr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstnmae=findViewById(R.id.firstnmae);
        lastnmae=findViewById(R.id.lastnmae);
        id=findViewById(R.id.id);
        btn=findViewById(R.id.btn);
        lbl=findViewById(R.id.lbl);
        texr=findViewById(R.id.texr);
        fetch=findViewById(R.id.fetch);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create db
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();

                //conectivity
                UserDao userDao = db.userDao();


                    userDao.insertAll(new User(firstnmae.getText().toString(), lastnmae.getText().toString()));
                    id.setText("");
                    firstnmae.setText("");
                    lastnmae.setText("");
                    lbl.setText("Inserted Successfully");


            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // single data fatch

                startActivity(new Intent(MainActivity.this,FetchActivity.class));

                //create db
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();

                //conectivity
                UserDao userDao = db.userDao();

                List<User> users = userDao.getalluser();

                String str = "" ;

                for (User user : users)

                    str = str+"\t "+user.getUid()+ " " +user.getFirstName()+ " " + user.getLastName()+"\n\n";

                texr.setText(str);

            }
        });
    }

}