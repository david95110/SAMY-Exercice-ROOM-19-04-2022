package com.example.tpandroid_room.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPerson(Person person);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Person> persons);

    @Delete
    void deletePerson(Person person);

    @Query("SELECT * FROM person WHERE id=:id")
    Person getPersonById(int id);

    @Query("SELECT * FROM person ORDER BY date DESC")
    LiveData<List<Person>> getAll();

    @Query("DELETE FROM person")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM person")
    int getCount();

    @Delete
    void deletePersons(List<Person> persons);
}