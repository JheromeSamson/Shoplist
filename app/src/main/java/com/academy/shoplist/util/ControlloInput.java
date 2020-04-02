package com.academy.shoplist.util;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import com.jherome.linx.shoplist.R;


public class ControlloInput {

    public static HashMap<Boolean, String> validaInput(EditText et1, EditText et2) {

        HashMap mappa = new HashMap<Boolean, String>();
        boolean isValidato = true;
        if ((TextUtils.isEmpty(et1.getText().toString())) && (TextUtils.isEmpty(et2.getText().toString()))) {
            isValidato = false;
            mappa.put(isValidato, "devi inserire un nome e una descrizione");
        } else if (TextUtils.isEmpty(et1.getText().toString())) {

            isValidato = false;
            mappa.put(isValidato, "devi inserire il nome del prodotto");
        } else if (TextUtils.isEmpty(et2.getText().toString())) {
            isValidato = false;
            mappa.put(isValidato, "devi inserire la descrizione");
        } else {
            mappa.put(isValidato, "");
        }

        return mappa;
    }
}
