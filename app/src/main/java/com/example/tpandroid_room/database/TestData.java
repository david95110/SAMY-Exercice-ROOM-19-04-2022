package com.example.tpandroid_room.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestData {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
    static List<Person> listPersons;
    static {
        listPersons = new ArrayList<>();
        try {
            listPersons.add(new Person(formatter.parse("29/1/1988"),"Romain"));
            listPersons.add(new Person(formatter.parse("06/8/2090"),"Jodré"));
            listPersons.add(new Person(formatter.parse("12/11/1651"),"Assma"));
            listPersons.add(new Person(formatter.parse("23/4/1871"),"Olivier"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static List<Person> getAll() {
        return listPersons;
    }
}
