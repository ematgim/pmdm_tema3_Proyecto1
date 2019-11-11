package com.example.pmdm_tema2_proyecto1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ShopingKart shoppingKart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(getIntent() != null ){

        }
        shoppingKart = new ShopingKart();

        Button btnConfirmSelection = findViewById(R.id.btnConfirmSelection);

        final EditText clientName = findViewById(R.id.eTxtClientName);

        final LinearLayout pizzaBBQ = findViewById(R.id.lLyPizzaBBQ);
        final LinearLayout pizzaMargarita = findViewById(R.id.lLyPizzaMargarita);
        final LinearLayout pizzaRomana= findViewById(R.id.lLyPizzaRomana);

        final TextView quantity = findViewById(R.id.txteQuantity);

        Button btnLessQuantity = findViewById(R.id.btnLessQuantity);
        Button btnMoreQuantity = findViewById(R.id.btnMoreQuantity);

        final LinearLayout water = findViewById(R.id.lLyWater);
        final LinearLayout cola= findViewById(R.id.lLyCola);

        final TextView txtPrecioTotal = findViewById(R.id.txtVTotalPrice);

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingKart.setDrink(new Item(getResources().getString(R.string.water),1.0));
                water.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                cola.setBackgroundColor(Color.TRANSPARENT);
                actualizarPrecio(txtPrecioTotal);
            }
        });
        cola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingKart.setDrink(new Item(getResources().getString(R.string.cola),1.5));
                water.setBackgroundColor(Color.TRANSPARENT);
                cola.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                actualizarPrecio(txtPrecioTotal);
            }
        });

        btnLessQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = shoppingKart.getnPizzas();
                if(n>1){
                    quantity.setText(String.valueOf(--n));
                    shoppingKart.setnPizzas(n);
                }
                actualizarPrecio(txtPrecioTotal);
            }
        });
        btnMoreQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = shoppingKart.getnPizzas();

                    quantity.setText(String.valueOf(++n));
                    shoppingKart.setnPizzas(n);
                actualizarPrecio(txtPrecioTotal);
            }

        });

        pizzaBBQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingKart.setPizza(new Item(getResources().getString(R.string.bbq),2.0));
                pizzaBBQ.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                pizzaMargarita.setBackgroundColor(Color.TRANSPARENT);
                pizzaRomana.setBackgroundColor(Color.TRANSPARENT);
                actualizarPrecio(txtPrecioTotal);
            }
        });
        pizzaMargarita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingKart.setPizza(new Item(getResources().getString(R.string.margaritta),2.5));
                pizzaBBQ.setBackgroundColor(Color.TRANSPARENT);
                pizzaMargarita.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                pizzaRomana.setBackgroundColor(Color.TRANSPARENT);
                actualizarPrecio(txtPrecioTotal);
            }
        });
        pizzaRomana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingKart.setPizza(new Item(getResources().getString(R.string.romana),3.0));
                pizzaBBQ.setBackgroundColor(Color.TRANSPARENT);
                pizzaMargarita.setBackgroundColor(Color.TRANSPARENT);
                pizzaRomana.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                actualizarPrecio(txtPrecioTotal);
            }
        });

        btnConfirmSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                shoppingKart.setClientName(clientName.getText().toString());
                if(shoppingKart.getClientName().equals("")){
                    Toast.makeText(MainActivity.this, "Debe introducir un nombre", Toast.LENGTH_SHORT).show();
                } else if(shoppingKart.getPizza().getName()==""){
                    Toast.makeText(MainActivity.this, "Seleccione una pizza", Toast.LENGTH_SHORT).show();
                }else if(shoppingKart.getnPizzas()<1){
                    Toast.makeText(MainActivity.this, "Debe seleccionar como minimo una porción de pizza", Toast.LENGTH_SHORT).show();
                }else if(shoppingKart.getDrink().getName() ==""){
                    Toast.makeText(MainActivity.this, "Seleccione una bebida", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, ResupenPedido.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ShoppingKart", shoppingKart);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == RESULT_OK){
            Toast.makeText(this, "Pago recibido correctamente!", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarPrecio (TextView txtPrecio){
        Double total = (shoppingKart.getPizza().getPrice() * shoppingKart.getnPizzas()) + shoppingKart.getDrink().getPrice();
        txtPrecio.setText(total+"");
        shoppingKart.setTotalPrice(total);
    }
}
