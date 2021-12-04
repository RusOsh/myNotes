package com.example.mynotes2;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private LayoutInflater inflater;
    private List<NotesModel> list = new ArrayList<>();

    private MainActivity activity;
    public NotesAdapter (Context context , MainActivity activity){
        this.inflater = LayoutInflater.from(context);

        this.activity = activity;
    }
    public void editNote(NotesModel model,int position){
        list.get(position).setTitle(model.getTitle());
        list.get(position).setDescription(model.getDescription());
        notifyItemChanged(position);
    }
    public void addNote(NotesModel model){
        list.add(model);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.item_notes, parent,false);
            return new NotesViewHolder(view);
        }


    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtDescription.setText(list.get (position).getDescription());
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = holder.txtTitle.getText().toString();
                String description = holder.txtDescription.getText().toString();

                Intent intent = new Intent(activity, AddNoteActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("description",description);
                intent.putExtra("pos", position);
                intent.putExtra("isEdit",true);

                activity.startActivityForResult(intent,101 );

            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size() ;

    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDescription;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_item_title);
            txtDescription = itemView.findViewById(R.id.txt_item_description);

        }
    }
}

