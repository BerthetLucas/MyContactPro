package com.example.mycontactpro.pojos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {

    @Query("SELECT * FROM contact")
    public List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE id= :id")
    public Contact getById(Long id);

    @Query("SELECT * FROM contact WHERE favori = 1")
    public List<Contact> getFavorites();

    @Insert
    public void add(Contact... contacts);

    @Update
    public void update(Contact... contacts);

    @Delete
    public void delete(Contact... contacts);

}
