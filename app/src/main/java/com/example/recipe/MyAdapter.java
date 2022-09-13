package com.example.recipe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context context;
    List<MyModel> recipes;
    AlertDialog myDialog;

    public MyAdapter(Context context, List<MyModel> recipes, AlertDialog myDialog) {
        this.context = context;
        this.recipes = recipes;
        this.myDialog = myDialog;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.items_layout,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MyModel recipe=recipes.get(position);
        holder.titleView.setText(recipe.getTitle());
        holder.imageView.setImageResource(recipe.getImage());

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

                myDialog.setTitle("Kuku");
                myDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        ImageView imageView;
        Button details;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            titleView=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.image);
            details=itemView.findViewById(R.id.detailsBtn);
        }
    }
}
