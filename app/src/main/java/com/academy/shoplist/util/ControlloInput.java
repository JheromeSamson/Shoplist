package com.academy.shoplist.util;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import com.jherome.linx.shoplist.R;


public class ControlloInput {

    public Boolean ControlloIputAddProduct(Map<TextView, String> value) {
        Boolean risultato = false;

        for (Map.Entry key : value.entrySet()) {

                String val = key.getValue().toString();

                if (val.isEmpty()) {
                    TextView textView = (TextView) key.getKey();
                    textView.setText("Campo Mandcante!!!");

                    risultato = true;
                }

        }
    return risultato;
    }
}
