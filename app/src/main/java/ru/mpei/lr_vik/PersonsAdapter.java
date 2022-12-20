package ru.mpei.lr_vik;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {

    public ArrayList<Person> persons = new ArrayList<>();
    private boolean drawHere = false;

    public void setDrawHere(boolean b){
        drawHere = b;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).name);
        holder.isHere.setChecked(persons.get(position).isHere);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView personName;
        public CheckBox isHere;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            isHere = itemView.findViewById(R.id.isHere);
            isHere.setOnCheckedChangeListener((compoundButton, b) -> {
                persons.get(getAdapterPosition()).isHere = b;
            });
            if(drawHere){
                isHere.setVisibility(View.GONE);
            }
        }
    }
}
