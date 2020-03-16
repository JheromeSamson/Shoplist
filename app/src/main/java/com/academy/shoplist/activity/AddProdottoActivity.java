package com.academy.shoplist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.academy.shoplist.data.ShoplistDatabaseManager;
import com.academy.shoplist.intentConstant.Constant;
import com.jherome.linx.shoplist.R;
import com.academy.shoplist.bean.Prodotto;

import java.util.HashMap;
import java.util.Map;
import com.academy.shoplist.data.SingletonShopList;

public class AddProdottoActivity extends AppCompatActivity {
    Map<TextView,String> controlValue = new HashMap<TextView,String>();
    TextView nameView;
    TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prodotto);


        Boolean esitoControllo;

        nameView =  (TextView) findViewById(R.id.texteditName);
        descriptionView =  (TextView) findViewById(R.id.texteditdescrizione);
/*
        controlValue.put(nameView, nameView.getText().toString());
        controlValue.put(descriptionView, descriptionView.getText().toString());

        ControlloInput controllo = new ControlloInput();

        do{
            esitoControllo = controllo.ControlloIputAddProduct(controlValue);
        }while(esitoControllo != true);
*/

        Button conferma_button = (Button) findViewById(R.id.conferma);

        conferma_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prodotto prodotto = new Prodotto(R.drawable.pizza, nameView.getText().toString(),descriptionView.getText().toString());
                SingletonShopList.getInstance().setProdotto(prodotto);
                ShoplistDatabaseManager.getInstance(AddProdottoActivity.this).addProdotto(new Prodotto(R.drawable.caffe,nameView.getText().toString(),descriptionView.getText().toString()));
                Intent intent = new Intent();
                setResult(Constant.REQUESTCODE,intent);
                finish();
            }
        });

    }
}
