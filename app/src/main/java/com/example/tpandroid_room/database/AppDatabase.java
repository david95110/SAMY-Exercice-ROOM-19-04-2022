package com.example.tpandroid_room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Person.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "AppDatabase.db";

    // Cette variable est une instance de la base de données qui peut
    // être partagée par plusieurs Threads.
    // Elle doit toujours être en mémoie, JAMAIS en cache.
    private static volatile AppDatabase instance;

    // L'accès à l'instance doit être syncronisé
    private static final Object LOCK = new Object();

    public abstract PersonDAO personDAO();

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME
                    ).build();
                }
            }
        }
        return instance;
    }
}








