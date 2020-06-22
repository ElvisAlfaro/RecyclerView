package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.recyclerview.adaptador.RecyclerAdapter;
import com.example.recyclerview.model.ItemList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items);
        rvLista.setAdapter(adapter);
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Saga de Broly", "Ultima pelicula de DB, peleas epicas.", R.drawable.saga_broly));
        itemLists.add(new ItemList("Super sayayines 4", "La ultima transformacion de la saga no canon.", R.drawable.ssj4s));
        itemLists.add(new ItemList("Super Sayayiness Blues", "Goku y Vegeta, la transformacion de dioses.", R.drawable.ssj_blues));
        itemLists.add(new ItemList("Goku ultrainstinto", "Infaltablñe power-up a Goku.", R.drawable.ultrainsitinto));
        itemLists.add(new ItemList("Super Vegeta Blue x2", "Diferentes transformaciones de super Vegeta.", R.drawable.super_vegeta));
        itemLists.add(new ItemList("Vegeta sapbe", "Vegeta sapbe o no sapbe xD.", R.drawable.vegeta_blue));
        itemLists.add(new ItemList("Saga de Broly", "Ultima pelicula de DB, peleas epicas.", R.drawable.saga_broly));
        itemLists.add(new ItemList("Super sayayines 4", "La ultima transformacion de la saga no canon.", R.drawable.ssj4s));
        itemLists.add(new ItemList("Super Sayayiness Blues", "Goku y Vegeta, la transformacion de dioses.", R.drawable.ssj_blues));
        itemLists.add(new ItemList("Goku ultrainstinto", "Infaltablñe power-up a Goku.", R.drawable.ultrainsitinto));
        itemLists.add(new ItemList("Super Vegeta Blue x2", "Diferentes transformaciones de super Vegeta.", R.drawable.super_vegeta));
        itemLists.add(new ItemList("Vegeta sapbe", "Vegeta sapbe o no sapbe xD.", R.drawable.vegeta_blue));

        return itemLists;
    }

}
