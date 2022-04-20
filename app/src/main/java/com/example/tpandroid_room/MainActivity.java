package com.example.tpandroid_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.tpandroid_room.database.Person;
import com.example.tpandroid_room.database.TestData;
import com.example.tpandroid_room.viewmodel.MainActivityViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private List<Person> persons = new ArrayList<>();
    private static int loadedMenu = R.menu.main_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainActivityRecyclerView);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.ldPersons.observe(this, newList -> {
            persons.clear();
            persons.addAll(newList);
            if(myAdapter == null) {
                // Si l'Adapter n'existe pas, on l'instancie ici
                myAdapter = new MyAdapter(persons, this);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            } else {
                // On notifie à l'Adapter que la liste a changé
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    // Permet d'inflater le menu et de l'afficher sur cette Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(loadedMenu, menu);
        return true;
    }

    public void addPersonsOnClick(MenuItem menuItem) {
        mainActivityViewModel.addPersons((TestData.getAll()));
    }

    public void deleteAllPersonsOnClick(MenuItem menuItem) {
        mainActivityViewModel.deleteAllPersons();
        resetCheckBoxes();
        switchMenu(R.menu.main_menu);
    }

    public void deleteSelectedPersonsOnClick(MenuItem menuItem) {
        if(!MyAdapter.selectedPersons.isEmpty()) {
            // /!\ On doit dupliquer l'ArrayList car même si on le vide à la ligne suivante, le tableau est vidé AVANT que la requête
            // SQL n'arrive en base de données sur le Thread secondaire ! Le delete ne s'effectuera donc pas.
            List<Person> personsToDelete = new ArrayList<>(MyAdapter.selectedPersons);
            mainActivityViewModel.deletePersons(personsToDelete);
            resetCheckBoxes();
            switchMenu(R.menu.main_menu);
        }
    }

    private void resetCheckBoxes() {
        MyAdapter.selectedPersons.clear();
        MyAdapter.actualCheckBoxCounter = 0;
        uncheckAllCheckboxes();
    }

    //
    public void uncheckAllCheckboxes() {
        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
            MyViewHolder holder = (MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            if(holder != null) {
                holder.getCheckboxImageView().setImageResource(android.R.drawable.checkbox_off_background);
            }
        }
    }

    // Permet d'intervertir les menus si aucun paramètre n'est donné
    public void switchMenu() {
        if(loadedMenu == R.menu.main_menu) {
            loadedMenu = R.menu.second_menu;
        } else {
            loadedMenu = R.menu.main_menu;
        }
        invalidateOptionsMenu();
    }

    // Permet de choisir quel menu afficher en le donnant en paramètre en surchargeant la méthode précédante
    public void switchMenu(int menu) {
        loadedMenu = menu;
        invalidateOptionsMenu();
    }
}