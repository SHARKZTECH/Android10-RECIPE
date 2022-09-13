package com.example.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context context;
    List<MyModel> recipes;

    public MyAdapter(Context context, List<MyModel> recipes) {
        this.context = context;
        this.recipes = recipes;
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

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            titleView=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}
