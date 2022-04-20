package com.example.tpandroid_room.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.tpandroid_room.AppRepository;
import com.example.tpandroid_room.database.Person;

import java.util.ArrayList;
import java.util.List;

// Nous avons besoin d'un Context dans le contructeur, on utilise donc AndroidViewModel au lieu de ViewModel
public class MainActivityViewModel extends AndroidViewModel {

    // Attributes
    private AppRepository repository;
    public LiveData<List<Person>> ldPersons;

    // Constructor
    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = AppRepository.getInstance(application.getApplicationContext());
        ldPersons = repository.ldPersons;
    }

    public void addPersons(List<Person> persons) {
        repository.addPersons(persons);
    }

    public void deleteAllPersons() {
        repository.deleteAllPersons();
    }

    public void deletePersons(List<Person> persons) {repository.deletePersons(persons);}
}
