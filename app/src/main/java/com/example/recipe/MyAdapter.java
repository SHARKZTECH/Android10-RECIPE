package com.example.recipe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context context;
    ArrayList<MealsModel> recipes;


    public MyAdapter(Context context, ArrayList<MealsModel> recipes) {
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
        MealsModel recipe=recipes.get(position);
        holder.titleView.setText(recipe.getName()+recipe.getId());

        Glide.with(holder.imageView).load(recipe.getImage()).into(holder.imageView);


//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(recipe.getImage()).getContent());
//            holder.imageView.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        holder.imageView.setImageResource(recipe.getImage());

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,DetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID",recipes.get(holder.getAdapterPosition()).getName());
                context.startActivity(i);
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
