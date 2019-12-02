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
    private ShoppingKart shoppingKart;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shoppingKart = new ShoppingKart();
        menu = new Menu();

        Button btnConfirmSelection = findViewById(R.id.btnConfirmSelection);

        final EditText clientName = findViewById(R.id.eTxtClientName);

        final LinearLayout pizzaBBQ = findViewById(R.id.lLyPizzaBBQ);
       // final NumberPicker npBBQ = findViewById(R.id.np_bbq);
        final LinearLayout pizzaMargarita = findViewById(R.id.lLyPizzaMargarita);
        //final NumberPicker npMargarita = findViewById(R.id.np_margarita);
        final LinearLayout pizzaRomana= findViewById(R.id.lLyPizzaRomana);
        //final NumberPicker npRomana = findViewById(R.id.np_romana);

       final TextView quantity = findViewById(R.id.txteQuantity);

       Button btnLessQuantity = findViewById(R.id.btnLessQuantity);
       Button btnMoreQuantity = findViewById(R.id.btnMoreQuantity);

        final LinearLayout water = findViewById(R.id.lLyWater);
        final LinearLayout cola= findViewById(R.id.lLyCola);

        final TextView txtPrecioTotal = findViewById(R.id.txtVTotalPrice);

        Button addToShoppingKart = findViewById(R.id.btn_addToShoppingKart);
        /*      npBBQ.setMinValue(0);
        npBBQ.setMaxValue(100);
        npMargarita.setMinValue(0);
        npMargarita.setMaxValue(100);
        npRomana.setMinValue(0);
        npRomana.setMaxValue(100);
*/
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setDrink(new Item(getResources().getString(R.string.water),1.0));
                water.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                cola.setBackgroundColor(Color.TRANSPARENT);
                actualizarPrecio(txtPrecioTotal);
            }
        });
        cola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setDrink(new Item(getResources().getString(R.string.cola),1.5));
                water.setBackgroundColor(Color.TRANSPARENT);
                cola.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                actualizarPrecio(txtPrecioTotal);
            }
        });
        /*npBBQ.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal != 0){

                }
            }
        });*/
       btnLessQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = menu.getnPizzas();
                if(n>1){
                    quantity.setText(String.valueOf(--n));
                    menu.setnPizzas(n);
                }
                actualizarPrecio(txtPrecioTotal);
            }
        });
        btnMoreQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = menu.getnPizzas();

                    quantity.setText(String.valueOf(++n));
                    menu.setnPizzas(n);
                actualizarPrecio(txtPrecioTotal);
            }

        });

     pizzaBBQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setPizza(new Item(getResources().getString(R.string.bbq),2.0));
                pizzaBBQ.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                pizzaMargarita.setBackgroundColor(Color.TRANSPARENT);
                pizzaRomana.setBackgroundColor(Color.TRANSPARENT);
                actualizarPrecio(txtPrecioTotal);
            }
        });
        pizzaMargarita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setPizza(new Item(getResources().getString(R.string.margaritta),2.5));
                pizzaBBQ.setBackgroundColor(Color.TRANSPARENT);
                pizzaMargarita.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                pizzaRomana.setBackgroundColor(Color.TRANSPARENT);
                actualizarPrecio(txtPrecioTotal);
            }
        });
        pizzaRomana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setPizza(new Item(getResources().getString(R.string.romana),3.0));
                pizzaBBQ.setBackgroundColor(Color.TRANSPARENT);
                pizzaMargarita.setBackgroundColor(Color.TRANSPARENT);
                pizzaRomana.setBackgroundColor(Color.parseColor("#86FFFFFF"));
                actualizarPrecio(txtPrecioTotal);
            }
        });

        addToShoppingKart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                menu.setClientName(clientName.getText().toString());
                if(menu.getClientName().equals("")){
                    Toast.makeText(MainActivity.this, "Debe introducir un nombre", Toast.LENGTH_SHORT).show();
                } else if(menu.getPizza().getName()==""){
                    Toast.makeText(MainActivity.this, "Seleccione una pizza", Toast.LENGTH_SHORT).show();
                }else if(menu.getnPizzas()<1){
                    Toast.makeText(MainActivity.this, "Debe seleccionar como minimo una porción de pizza", Toast.LENGTH_SHORT).show();
                }else if(menu.getDrink().getName() ==""){
                    Toast.makeText(MainActivity.this, "Seleccione una bebida", Toast.LENGTH_SHORT).show();
                }else{
                   shoppingKart.addMenu(menu);
                   if(clientName.isEnabled()){
                       shoppingKart.setClientName(clientName.getText().toString());
                       clientName.setEnabled(false);
                   }
                   clearForm();
                }

            }
        });

        btnConfirmSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, testrecycle.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ShoppingKart", shoppingKart);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
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
        Double total = (menu.getPizza().getPrice() * menu.getnPizzas()) + menu.getDrink().getPrice();
        txtPrecio.setText(total+"");
        menu.setTotalPrice(total);
    }
    private void clearForm(){

         LinearLayout pizzaBBQ = findViewById(R.id.lLyPizzaBBQ);
         LinearLayout pizzaMargarita = findViewById(R.id.lLyPizzaMargarita);
         LinearLayout pizzaRomana= findViewById(R.id.lLyPizzaRomana);

         LinearLayout water = findViewById(R.id.lLyWater);
         LinearLayout cola= findViewById(R.id.lLyCola);

         TextView quantity = findViewById(R.id.txteQuantity);
        menu = new Menu();
        pizzaBBQ.setBackgroundColor(Color.TRANSPARENT);
        pizzaMargarita.setBackgroundColor(Color.TRANSPARENT);
        pizzaRomana.setBackgroundColor(Color.TRANSPARENT);

        water.setBackgroundColor(Color.TRANSPARENT);
        cola.setBackgroundColor(Color.TRANSPARENT);
        quantity.setText("1");
    }


}
