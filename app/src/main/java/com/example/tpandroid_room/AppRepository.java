package com.example.tpandroid_room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.tpandroid_room.database.AppDatabase;
import com.example.tpandroid_room.database.Person;
import com.example.tpandroid_room.database.PersonDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// On communique avec la base de données
// Cette classe est toujours un singleton
public class AppRepository {

    // Attributes
    // Une classe qui prends en attribut une instance d'elle même en statique est un Singleton
    private static AppRepository instance;
    public LiveData<List<Person>> ldPersons;
    private AppDatabase database;
    private Executor executor = Executors.newSingleThreadExecutor();

    // Constructors
    public AppRepository(Context context) {
        database = AppDatabase.getInstance(context);
        ldPersons = getAllPersons();
    }

    // Getters
    public static AppRepository getInstance(Context context) {
        if(instance == null) {
            instance = new AppRepository(context);
        }
        return instance;
    }

    private LiveData<List<Person>> getAllPersons() {
        return database.personDAO().getAll();
    }

    // Methods
    public void addPersons(List<Person> persons) {
        executor.execute(() -> database.personDAO().insertAll(persons));
    }

    public void deleteAllPersons() {
        executor.execute(() -> database.personDAO().deleteAll());
    }

    public void deletePersons(List<Person> persons) { executor.execute( () -> database.personDAO().deletePersons(persons)); }

}
