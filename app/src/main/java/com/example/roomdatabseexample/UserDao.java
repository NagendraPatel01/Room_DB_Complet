package com.example.roomdatabseexample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {


    @Insert
    void insertAll(User users);


    @Query("SELECT * FROM User")
   List <User> getalluser();


    @Query("DELETE  FROM User WHERE uid = :id")
    void deletbyid(int id);

    @Query("UPDATE  User SET first_name= :fname, last_name= :lname  WHERE uid = :id")
    void updatebyid(int id,String fname, String lname);



}