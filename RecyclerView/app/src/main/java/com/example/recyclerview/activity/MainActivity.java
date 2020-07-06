package com.example.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.recyclerview.R;
import com.example.recyclerview.adaptador.RecyclerAdapter;
import com.example.recyclerview.model.ItemList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
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

    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
