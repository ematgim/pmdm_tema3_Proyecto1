package com.example.pmdm_tema2_proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FormularioPago extends AppCompatActivity {

    private Menu shoppingKart;
    private ConstraintLayout cardFront;
    private ConstraintLayout cardBack;
     private CreditCard creditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pago);

        Bundle bundle = getIntent().getExtras();

        shoppingKart = (Menu) bundle.getSerializable("ShoppingKart");
        creditCard = new CreditCard();
        //Formulario datos
        final EditText creditCardNum = findViewById(R.id.edTxtnTargeta);
        final EditText creditCardName = findViewById(R.id.etxtTitularTarjeta);
        final EditText creditCardExpiration = findViewById(R.id.eCreditCardExpiration);
        final EditText creditCardCvv = findViewById(R.id.eTxtCVV);


        //Zona tarjeta
        final TextView cardNumber = findViewById(R.id.cardNumber);
        final TextView cardName = findViewById(R.id.cardName);
        final TextView cardExpiration = findViewById(R.id.cardExpiration);
        final TextView cardCvv = findViewById(R.id.cardCVV);
        //Boton pago

        cardFront = findViewById(R.id.cardFront);
        cardBack = findViewById(R.id.cardBack);


        final Button payBtn = findViewById(R.id.btnPay);
        payBtn.setText(getResources().getText(R.string.pay) + " (" +shoppingKart.getTotalPrice() + "€)");

        //Voltear la tarjeta
        creditCardCvv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cardFront.setVisibility(View.INVISIBLE);
                    cardBack.setVisibility(View.VISIBLE);
                } else {
                    cardFront.setVisibility(View.VISIBLE);
                    cardBack.setVisibility(View.INVISIBLE);
                }
            }
        });
        //Modificacion de los datos de la card
        creditCardCvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardCvv.setText(creditCardCvv.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        creditCardExpiration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardExpiration.setText(creditCardExpiration.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        creditCardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardName.setText(creditCardName.getText().toString().toUpperCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        creditCardNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardNumber.setText(creditCardNum.getText().toString());
                if (creditCardNum.getText().length() > 0) {
                    TypeOfCard(creditCardNum.getText().charAt(0));
                } else {
                    TypeOfCard('0');
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Ejecutamos comprobaciones y lanzamos el intent.
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(creditCardNum.getText().toString().length()<19){
                    Toast.makeText(FormularioPago.this, "Instroduce un numero de tarjata correcto", Toast.LENGTH_SHORT).show();
                }else if(creditCardName.getText().toString().length()<3){
                    Toast.makeText(FormularioPago.this, "Escriba un nombre correcto", Toast.LENGTH_SHORT).show();
                } else if(!checkExpireDate(creditCardExpiration.getText().toString())){
                    Toast.makeText(FormularioPago.this, "Introduzca una fecha de expiracion correcta", Toast.LENGTH_SHORT).show();
                }else if(creditCardCvv.getText().toString().length()!=3){
                    Toast.makeText(FormularioPago.this, "Introduzca un cvv correcto", Toast.LENGTH_SHORT).show();
                }else {
                    creditCard.setNumCard(creditCardNum.getText().toString());
                    creditCard.setOwnerName(creditCardName.getText().toString());
                    creditCard.setExpireDate(creditCardExpiration.getText().toString());
                    creditCard.setCvv(Integer.parseInt(creditCardCvv.getText().toString()));
                    Intent intent = new Intent(FormularioPago.this, ResupenPago.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ShoppingKart", shoppingKart);
                    bundle.putSerializable("CreditCard", creditCard);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean checkExpireDate(String expireDate) {

        if (expireDate.length() == 5) {

            Calendar fecha = Calendar.getInstance();
            String[] parts = expireDate.split("/");
            int anyoActual = Integer.parseInt(String.valueOf(fecha.get(Calendar.YEAR)).substring(1));
            int mesActual = fecha.get(Calendar.MONTH)+1;
            int mes = Integer.parseInt(parts[0]);
            int anyo = Integer.parseInt(parts[1]);

            if (mes <= 12) {
                if (anyo == anyoActual) {
                    if (mes >= mesActual) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (anyo < anyoActual) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }return false;
    }


    private void TypeOfCard(char fistNumber) {
        Resources res = this.getResources();
        switch (fistNumber) {
            case '3':
                cardFront.setBackgroundResource(R.drawable.americanfront);
                cardBack.setBackgroundResource(R.drawable.americanback);
                creditCard.setType("American Express");
                break;
            case '4':
                cardFront.setBackgroundResource(R.drawable.visafront);
                cardBack.setBackgroundResource(R.drawable.visaback);
                creditCard.setType("VISA");
                break;
            case '5':
                cardFront.setBackgroundResource(R.drawable.mastercardfront);
                cardBack.setBackgroundResource(R.drawable.mastercardback);
                creditCard.setType("Master Card");
                break;
            default:
                cardFront.setBackgroundResource(R.drawable.card);
                cardBack.setBackgroundResource(R.drawable.cardback);
                creditCard.setType("Other Type");
                break;
        }
    }
}


