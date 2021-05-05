package com.sjb.quicknoteapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    Activity activity;
    private final List<Note> notes;


    public Adapter(Context context, Activity activity, List<Note> notes) {
        this.notes = notes;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.title.setText(notes.get(i).getTitle());
        viewHolder.disception.setText(notes.get(i).getDiscreption());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateNotes.class);
                intent.putExtra("title", notes.get(i).getTitle());
                intent.putExtra("description", notes.get(i).getDiscreption());
                intent.putExtra("id", notes.get(i).getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, disception;
        RelativeLayout layout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleNotes);
            disception = itemView.findViewById(R.id.titleDescription);
            layout = itemView.findViewById(R.id.noteLayout);


        }
    }
}
