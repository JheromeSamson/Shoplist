package com.academy.shoplist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.academy.shoplist.data.ShoplistDatabaseManager;
import com.academy.shoplist.intentConstant.Constant;
import com.academy.shoplist.util.ControlloInput;
import com.academy.shoplist.util.Uuid;
import com.jherome.linx.shoplist.R;
import com.academy.shoplist.bean.Prodotto;

import java.util.HashMap;
import java.util.Map;
import com.academy.shoplist.data.SingletonShopList;

public class AddProdottoActivity extends AppCompatActivity {
    Map<TextView,String> controlValue = new HashMap<TextView,String>();

    EditText nameView;
    EditText descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prodotto);


        Boolean esitoControllo;

        nameView =  (EditText) findViewById(R.id.texteditName);
        descriptionView =  (EditText) findViewById(R.id.texteditdescrizione);
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
                if(ControlloInput.validaInput(nameView,descriptionView).containsKey(false))
                {

                    Toast.makeText(AddProdottoActivity.this,ControlloInput.validaInput(nameView,descriptionView).get(false),Toast.LENGTH_LONG).show();

                }else if(ControlloInput.validaInput(nameView,descriptionView).containsKey(true)) {
                    Uuid uuid = new Uuid();
                    ShoplistDatabaseManager.getInstance(AddProdottoActivity.this).addProdotto(new Prodotto(uuid.generaUUID(), R.drawable.caffe, nameView.getText().toString(), descriptionView.getText().toString()));
                    Intent intent = new Intent();
                    setResult(Constant.REQUESTCODE, intent);
                    finish();
                }
            }
        });


    }
}
