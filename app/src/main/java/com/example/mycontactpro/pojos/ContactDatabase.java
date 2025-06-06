package com.example.mycontactpro.pojos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "contact";

    public static ContactDatabase getDb(Context context) {
        return Room.databaseBuilder(context, ContactDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public abstract ContactDAO contactDAO();

}


