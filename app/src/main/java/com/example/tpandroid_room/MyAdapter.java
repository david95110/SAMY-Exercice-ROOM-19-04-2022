package com.example.tpandroid_room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_room.database.Person;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public static int actualCheckBoxCounter = 0;
    List<Person> persons;
    MainActivity mainActivity;
    public static List<Person> selectedPersons = new ArrayList<>();


    public MyAdapter(List<Person> persons, Context context) {
        this.persons = persons;
        this.mainActivity = (MainActivity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setNameTextView(persons.get(position).getNom());
        holder.setDateTextView(persons.get(position).getDateIntoStringFormat());

        holder.getCheckboxImageView().setOnClickListener(view -> {
            Person person = persons.get(position);

            // Compteur avant les changements
            int startCounter = actualCheckBoxCounter;

            if(!holder.isCheckboxIsChecked()) {
                // On décoche la Checkbox, affiche la bonne image, incrémente le compteur, et ajoute la personne
                holder.setCheckboxIsChecked(true);
                holder.setCheckboxImageView(android.R.drawable.checkbox_on_background);
                actualCheckBoxCounter++;
                selectedPersons.add(person);
            } else {
                // On décoche la Checkbox, affiche la bonne image, désincrémente le compteur, et retire la personne
                holder.setCheckboxIsChecked(false);
                holder.setCheckboxImageView(android.R.drawable.checkbox_off_background);
                actualCheckBoxCounter--;
                selectedPersons.remove(person);
            }

            // Compteur après les changements
            int endCounter = actualCheckBoxCounter;
            // On intervertit les menus si nécessaire
            if((startCounter == 0) || (endCounter == 0)) {
                mainActivity.switchMenu();
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
