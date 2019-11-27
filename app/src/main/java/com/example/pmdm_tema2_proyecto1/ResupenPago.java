package com.example.pmdm_tema2_proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResupenPago extends AppCompatActivity {
    private Menu shoppingKart;
    private CreditCard creditCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resupen_pago);
        Bundle bundle = getIntent().getExtras();

        shoppingKart = (Menu) bundle.getSerializable("ShoppingKart");
        creditCard =(CreditCard) bundle.getSerializable("CreditCard");
        TextView clientName = findViewById(R.id.txtVClientName);
        TextView cantPizza = findViewById(R.id.txtVCantPizza);
        TextView pizzaName = findViewById(R.id.txtVPizzaName);
        TextView pizzaPriceU = findViewById(R.id.txtVPizzaPriceU);
        TextView pizzaPriceT = findViewById(R.id.txtVPizzaPriceT);

        TextView drinkName = findViewById(R.id.txtVDrinkName);
        TextView drinkPriceU = findViewById(R.id.txtVDrinkPriceU);
        TextView drinkPriceT = findViewById(R.id.txtVDrinkPriceT);
        TextView totalPrice = findViewById(R.id.txtVtotalPriceNumber);

        TextView creditCardType = findViewById(R.id.cardType);
        TextView censoredCreditNum = findViewById(R.id.censoredNumCard);
        TextView  creditCardName = findViewById(R.id.nameCreditCard);


        Button btnSend = findViewById(R.id.btnSend);
        Button btnCancel = findViewById(R.id.btnCancel);

        clientName.setText(shoppingKart.getClientName());
        cantPizza.setText(String.valueOf(shoppingKart.getnPizzas()));
        pizzaName.setText("Pizza "+ shoppingKart.getPizza().getName());
        pizzaPriceU.setText(shoppingKart.getPizza().getPrice() +"€");
        pizzaPriceT.setText((shoppingKart.getPizza().getPrice()*shoppingKart.getnPizzas()) + "€");

        drinkName.setText(shoppingKart.getDrink().getName());
        drinkPriceU.setText(shoppingKart.getDrink().getPrice() +"€");
        drinkPriceT.setText(shoppingKart.getDrink().getPrice()+ "€");

        totalPrice.setText(shoppingKart.getTotalPrice() +"€");

        creditCardType.setText(creditCard.getType());
        censoredCreditNum.setText(creditCard.getCensoredCreditCard());
        creditCardName.setText(creditCard.getOwnerName());





        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cuerpo_mensaje = String.format("Hola %s %n"+
                        "%d x %s -- %.2f %n" +
                         "1 x %s %n" +
                        "TOTAL: %.2f €", shoppingKart.getClientName(), shoppingKart.getnPizzas(),shoppingKart.getPizza().getName(),shoppingKart.getPizza().getPrice(),shoppingKart.getDrink().getName(),shoppingKart.getTotalPrice());
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getText(R.string.mail) + " " +shoppingKart.getClientName());
                intent.putExtra(Intent.EXTRA_TEXT, cuerpo_mensaje);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }


        });
  btnCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent = new Intent(ResupenPago.this, MainActivity.class);
          intent.putExtra("RESULT_OK", 1);
          startActivity(intent);
      }
  });

    }
}
