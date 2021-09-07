package com.learningandroid.contactmanager.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learningandroid.contactmanager.R;


public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView row_name;
    public TextView row_occupation;
    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        row_name = itemView.findViewById(R.id.nameTextView);
        row_occupation = itemView.findViewById(R.id.occupationTextView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("clicked", "onClick: inside" );
    }
}