package com.example.recipe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<MyModel> recipes=new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    private String BASE_URL="https://www.themealdb.com/api/json/v1/1/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);

        initData();

        recyclerView=findViewById(R.id.recyclerView);
        myAdapter=new MyAdapter(getApplicationContext(),recipes);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }


    private void initData() {
        Call<MyModel> call=retrofitInterface.excuteItems();
        call.enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                if(response.isSuccessful()){
                    Log.d("Result:",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
    }
}