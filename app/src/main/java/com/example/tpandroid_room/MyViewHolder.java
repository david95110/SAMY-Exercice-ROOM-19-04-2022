package com.example.tpandroid_room;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    // Attributes
    private ImageView checkboxImageView;
    private TextView nameTextView, dateTextView;


    // Getters
    public ImageView getCheckboxImageView() {
        return checkboxImageView;
    }


    // Setters
    public void setCheckboxImageView(int newImage) {
        this.checkboxImageView.setImageResource(newImage);
    }
    public void setNameTextView(String newName) {
        this.nameTextView.setText(newName);
    }
    public void setDateTextView(String newDate) {
        this.dateTextView.setText(newDate);
    }

    // Constructor
    public MyViewHolder(@NonNull View parent) {
        super(parent);
        checkboxImageView = parent.findViewById(R.id.checkbox);
        nameTextView = parent.findViewById(R.id.name);
        dateTextView = parent.findViewById(R.id.date);
    }
}
