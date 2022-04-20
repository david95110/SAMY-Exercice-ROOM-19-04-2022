package com.example.tpandroid_room.database;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Entity(tableName = "person")
public class Person {

    // Attributes
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private String nom;


    // Constructors
    @Ignore
    public Person() {
    }

    @Ignore
    public Person(Date date, String nom) {
        this.date = date;
        this.nom = nom;
    }

    public Person(int id, Date date, String nom) {
        this.id = id;
        this.date = date;
        this.nom = nom;
    }


    // Getters
    public int getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }

    public String getDateIntoStringFormat() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return formatter.format(date);
    }

    // Setters
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "{"+id+" - "+nom +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, nom);
    }
}
