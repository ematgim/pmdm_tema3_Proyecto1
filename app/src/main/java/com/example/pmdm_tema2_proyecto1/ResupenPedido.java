package com.example.pmdm_tema2_proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResupenPedido extends AppCompatActivity {
    private ShoppingKart shoppingKart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resupen_pedido);
        Bundle bundle = getIntent().getExtras();

        shoppingKart = (ShoppingKart) bundle.getSerializable("ShoppingKart");

        ArrayList<Menu> menuList = shoppingKart.getMenuList();

        TextView clientName = findViewById(R.id.txtVClientName);
       /* TextView cantPizza = findViewById(R.id.txtVCantPizza);
        TextView pizzaName = findViewById(R.id.txtVPizzaName);
        TextView pizzaPriceU = findViewById(R.id.txtVPizzaPriceU);
        TextView pizzaPriceT = findViewById(R.id.txtVPizzaPriceT);

        TextView drinkName = findViewById(R.id.txtVDrinkName);
        TextView drinkPriceU = findViewById(R.id.txtVDrinkPriceU);
        TextView drinkPriceT = findViewById(R.id.txtVDrinkPriceT);*/
        TextView totalPrice = findViewById(R.id.txtVtotalPriceNumber);

        Button btnCancelar = findViewById(R.id.btnCancel);
        Button btnPay = findViewById(R.id.btnPay);


        //clientName.setText(shoppingKart.getClientName());

        // Buscamos el RecyclerView e indicamos que su tamaño es fijo
        RecyclerView recycler = findViewById(R.id.recyclerView);

        recycler.setHasFixedSize(true);

        // Añadimos la línea de separación de elementos de la lista
        // 0 para horizontal y 1 para vertical
        recycler.addItemDecoration(new DividerItemDecoration(ResupenPedido.this, 1));

        // Creamos un LinearLayout que contendrá cada elemento del RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);

        // Creamos el adapter y lo asignamos al RecyclerView
        ResumenPedidoAdapter adapter = new ResumenPedidoAdapter(menuList);

        recycler.setAdapter(adapter);


       /* cantPizza.setText(String.valueOf(shoppingKart.getnPizzas()));
        pizzaName.setText("Pizza "+ shoppingKart.getPizza().getName());
        pizzaPriceU.setText(shoppingKart.getPizza().getPrice() +"€");
        pizzaPriceT.setText((shoppingKart.getPizza().getPrice()*shoppingKart.getnPizzas()) + "€");

        drinkName.setText(shoppingKart.getDrink().getName());
        drinkPriceU.setText(shoppingKart.getDrink().getPrice() +"€");
        drinkPriceT.setText(shoppingKart.getDrink().getPrice()+ "€");*/

       // totalPrice.setText(shoppingKart.getTotalPrice() +"€");




        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResupenPedido.this,FormularioPago.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ShoppingKart", shoppingKart);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
