package com.example.roomdatabseexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    int uid;
    EditText firstnmae,lastnmae;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        firstnmae=findViewById(R.id.firstnmae);
        lastnmae=findViewById(R.id.lastnmae);
        btn=findViewById(R.id.btn);

        uid=Integer.parseInt(getIntent().getStringExtra("uid"));
        firstnmae.setText(getIntent().getStringExtra("ufname"));
        lastnmae.setText(getIntent().getStringExtra("ulname"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //create db
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();

                //conectivity
                UserDao userDao = db.userDao();

                userDao.updatebyid(uid,firstnmae.getText().toString(),lastnmae.getText().toString());
                startActivity(new Intent(UpdateActivity.this,FetchActivity.class));
                finish();

            }
        });
    }
}