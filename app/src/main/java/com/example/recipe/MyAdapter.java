package com.example.recipe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    MainActivity context;
    ArrayList<MealsModel> recipes;

    ArrayList<MealsModel> data;
    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    private String BASE_URL="https://www.themealdb.com/api/json/v1/1/";


    public MyAdapter(MainActivity context, ArrayList<MealsModel> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.items_layout,null);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MealsModel recipe=recipes.get(position);
        holder.titleView.setText(recipe.getName());

        Glide.with(holder.imageView).load(recipe.getImage()).into(holder.imageView);

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                View view1=context.getLayoutInflater().inflate(R.layout.details_layout,null);
                builder.setView(view1);
                AlertDialog dialog=builder.create();

                TextView title=view1.findViewById(R.id.title);
                TextView instruc=view1.findViewById(R.id.instructions);
                Button yt=view1.findViewById(R.id.yt);
                ImageView imageView=view1.findViewById(R.id.image);
                Button cancle=view1.findViewById(R.id.cancel_button);

                Call<Meals> call=retrofitInterface.excuteItem(recipe.getId());
                call.enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(Call<Meals> call, Response<Meals> response) {
                        if(response.isSuccessful()) {
                            Meals meals = response.body();
                            data=new ArrayList<>(Arrays.asList(meals.getMeals()));

                            instruc.setText(data.get(0).getInstruc());
                            yt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String url=data.get(0).getYt();
                                    Intent i=new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url));
                                    context.startActivity(i);
                                }
                            });
                        }
                    }
                    @Override
                    public void onFailure(Call<Meals> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                title.setText(recipe.getName());
                Glide.with(imageView).load(recipe.getImage()).into(imageView);

                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
//                Intent i=new Intent(context,DetailsActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("ID",recipes.get(holder.getAdapterPosition()).getName());
//                context.startActivity(i);
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
