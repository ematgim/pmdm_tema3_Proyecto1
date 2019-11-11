package com.example.pmdm_tema2_proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResupenPedido extends AppCompatActivity {
    private ShopingKart shoppingKart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resupen_pedido);
        Bundle bundle = getIntent().getExtras();

        shoppingKart = (ShopingKart) bundle.getSerializable("ShoppingKart");

        TextView clientName = findViewById(R.id.txtVClientName);
        TextView cantPizza = findViewById(R.id.txtVCantPizza);
        TextView pizzaName = findViewById(R.id.txtVPizzaName);
        TextView pizzaPriceU = findViewById(R.id.txtVPizzaPriceU);
        TextView pizzaPriceT = findViewById(R.id.txtVPizzaPriceT);

        TextView drinkName = findViewById(R.id.txtVDrinkName);
        TextView drinkPriceU = findViewById(R.id.txtVDrinkPriceU);
        TextView drinkPriceT = findViewById(R.id.txtVDrinkPriceT);
        TextView totalPrice = findViewById(R.id.txtVtotalPriceNumber);

        Button btnCancelar = findViewById(R.id.btnCancel);
        Button btnPay = findViewById(R.id.btnPay);


        clientName.setText(shoppingKart.getClientName());
        cantPizza.setText(String.valueOf(shoppingKart.getnPizzas()));
        pizzaName.setText("Pizza "+ shoppingKart.getPizza().getName());
        pizzaPriceU.setText(shoppingKart.getPizza().getPrice() +"€");
        pizzaPriceT.setText((shoppingKart.getPizza().getPrice()*shoppingKart.getnPizzas()) + "€");

        drinkName.setText(shoppingKart.getDrink().getName());
        drinkPriceU.setText(shoppingKart.getDrink().getPrice() +"€");
        drinkPriceT.setText(shoppingKart.getDrink().getPrice()+ "€");

        totalPrice.setText(shoppingKart.getTotalPrice() +"€");

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
