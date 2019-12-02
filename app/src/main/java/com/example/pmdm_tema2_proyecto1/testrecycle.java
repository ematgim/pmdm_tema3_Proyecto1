package com.example.pmdm_tema2_proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class testrecycle extends AppCompatActivity {

    ShoppingKart shoppingKart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testrecycle);

        Bundle bundle = getIntent().getExtras();

        shoppingKart = (ShoppingKart) bundle.getSerializable("ShoppingKart");

        //ArrayList<Menu> menuList = shoppingKart.getMenuList();


        // Buscamos el RecyclerView e indicamos que su tamaño es fijo
        RecyclerView recycler = findViewById(R.id.recyclerV);

        recycler.setHasFixedSize(true);

        // Añadimos la línea de separación de elementos de la lista
        // 0 para horizontal y 1 para vertical
        recycler.addItemDecoration(new DividerItemDecoration(testrecycle.this, 1));

        // Creamos un LinearLayout que contendrá cada elemento del RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);

        // Creamos el adapter y lo asignamos al RecyclerView
        ResumenPedidoAdapter adapter = new ResumenPedidoAdapter(menuList);

        recycler.setAdapter(adapter);
    }
}
