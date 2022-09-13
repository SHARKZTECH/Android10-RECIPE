package com.example.recipe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MyModel> recipes=new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        recyclerView=findViewById(R.id.recyclerView);
        myAdapter=new MyAdapter(getApplicationContext(),recipes,MyDialog());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));






    }

    private AlertDialog MyDialog() {
                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        View view1=getLayoutInflater().inflate(R.layout.details_layout,null);

                builder.setView(view1);


                TextView title=view1.findViewById(R.id.title);
                Button cancle=view1.findViewById(R.id.cancel_button);

                title.setText("Chips");
                return builder.create();



    }


    private void initData() {
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
        recipes.add(new MyModel("Chips", R.drawable.img));
    }
}