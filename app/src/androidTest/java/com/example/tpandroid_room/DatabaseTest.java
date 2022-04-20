package com.example.tpandroid_room;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.tpandroid_room.database.AppDatabase;
import com.example.tpandroid_room.database.Person;
import com.example.tpandroid_room.database.PersonDAO;
import com.example.tpandroid_room.database.TestData;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    // Attributes
    private AppDatabase database;
    private PersonDAO personDAO;
    private Person person;

    // S'éxécute avant chaque test
    @Before
    public void createDb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class).build();
        personDAO = database.personDAO();
    }

    // S'éxécute après chaque test
    @After
    public void closeDb() {

    }


    // Execution d'un seul test
    @Test
    public void insertAllPersons() {
        personDAO.insertAll(TestData.getAll());
        int size = TestData.getAll().size();
        int count = personDAO.getCount();
        Assert.assertEquals(size, count);
    }

    @Test
    public void insertPerson() {
        personDAO.insertPerson(TestData.getAll().get(2));
        int count = personDAO.getCount();
        Assert.assertEquals(1, count);
    }

    @Test
    public void getPersonById() {
        personDAO.insertAll(TestData.getAll());
        Person person = personDAO.getPersonById(3);
        Assert.assertEquals(person, TestData.getAll().get(2));
    }

    public void deleteAll() {
        insertAllPersons();
        int count = personDAO.getCount();
        personDAO.deleteAll();
        Assert.assertEquals(0, count);
    }

}
